package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {
    EditText a,b,c,d,e,f;
    String str_a,str_b,str_c,str_d,str_e,str_f;
    Button btn;

    SharedPrefHandler shr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        shr=new SharedPrefHandler(this);

        btn=(Button) findViewById(R.id.signup);
        a=(EditText)findViewById(R.id.fnm);
        b=(EditText)findViewById(R.id.lnm);
        c=(EditText)findViewById(R.id.phn);
        d=(EditText)findViewById(R.id.eml);
        e=(EditText)findViewById(R.id.adrs);
        f=(EditText)findViewById(R.id.pass);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                str_a=a.getText().toString();
                str_b=b.getText().toString();
                str_c=c.getText().toString();
                str_d=d.getText().toString();
                str_e=e.getText().toString();
                str_f=f.getText().toString();

                shr.setSharedPreferences("phone",str_c);
                shr.setSharedPreferences("pass",str_f);
                shr.setSharedPreferences("name",str_a);

                if(str_a.isEmpty()||str_b.isEmpty()||str_c.length()!=10||str_d.isEmpty()||str_e.isEmpty()||str_f.isEmpty())
                {
                    a.setError("Fields cant be empty");
                    b.setError("Fields cant be empty");
                    c.setError("Fields cant be empty");
                    d.setError("Fields cant be empty");
                    e.setError("Fields cant be empty");
                    f.setError("Fields cant be empty");
                }
                else if(!str_a.matches("[a-zA-Z]+"))
                {
                    Toast.makeText(Signup.this, "Enter valid Fname", Toast.LENGTH_SHORT).show();
                }
                else if(!str_b.matches("[a-zA-Z]+"))
                {
                    Toast.makeText(Signup.this, "Enter valid Lname ", Toast.LENGTH_SHORT).show();
                }
                else if(!str_c.matches("^[6-9]\\d{9}"))
                {
                    Toast.makeText(Signup.this, "enter valid Mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(!str_d.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
                {
                    Toast.makeText(Signup.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i=new Intent(getApplication(),Login.class);
                    Toast.makeText(Signup.this, "Get Login into your Account", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    CreateUserAccount();
                    finish();
                    shr.setSharedPreferences("phone",str_c);
                    shr.setSharedPreferences("pass",str_f);
                    shr.setSharedPreferences("fname",str_a);
                    shr.setSharedPreferences("lname",str_b);
                    shr.setSharedPreferences("address",str_e);
                }
            }
        });

    }
    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api Api = retrofit.create(api.class);

        Call<IsExist> call = Api.insert_signup_api(
                str_a,str_b,str_c,str_d,str_e,str_f
        );

        call.enqueue(new Callback<IsExist>() {
            @Override
            public void onResponse(Call<IsExist> call, Response<IsExist> response) {
                IsExist responseResult = response.body();

                Boolean isSuccess = false;
                if(responseResult != null)
                {
                    isSuccess = responseResult.getSuccess();
                }

                if(isSuccess)
                {

                }
                else
                {
                    // Show Creation Failed Message

                }
            }

            @Override
            public void onFailure(Call<IsExist> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
