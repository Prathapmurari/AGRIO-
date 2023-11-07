package com.example.agrio;

public class Order_details_array {
    private String fname;
    private String lname;
    private String address;
    private String phone;
    private String product_id;
    private String 	quantity;


    public Order_details_array(String fname,String lname, String phone, String email,String address,String password)
    {
        this.fname =fname;
        this.lname=lname;
        this.phone=phone;
        this.product_id=product_id;
        this.address=address;
        this.quantity=quantity;
    }
    public String getFname()
    {
        return fname;
    }
    public String getLname()
    {
        return lname;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getproduct_id()
    {
        return product_id;
    }
    public String getAddress()
    {
        return address;
    }
    public String getQuantity()
    {
        return quantity;
    }
}
