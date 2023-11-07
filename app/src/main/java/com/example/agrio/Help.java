package com.example.agrio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        txt=(TextView) findViewById(R.id.feed);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ph="9731878747";
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" +ph));
                startActivity(i);

            }
        });
    }
}
