package com.example.agrio;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface api
{
    String BASE_URL = "https://phonix26.000webhostapp.com/api/";

    @POST("insert_signup_api.php")
    Call<IsExist> insert_signup_api(
            @Query("f1") String str_a,
            @Query("f2") String str_b,
            @Query("f3") String str_c,
            @Query("f4") String str_d,
            @Query("f5") String str_e,
            @Query("f6") String str_f
    );

    String BAS_URL = "https://phonix26.000webhostapp.com/api/";
    @POST("update_user_api.php")
    Call<IsExist> update_user_api(
            @Query("f1") String str_a,
            @Query("f2") String str_b,
            @Query("f3") String str_c,
            @Query("f4") String str_d,
            @Query("f5") String str_e,
            @Query("f6") String str_f
    );

    @GET("https://phonix26.000webhostapp.com/api/display_details_api.php")
    Call<List<userarray>> getProductByCode(@Query("f1") String str_mob);

    @GET("https://phonix26.000webhostapp.com/api/display_product_details_api.php")
    Call<List<product_details_array>> getByCode(@Query("f1") String str_data , @Query("f2") String str_ct);

    @GET("https://phonix26.000webhostapp.com/api/fetch_product_api.php")
    Call<List<products_array>> productcode(@Query("f1") String str_span);


     String BAse_URL = "https://rockstar-nex.000webhostapp.com/Agrio/";
     @POST("https://rockstar-nex.000webhostapp.com/Agrio/insert_orders_api.php")
    Call<IsExist> insert_orders_api(
            @Query("f1") String str_a,
            @Query("f2") String str_b,
            @Query("f3") String str_c,
            @Query("f4") String str_d,
            @Query("f5") String str_e,
            @Query("f6") String str_f,
            @Query("f7") String str_g
    );
     String BASE1_URL ="https://rockstar-nex.000webhostapp.com/Agrio/";
    @POST("equip_enquirers.php")
    Call<IsExist> add_enquirer(
            @Query("f1") String seid,
            @Query("f2") String s_fnm,
            @Query("f3") String s_phn,
            @Query("f4") String s_email
    );

    @GET("https://rockstar-nex.000webhostapp.com/Agrio/get_EquipName.php")
    Call<List<equip_array>> productbycode(@Query("f1") String str_spin);

    @GET("https://rockstar-nex.000webhostapp.com/Agrio/equip_details.php")
    Call<List<Equip_details_array>> getEquipDetials(@Query("f1") String s_ct,@Query("f2") String s_itm);


    @GET("https://rockstar-nex.000webhostapp.com/Agrio/get_EquipName.php")
    Call<List<EquipArray>> getEquipList(@Query("f1") String scat);

    @GET("https://rockstar-nex.000webhostapp.com/Agrio/get_my_order_id.php")
    Call<List<Order_ids_array>> getorderids(@Query("f1") String s_phn);

    @GET("https://rockstar-nex.000webhostapp.com/Agrio/get_order_details.php")
    Call<List<Order_details_array>> getOrderDetials(@Query("f1") String clkpid);

    @GET("https://rockstar-nex.000webhostapp.com/Agrio/get_order_name.php")
    Call<List<Order_name_array>> getOrderName(@Query("f1") String clkpid);

    String BA_URL = "https://rockstar-nex.000webhostapp.com/Agrio/";
    @POST("insert_feedback.php")
    Call<IsExist> add_feedback(
            @Query("f1") String str_a,
            @Query("f2") String str_b,
            @Query("f3") String str_c
    );

}