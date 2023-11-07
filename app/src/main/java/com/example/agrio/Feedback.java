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

public class Feedback extends AppCompatActivity {
    Button btn;
    EditText a,b,c;
    String str_a,str_b,str_c;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        btn=(Button) findViewById(R.id.snd);
        a=(EditText) findViewById(R.id.msg);
        b=(EditText) findViewById(R.id.phn);
        c=(EditText) findViewById(R.id.sbj);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                str_a = a.getText().toString();
                str_b = b.getText().toString();
                str_c = c.getText().toString();

                if (str_a.isEmpty() || str_b.isEmpty() || str_c.isEmpty()) {
                    a.setError("Subject Cannot be empty");
                    b.setError("Phone cannot be empty");
                    c.setError("Message cannot be Empty");
                    Toast.makeText(Feedback.this, "Fill the Fields correctly", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getApplication(), Home.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(Feedback.this, "Feedback Submitted Successfully ", Toast.LENGTH_SHORT).show();
                    CreateUserAccount();
                }
            }
        });

    }
    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api api = retrofit.create(api.class);

        Call<IsExist> call = api.add_feedback(
              str_a, str_b, str_c
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
