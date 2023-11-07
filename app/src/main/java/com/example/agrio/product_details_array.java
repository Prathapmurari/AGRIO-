package com.example.agrio;

public class product_details_array {
        private String product_id;
        private String product_name;
        private String 	category;
        private String price;
        private String available_qty;
        private String 	farmer_phone;


        public product_details_array(String product_id,String product_name, String category, String price,String available_qty,String farmer_phone)
        {
            this.product_id =product_id;
            this.product_name= product_name;
            this.category=category;
            this.price=price;
            this.available_qty=available_qty;
            this.farmer_phone=farmer_phone;
        }
        public String getProduct_id()
        {
            return product_id;
        }
        public String getProduct_name()
        {
            return product_name;
        }
        public String getCategory()
        {
            return category;
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


