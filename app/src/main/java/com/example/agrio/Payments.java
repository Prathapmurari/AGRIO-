package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Payments extends AppCompatActivity {

    EditText desc, Amt, Upi, Name;
    Button pay;
    String a,b,c,d;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payments);

        desc=findViewById(R.id.EdtDescription);
        Amt=findViewById(R.id.EdtAmount);
        Upi=findViewById(R.id.EdtUpi);
        Name=findViewById(R.id.EdtName);

        pay=findViewById(R.id.payBtn);

        pay.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                a=desc.getText().toString();
                b=Amt.getText().toString();
                c=Upi.getText().toString();
                d=Name.getText().toString();
                if (a.isEmpty()||b.isEmpty()||c.isEmpty()||d.isEmpty())
                {
                    Toast.makeText(Payments.this, "Fill the Fields correctly", Toast.LENGTH_SHORT).show();
                    desc.setError("Description Cannot be empty");
                    Amt.setError("Amount Filed Cannot be Empty");
                    Upi.setError("Upi Field cannot be empty");
                    Name.setError("Name cannot be empty");
                }
                else{
                    Intent i=new Intent(getApplication(),Home.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(Payments.this, "Payment Completed Successfully", Toast.LENGTH_SHORT).show();                }
            }
        });

    }
}
