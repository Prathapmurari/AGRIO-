package com.example.agrio;

public class equip_array {
    private String e_id;
    private String e_name;
    private String category;
    private String username;
    private String phone;
    private String 	address;
    private String rent;


    public equip_array(String i_id,String e_name, String category, String username,String phone,String address,String rent)
    {
        this.e_id =e_id;
        this.e_name=e_name;
        this.category=category;
        this.username=username;
        this.phone=phone;
        this.address=address;
        this.rent=rent;
    }
    public String gete_id()
    {
        return e_id;
    }
    public String gete_name()
    {
        return e_name;
    }
    public String getcategory()
    {
        return category;
    }
    public String getusername()
    {
        return username;
    }
    public String getphone()
    {
        return phone;
    }
    public String getaddress()
    {
        return address;
    }
    public String getrent()
    {
        return rent;
    }
}

