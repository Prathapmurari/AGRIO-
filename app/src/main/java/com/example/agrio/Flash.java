package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Flash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash);

        Thread th=new Thread()
        {
            @Override
            public void run() {
                super.run();
                {
                    try {
                        sleep(1000);
                        Intent i=new Intent(getApplication(),Progress.class);
                        startActivity(i);
                        finish();
                    }
                    catch(Exception e)
                    {

                    }
                }
            }
        };
        th.start();
    }
    }

