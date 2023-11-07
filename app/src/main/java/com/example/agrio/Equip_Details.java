package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Equip_Details extends AppCompatActivity {

    List<Equip_details_array> productList;
    SharedPrefHandler shr;
    Button sub;
    EditText eid,enme,cat,unme,phn,adrs,rnt;
    String seid,senme,scat,sunme,sphn,sadrs,srnt,s_ct,s_itm,s_fnm,s_lnm,s_phn,s_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_details);

        shr=new SharedPrefHandler(this);
        sub=(Button) findViewById(R.id.sub);
        eid=(EditText) findViewById(R.id.eid);
        enme=(EditText) findViewById(R.id.enme);
        cat=(EditText) findViewById(R.id.cat);
        unme=(EditText) findViewById(R.id.unme);
        phn=(EditText) findViewById(R.id.phn);
        adrs=(EditText) findViewById(R.id.adrs);
        rnt=(EditText) findViewById(R.id.rnt);

        s_ct=shr.getSharedPreferences("ct");
        s_itm=shr.getSharedPreferences("itm");
        s_fnm=shr.getSharedPreferences("fnm");
        s_lnm=shr.getSharedPreferences("lnm");
        s_phn=shr.getSharedPreferences("phn");
        getProductByCode(s_ct,s_itm);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seid=eid.getText().toString();
                s_fnm=enme.getText().toString();
                s_phn=phn.getText().toString();
                s_email=rnt.getText().toString();
                AddEnquirerDetails();
                Intent i = new Intent(getApplication(),Home.class);
                startActivity(i);
                Toast.makeText(Equip_Details.this, "Request Processed for "+seid+" "+s_fnm+" "+s_lnm+" "+s_phn, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getProductByCode(final String s_ct,final String s_itm) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        api api = retrofit.create(api.class);

        Call<List<Equip_details_array>> call = api.getEquipDetials(s_ct,s_itm);

        call.enqueue(new Callback<List<Equip_details_array>>() {
            @Override
            public void onResponse(Call<List<Equip_details_array>> call, Response<List<Equip_details_array>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if(response.body() != null) {
                    isSuccess = true;
                }

                if(isSuccess) {
                    eid.setText(productList.get(0).gete_id());
                    enme.setText(productList.get(0).gete_name());
                    cat.setText(productList.get(0).getcategory());
                    unme.setText(productList.get(0).getusername());
                    phn.setText(productList.get(0).getphone());
                    adrs.setText(productList.get(0).getaddress());
                    rnt.setText(productList.get(0).getrent());


                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Equip_details_array>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddEnquirerDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE1_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api api = retrofit.create(api.class);

        Call<IsExist> call = api.add_enquirer(
                seid,s_fnm,s_phn,s_email
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