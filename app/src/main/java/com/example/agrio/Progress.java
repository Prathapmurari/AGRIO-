package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Progress extends AppCompatActivity {
    SharedPrefHandler shr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);

        shr=new SharedPrefHandler(this);

        Thread th=new Thread()
        {
            @Override
            public void run() {
                super.run();
                {
                    try {
                        sleep(1000);
                        shr=new SharedPrefHandler(getApplicationContext());
                        if (shr.getSharedPreferences("login").equals("NF"))
                        {
                            shr.setSharedPreferences("login","false");
                            Intent i = new Intent(getApplication(),Login.class);
                            startActivity(i);
                            finish();
                        }
                        else if (shr.getSharedPreferences("login").equals("false"))
                        {
                            Intent i = new Intent(getApplication(),Login.class);
                            startActivity(i);
                            finish();
                        }
                        else if (shr.getSharedPreferences("login").equals("true"))
                        {
                            Intent i = new Intent(getApplication(),Home.class);
                            startActivity(i);
                            finish();
                        }

                    }
                    catch(Exception e){

                    }
                }
            }
        };
        th.start();
    }
}
