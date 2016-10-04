package com.example.prakash.prakash.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prakash on 10/3/16.
 */

public class DBhandler extends SQLiteOpenHelper {

    // databasse version
public static final int Db_version=1;

    // database name
public static final String Db_name="itemlist";

    //database table
    public static final String itemlist_category="category";
    public static final String itemlist_subcategory="sub_category";
    public static final String itemlist_product="product";
    //public static final String itemlist_detail="detail";

    //category data
    public static final String category_id = "id";
    public static final String category_name = "name";
    public static final String category_type = "type  ";
    public static final String category_specification = "specification";
    public static final String category_rank = "rank";


    //category sql writting
    public static final  String create_table_category =
        "CREATE TABLE "+itemlist_category +
                "(" + category_id +" integer primary key,"
                    +category_name +" text,"
                    +category_type +" text,"
                    +category_specification +" text,"
                    +category_rank +" integer" +")";

    //sub_category data
    public static final String sub_category_id = "id";
    public static final String sub_category_name = "name";
    public static final String sub_category_type = "type  ";
    public static final String sub_category_catid = "cat_id";
    public static final String sub_category_rank = "rank";


    //sub_category sql writting
    public static final  String create_table_sub_category =
            "CREATE TABLE "+itemlist_subcategory +
                    "(" + sub_category_id +" integer primary key,"
                    +sub_category_name +" text,"
                    +sub_category_type +" text,"
                    +sub_category_catid +" integer,"
                    +sub_category_rank +" integer" +")";



    //product data
    public static final String product_id = "id";
    public static final String product_name = "name";
    public static final String product_catid = "cat_id  ";
    public static final String product_brand = "brand";
    public static final String product_suppliedby = "supplied_by";
    public static final String product_costprice = "cost_price";
    public static final String product_sellingprice = "selling_price";
    public static final String product_wholesaleprice = "wholesale_price";
    public static final String product_quantity = "quantity";
    public static final String product_rank = "rank";


    //product sql writting
    public static final  String create_table_product =
            "CREATE TABLE "+itemlist_product +
                    "(" + product_id +" integer primary key,"
                        +product_name +" text,"
                        +product_catid +" integer,"
                        +product_brand +" text,"
                        +product_suppliedby +" text,"
                        +product_costprice +" text,"
                        +product_sellingprice +" text,"
                        +product_wholesaleprice +" text,"
                        +product_quantity +" text,"
                        +product_rank +" integer" +")";


    //detail data
//    public static final String detail_id = "id";
//    public static final String detail_name = "name";
//    public static final String detail_catid = "cat_id  ";
//    public static final String detail_subcatid = "sub_cat_id";
//    public static final String detail_brand = "brand";
//    public static final String detail_suppliedby = "supplied_by";
//    public static final String detail_image = "image";
//
//
//    //detail sql writting
//    public static final  String create_table_detail =
//            "CREATE TABLE "+itemlist_detail +
//                    "(" + detail_id +" integer primary key,"
//                        +detail_name +" text,"
//                        +detail_catid +" integer,"
//                        +detail_subcatid +" integer,"
//                        +detail_suppliedby +" text,"
//                        +detail_brand +" text,"
//                        +detail_image +" text" +")";

    public DBhandler(Context context) {
        super(context, Db_name, null, Db_version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_category);
        db.execSQL(create_table_sub_category);
        db.execSQL(create_table_product);
        //db.execSQL(create_table_detail);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists" + itemlist_category);
    db.execSQL("drop table if exists" + itemlist_subcategory);
    db.execSQL("drop table if exists" + itemlist_product);
   // db.execSQL("drop table if exists" + itemlist_detail);

        onCreate(db);
    }


    public void add_category(CategoryClass category){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(category_name,category.getName());
        contentValues.put(category_rank,category.getRank());
        contentValues.put(category_type,category.getType());
        contentValues.put(category_specification,category.getSpec());

        sqLiteDatabase.insert(itemlist_category,null,contentValues);
        sqLiteDatabase.close();
  }


    public List<CategoryClass> getAllCategory(){
        List<CategoryClass> categorylist = new ArrayList<>();

        // Select All Query
        String select = "SELECT * FROM " + itemlist_category;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CategoryClass category = new CategoryClass(category_name,category_type,category_specification,category_rank,category_id);
                category.setName(cursor.getString(1));
                category.setType(cursor.getString(2));
                category.setSpec(cursor.getString(3));
                category.setRank(cursor.getString(4));
                category.setId(cursor.getString(0));


                // Adding contact to list
                categorylist.add(category);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return contact list
        return categorylist;

    }

    public void add_subcategory(SubCategoryClass subcategory){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(sub_category_name,subcategory.getName());
        contentValues.put(sub_category_rank,subcategory.getRank());
        contentValues.put(sub_category_catid,subcategory.getCat_id());
        contentValues.put(sub_category_type,subcategory.getType());

        sqLiteDatabase.insert(itemlist_subcategory,null,contentValues);
        sqLiteDatabase.close();
    }


    public List<SubCategoryClass> getAllSubCategory(String id){
        List<SubCategoryClass> subcategorylist = new ArrayList<>();

        // Select All Query
        String select = "SELECT * FROM " + itemlist_subcategory +" where "+sub_category_catid + "=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SubCategoryClass subcategory = new SubCategoryClass(sub_category_name,sub_category_type,sub_category_catid,sub_category_rank);
                subcategory.setName(cursor.getString(1));
                subcategory.setType(cursor.getString(2));
                subcategory.setCat_id(cursor.getString(3));
                subcategory.setRank(cursor.getString(4));


                // Adding contact to list
                subcategorylist.add(subcategory);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return contact list
        return subcategorylist;

    }


    public void add_product(ProductClass product){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(product_name,product.getName());
        contentValues.put(product_catid,product.getCat_id());
        contentValues.put(product_brand,product.getBrand());
        contentValues.put(product_suppliedby,product.getSupplied_by());
        contentValues.put(product_costprice,product.getCost_price());
        contentValues.put(product_sellingprice,product.getSelling_price());
        contentValues.put(product_wholesaleprice,product.getWholesale_price());
        contentValues.put(product_quantity,product.getQuantity());
        contentValues.put(product_rank,product.getRank());



        sqLiteDatabase.insert(itemlist_product,null,contentValues);
        sqLiteDatabase.close();
    }


    public List<ProductClass> getAllProduct(String id){
        List<ProductClass> productlist = new ArrayList<>();

        // Select All Query
        String select = "SELECT * FROM " + itemlist_product +" where "+product_catid + "=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductClass product = new ProductClass(product_name,product_catid,product_brand,product_suppliedby,product_costprice,product_sellingprice,product_wholesaleprice,product_quantity,product_rank);
                product.setName(cursor.getString(1));
                product.setCat_id(cursor.getString(2));
                product.setBrand(cursor.getString(3));
                product.setSupplied_by(cursor.getString(4));
                product.setCost_price(cursor.getString(5));
                product.setSelling_price(cursor.getString(6));
                product.setWholesale_price(cursor.getString(7));
                product.setQuantity(cursor.getString(8));
                product.setRank(cursor.getString(9));


                // Adding contact to list
                productlist.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return contact list
        return productlist;

    }



}
