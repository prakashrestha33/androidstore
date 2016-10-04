package com.example.prakash.prakash;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.prakash.prakash.Database.DBhandler;
import com.example.prakash.prakash.Database.ProductClass;

import java.util.List;

/**
 * Created by prakash on 10/5/16.
 */

public class ProductActivity extends AppCompatActivity {

    final Context context = this;
    DBhandler db;
    String name,cat_id,brand,suppliedby,costprice,sellingprice,wholesaleprice,quantity,rank;
    EditText mName,mCatid,mBrand,mSuppliedby,mCostprice,mSellingprice,mWholesaleprice,mQuantity,mRank;
    String id;
    Button mSave;
    ListView mproductlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        db= new DBhandler(this);
        id = getIntent().getExtras().getString("id");
        mproductlist= (ListView) findViewById(R.id.name);


        List<ProductClass> productlist=db.getAllProduct(id);
        ProductAdapter productAdapter= new ProductAdapter(ProductActivity.this,R.layout.item_list_content,productlist);
        mproductlist.setAdapter(productAdapter);
        mproductlist.deferNotifyDataSetChanged();

        mproductlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(ProductActivity.this,CategoryActivity.class));
            }
        });


        FloatingActionButton addproduct = (FloatingActionButton) findViewById(R.id.addproduct);
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_addproduct);
                dialog.setTitle("Add Product");

                mName = (EditText) dialog.findViewById(R.id.activity_addproduct_name);
                mCatid  = (EditText) dialog.findViewById(R.id.activity_addproduct_catid);
                mCatid.setText(id.toString());
                mBrand  = (EditText) dialog.findViewById(R.id.activity_addproduct_brand);
                mSuppliedby  = (EditText) dialog.findViewById(R.id.activity_addproduct_supplied_by);
                mCostprice  = (EditText) dialog.findViewById(R.id.activity_addproduct_costprice);
                mSellingprice  = (EditText) dialog.findViewById(R.id.activity_addproduct_sellingprice);
                mWholesaleprice  = (EditText) dialog.findViewById(R.id.activity_addproduct_wholesaleprice);
                mQuantity = (EditText) dialog.findViewById(R.id.activity_addproduct_quantity);
                mRank  = (EditText) dialog.findViewById(R.id.activity_addproduct_rank);

                mSave = (Button) dialog.findViewById(R.id.activity_addproduct_save);

                mSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = mName.getText().toString();
                        cat_id = mCatid.getText().toString();
                        brand = mBrand.getText().toString();
                        suppliedby = mSuppliedby.getText().toString();
                        costprice = mCostprice.getText().toString();
                        sellingprice = mSellingprice.getText().toString();
                        wholesaleprice = mWholesaleprice.getText().toString();
                        quantity = mQuantity.getText().toString();
                        rank = mRank.getText().toString();

                        db.add_product(new ProductClass(name,cat_id,brand,suppliedby,costprice,sellingprice,wholesaleprice,quantity,rank));

                        Toast.makeText(ProductActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProductActivity.this,ProductActivity.class));
                    }
                });

                dialog.show();
            }
        });
    }
}
