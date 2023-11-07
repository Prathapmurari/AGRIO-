package com.example.agrio;

public class userarray {
    private String fname;
    private String lname;
    private String 	phone;
    private String email;
    private String address;
    private String 	password;


    public userarray(String fname,String lname, String phone, String email,String address,String password)
    {
        this.fname =fname;
        this.lname= lname;
        this.phone=phone;
        this.email=email;
        this.address=address;
        this.password=password;
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
    public String getEmail()
    {
        return email;
    }
    public String getAddress()
    {
        return address;
    }
    public String getPassword()
    {
        return password;
    }

    public int getProduct_id() {
        return 0;
    }
}
