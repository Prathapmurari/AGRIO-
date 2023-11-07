package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity {

    CardView crd_a,crd_b,crd_c,crd_d,crd_e,crd_f;
    SharedPrefHandler shr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        shr=new SharedPrefHandler(this);

        getSupportActionBar().setTitle("HOME");

        crd_a=(CardView) findViewById(R.id.product);
        crd_b=(CardView) findViewById(R.id.equip);
        crd_c=(CardView) findViewById(R.id.orders);
        crd_d=(CardView) findViewById(R.id.govt);
        crd_e=(CardView) findViewById(R.id.payments);
        crd_f=(CardView) findViewById(R.id.aboutus);


        crd_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(Home.this,R.anim.fadein);
                crd_a.startAnimation(animation);
                startActivity(new Intent(Home.this,Products.class));
            }

        });

        crd_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(Home.this,R.anim.fadein);
                crd_b.startAnimation(animation);
                startActivity(new Intent(Home.this,Equipments.class));
            }
        });

        crd_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(Home.this,R.anim.fadein);
                crd_c.startAnimation(animation);
                startActivity(new Intent(Home.this,Orders.class));
            }
        });

        crd_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(Home.this,R.anim.fadein);
                crd_d.startAnimation(animation);
                startActivity(new Intent(Home.this,Govt.class));
            }
        });

        crd_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(Home.this,R.anim.fadein);
                crd_e.startAnimation(animation);
                startActivity(new Intent(Home.this,Qrcode.class));
            }
        });

        crd_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(Home.this,R.anim.fadein);
                crd_f.startAnimation(animation);
                startActivity(new Intent(Home.this,Aboutus.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent d=new Intent(getApplication(),Profile.class);
                startActivity(d);
                Toast.makeText(this, "User Profile", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Intent c = new Intent(getApplication(),Feedback.class);
                startActivity(c);
                Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Intent i = new Intent(getApplication(),Help.class);
                startActivity(i);
                Toast.makeText(this, "Help Center", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                shr.setSharedPreferences("login","false");
                Intent a = new Intent(getApplication(),Login.class);
                startActivity(a);
                Toast.makeText(this, "Logged Out Successful", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
