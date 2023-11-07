package com.example.agrio;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextView txt;
    String str_usname,str_password;
    EditText edt,tdt;
    Button btn_login;
    String str_nm,str_ps;

    SharedPrefHandler shr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        shr=new SharedPrefHandler(this);

        str_nm=shr.getSharedPreferences("phone");
        str_ps=shr.getSharedPreferences("pass");


        txt=(TextView) findViewById(R.id.new_user);
        btn_login=(Button) findViewById(R.id.login);

        edt=(EditText)findViewById(R.id.usr_nm);
        tdt=(EditText) findViewById(R.id.pass);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_usname=edt.getText().toString();
                str_password=tdt.getText().toString();

                if(str_usname.isEmpty()||str_password.isEmpty())
                {
                    Toast.makeText(Login.this, "Fill the Fields Correctly", Toast.LENGTH_SHORT).show();
                    edt.setError("Fields Can't Be empty");
                    tdt.setError("Fields Can't Be empty");
                }
                else if(str_nm.equals(str_usname) && str_ps.equals(str_password)){

                    shr.setSharedPreferences("u_mob",str_usname);
                    shr.setSharedPreferences("login","true");
                    Intent i=new Intent(getApplication(),Home.class);
                    startActivity(i);
                    Toast.makeText(Login.this, "Welcome To Agrio", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(Login.this, "Login Fails Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                }

            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplication(),Signup.class);
                startActivity(i);
                finish();
            }
        });
    }
}
