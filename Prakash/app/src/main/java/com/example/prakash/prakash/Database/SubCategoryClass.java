package com.example.prakash.prakash.Database;

/**
 * Created by prakash on 10/4/16.
 */

public class SubCategoryClass {
    String name,type,rank,cat_id;

    public SubCategoryClass(String name,String type, String cat_id, String rank ) {

        this.name = name;
        this.type = type;
        this.cat_id = cat_id;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
