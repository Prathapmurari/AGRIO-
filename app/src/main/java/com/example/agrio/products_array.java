package com.example.agrio;

public class products_array {

    private String product_id;
    private String product_name;
    private String price;
    private String available_qty;
    private String farmer_phone;

    public products_array(String product_id,String product_name,String price,String available_qty,String farmer_phone)
    {
        this.product_id =product_id;
    }
    public String getProduct_id()
    {
        return product_id;
    }
    public String getProduct_name()
    {
        return product_name;
    }
    public String getPrice()
    {
        return price;
    }
    public String getAvailable_qty()
    {
        return available_qty;
    }
    public String getFarmer_phone()
    {
        return farmer_phone;
    }
}