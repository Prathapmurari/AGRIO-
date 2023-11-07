package com.example.agrio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Qrcode extends AppCompatActivity {
    EditText txt;
    Button btn;
    String str;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);

        txt=(EditText) findViewById(R.id.editText);
        btn=(Button) findViewById(R.id.btnsub);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str=txt.getText().toString();

                if (str.isEmpty()){
                    Toast.makeText(Qrcode.this, "Fields cannot be Empty", Toast.LENGTH_SHORT).show();
                    txt.setError("Fill the ID");
                }
                else {
                    Toast.makeText(Qrcode.this, "Complete Further Details", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplication(), Payments.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }
}
