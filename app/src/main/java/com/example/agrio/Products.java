package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Products extends AppCompatActivity {
    Button btn;

    String[] products;
    List<products_array> productList;
    ArrayAdapter<String> adapter;
    Spinner span;
    ListView lst;
    String str_span;
    SharedPrefHandler shr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);

        btn = (Button) findViewById(R.id.btn_search);
        span = (Spinner) findViewById(R.id.spin);
        lst = (ListView) findViewById(R.id.list);
        shr=new SharedPrefHandler(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_span = span.getSelectedItem().toString();
                shr.setSharedPreferences("item_cat",str_span);
                productcode(str_span);
            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String str_item_name=lst.getItemAtPosition(position).toString();

            shr.setSharedPreferences("item_name",str_item_name);

            Intent i=new Intent(getApplication(),Product_details.class);
            startActivity(i);
    }
});

    }
    private void productcode(final String str_span) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();

            api api = retrofit.create(api.class);

            Call<List<products_array>> call = api.productcode(str_span);

            call.enqueue(new Callback<List<products_array>>() {
                @Override
                public void onResponse(Call<List<products_array>> call, Response<List<products_array>> response) {
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
                public void onFailure(Call<List<products_array>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }

        private void loadProductListView() {
            //Creating an String array for the ListViewre
            products = new String[productList.size()];



            //looping through all the products and inserting the names inside the string array
            for (int i = 0; i < productList.size(); i++) {
                products[i] = productList.get(i).getProduct_name();

            }

            adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.item, products);
            lst.setAdapter(adapter);



        }
    }

