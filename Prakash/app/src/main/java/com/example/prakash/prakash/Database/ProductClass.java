package com.example.prakash.prakash.Database;

/**
 * Created by prakash on 10/5/16.
 */

public class ProductClass {
    String name,cat_id, brand,supplied_by,cost_price,selling_price,wholesale_price,quantity,rank;

    public ProductClass(String name, String cat_id,  String brand, String supplied_by, String cost_price, String selling_price, String wholesale_price, String quantity, String rank) {
        this.name = name;
        this.cat_id = cat_id;
        this.brand = brand;
        this.supplied_by = supplied_by;
        this.cost_price = cost_price;
        this.selling_price = selling_price;
        this.wholesale_price = wholesale_price;
        this.quantity = quantity;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupplied_by() {
        return supplied_by;
    }

    public void setSupplied_by(String supplied_by) {
        this.supplied_by = supplied_by;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getWholesale_price() {
        return wholesale_price;
    }

    public void setWholesale_price(String wholesale_price) {
        this.wholesale_price = wholesale_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}

