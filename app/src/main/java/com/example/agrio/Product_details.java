package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Product_details extends AppCompatActivity {
    EditText edt_a, edt_b, edt_c, edt_d, edt_e,edt_f;
    String str_a,str_b,str_c,str_d,str_f,str_e,str_g;
    List<product_details_array> productList;
    SharedPrefHandler shr;
    String str_data,str_ct;
    Button btn_or;
    String str_or;
    EditText or;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        shr=new SharedPrefHandler(this);

        edt_a=(EditText) findViewById(R.id.product_id);
        edt_b=(EditText) findViewById(R.id.product_name);
        edt_c=(EditText) findViewById(R.id.category);
        edt_d=(EditText) findViewById(R.id.price);
        edt_e=(EditText) findViewById(R.id.avaliable_qty);
        edt_f=(EditText) findViewById(R.id.farmer_phone);

        btn_or=(Button)findViewById(R.id.ord);
        or=(EditText)findViewById(R.id.qnt);

        str_data=shr.getSharedPreferences("item_name");
        str_ct=shr.getSharedPreferences("item_cat");


        Toast.makeText(this, ""+str_data +str_ct, Toast.LENGTH_SHORT).show();

        getByCode(str_data,str_ct);

        btn_or.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                str_a=shr.getSharedPreferences("fname");
                str_b=shr.getSharedPreferences("lname");
                str_c=shr.getSharedPreferences("address");
                str_d=shr.getSharedPreferences("phone");

                str_e=edt_a.getText().toString();
                str_f=or.getText().toString();
                str_g=edt_f.getText().toString();

                if(str_f.isEmpty())
                {
                    Toast.makeText(Product_details.this, ""+str_a +str_b +str_c +str_d +str_e +str_f +str_g, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Product_details.this, "Enter Quantity", Toast.LENGTH_SHORT).show();
                }
                else {
                    int int_aa = Integer.parseInt(str_f);
                    int int_bb = Integer.parseInt(edt_e.getText().toString());
                    if (int_aa >= int_bb) {
                        Toast.makeText(Product_details.this, "Your Qnty is more than avalible Qnty", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(Product_details.this, "" + str_a + str_b + str_c + str_d + str_e + str_f + str_g, Toast.LENGTH_SHORT).show();
                        CreateUserAccount();
                        Intent i = new Intent(getApplication(), Products.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(Product_details.this, "Thank You for your order", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void CreateUserAccount() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BAse_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api Api = retrofit.create(api.class);

        Call<IsExist> call = Api.insert_orders_api(
                str_a, str_b, str_c, str_d, str_e, str_f, str_g
        );
        call.enqueue(new Callback<IsExist>() {
            @Override
            public void onResponse(Call<IsExist> call, Response<IsExist> response) {
                IsExist responseResult = response.body();

                Boolean isSuccess = false;
                if (responseResult != null) {
                    isSuccess = responseResult.getSuccess();
                }

                if (isSuccess) {

                } else {
                    // Show Creation Failed Message

                }
            }

            @Override
            public void onFailure(Call<IsExist> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getByCode(final String str_data,String str_ct) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BAse_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        api api = retrofit.create(api.class);

        Call<List<product_details_array>> call = api.getByCode(str_ct,str_data);

        call.enqueue(new Callback<List<product_details_array>>() {
            @Override
            public void onResponse(Call<List<product_details_array>> call, Response<List<product_details_array>> response) {
                productList = response.body();

                Boolean isSuccess = false;
                if (response.body() != null) {
                    isSuccess = true;
                }

                if (isSuccess) {
                    edt_a.setText(productList.get(0).getProduct_id());
                    edt_b.setText("Product NAME :"+ productList.get(0).getProduct_name());
                    edt_c.setText("Category :"+ productList.get(0).getCategory());
                    edt_d.setText("Price :"+ productList.get(0).getPrice());
                    edt_e.setText(productList.get(0).getAvailable_qty());
                    edt_f.setText(productList.get(0).getFarmer_phone());
                    //finish();
                } else {

                }
            }
            @Override
            public void onFailure(Call<List<product_details_array>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Product_details.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });

    }

}