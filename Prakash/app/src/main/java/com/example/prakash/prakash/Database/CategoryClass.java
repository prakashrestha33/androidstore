package com.example.prakash.prakash.Database;

/**
 * Created by prakash on 10/4/16.
 */

public class CategoryClass {
    String name,type,rank,spec,id;

    public CategoryClass(String name, String type, String rank, String spec, String id) {
        this.name = name;
        this.type = type;
        this.rank = rank;
        this.spec = spec;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
