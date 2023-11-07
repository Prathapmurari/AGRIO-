package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Equipments extends AppCompatActivity {

    SharedPrefHandler shr;
    Spinner cat;
    String scat;
    String[] products;
    Button dsp;
    ListView equ_lst;
    List<EquipArray> productList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);

        shr=new SharedPrefHandler(this);

        equ_lst= (ListView) findViewById(R.id.equ_lst);

        cat= (Spinner) findViewById(R.id.cat);

        dsp= (Button) findViewById(R.id.b_dsp);

        dsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scat=cat.getSelectedItem().toString();
                getProductByCode(scat);
                shr.setSharedPreferences("ct",scat);
                Toast.makeText(Equipments.this, "Details Displaying..", Toast.LENGTH_SHORT).show();
            }
        });

        equ_lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String clkdItem = equ_lst.getItemAtPosition(pos).toString();
                shr.setSharedPreferences("itm",clkdItem);
                startActivity(new Intent(Equipments.this,Equip_Details.class));
            }
        });
    }

    private void getProductByCode(final  String scat) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        api api = retrofit.create(api.class);

        Call<List<EquipArray>> call = api.getEquipList(scat);

        call.enqueue(new Callback<List<EquipArray>>() {
            @Override
            public void onResponse(Call<List<EquipArray>> call, Response<List<EquipArray>> response) {
                // List<Product> responseResult = response.body();
                productList = response.body();

                Boolean isSuccess = false;
                if(productList != null) {
                    isSuccess = true;
                }

                if(isSuccess) {

                    // responseResult.getSuccess();
                    // Update all field with result data

                    loadProductListView();

                } else {


                }
            }

            @Override
            public void onFailure(Call<List<EquipArray>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView() {
        //Creating an String array for the ListView
        products = new String[productList.size()];



        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++) {
            products[i] = productList.get(i).gete_name();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_items, R.id.eqlst, products);
        equ_lst.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_e, menu);
        return true;
    }

    }