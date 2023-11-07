package com.example.agrio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Orders extends AppCompatActivity {

    ListView pid_lst;
    EditText fn,ln,adrs,phn,pid,pnme,qty;
    Button sub;
    List<Order_ids_array> productList;
    List<Order_details_array> ordersdetailsList;
    List<Order_name_array> orderList;
    ArrayAdapter<String> adapter;
    SharedPrefHandler shr;
    String s_phn;
    String[] products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        shr= new SharedPrefHandler(this);

        s_phn=shr.getSharedPreferences("phone");

        pid_lst=(ListView) findViewById(R.id.pid_lst);

        fn=(EditText) findViewById(R.id.fn);
        ln=(EditText) findViewById(R.id.ln);
        adrs=(EditText) findViewById(R.id.adrs);
        phn=(EditText) findViewById(R.id.phn);
        pid=(EditText) findViewById(R.id.oid);
        pnme=(EditText) findViewById(R.id.pnme);
        qty=(EditText) findViewById(R.id.qty);

        sub=(Button) findViewById(R.id.sub);


        getProductByCode(s_phn);
        Toast.makeText(Orders.this, "Orders Displaying for "+s_phn, Toast.LENGTH_SHORT).show();

        pid_lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String clkpid = pid_lst.getItemAtPosition(pos).toString();
                Toast.makeText(Orders.this, "Order details of product id "+clkpid+" is displaying", Toast.LENGTH_LONG).show();
                getOrderById(clkpid);
                getProductNameById(clkpid);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sphn=phn.getText().toString();
                String msg="Your product will is dispatched and it will be delivered as soon as possible..";
                Toast.makeText(getApplicationContext(), "Message Sent successfully to "+sphn,Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplication(),Home.class);
                startActivity(i);
            }
        });
    }

    private void getProductByCode(final  String s_phn) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        api api = retrofit.create(api.class);

        Call<List<Order_ids_array>> call = api.getorderids(s_phn);

        call.enqueue(new Callback<List<Order_ids_array>>() {
            @Override
            public void onResponse(Call<List<Order_ids_array>> call, Response<List<Order_ids_array>> response) {
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
            public void onFailure(Call<List<Order_ids_array>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProductListView() {
        //Creating an String array for the ListView
        products = new String[productList.size()];



        //looping through all the products and inserting the names inside the string array
        for (int i = 0; i < productList.size(); i++) {
            products[i] = productList.get(i).getproduct_id();
        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_ids, R.id.idlst, products);
        pid_lst.setAdapter(adapter);
    }

    private void getOrderById(final String clkpid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        api api = retrofit.create(api.class);

        Call<List<Order_details_array>> call = api.getOrderDetials(clkpid);

        call.enqueue(new Callback<List<Order_details_array>>() {
            @Override
            public void onResponse(Call<List<Order_details_array>> call, Response<List<Order_details_array>> response) {
                ordersdetailsList = response.body();

                Boolean isSuccess = false;
                if(response.body() != null) {
                    isSuccess = true;
                }

                if(isSuccess) {
                    fn.setText(ordersdetailsList.get(0).getFname());
                    ln.setText(ordersdetailsList.get(0).getLname());
                    adrs.setText(ordersdetailsList.get(0).getAddress());
                    phn.setText(ordersdetailsList.get(0).getPhone());
                    pid.setText(ordersdetailsList.get(0).getproduct_id());
                    qty.setText(ordersdetailsList.get(0).getQuantity());


                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Order_details_array>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProductNameById(final String clkpid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        api api = retrofit.create(api.class);

        Call<List<Order_name_array>> call = api.getOrderName(clkpid);

        call.enqueue(new Callback<List<Order_name_array>>() {
            @Override
            public void onResponse(Call<List<Order_name_array>> call, Response<List<Order_name_array>> response) {
                orderList = response.body();

                Boolean isSuccess = false;
                if(response.body() != null) {
                    isSuccess = true;
                }

                if(isSuccess) {
                    pnme.setText(orderList.get(0).getProduct_name());


                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Order_name_array>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}