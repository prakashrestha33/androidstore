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

import com.example.prakash.prakash.Database.CategoryClass;
import com.example.prakash.prakash.Database.DBhandler;
import com.example.prakash.prakash.Database.SubCategoryClass;

import java.util.List;

/**
 * Created by prakash on 10/4/16.
 */

public class SubCategoryActivity extends AppCompatActivity {
    final Context context = this;
    DBhandler db;
    String name,type,cat_id,rank;
    EditText mName,mType,mCatid,mRank;
    String id;
    Button mSave;
    ListView msublist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);


        db= new DBhandler(this);
        id = getIntent().getExtras().getString("id");
        msublist= (ListView) findViewById(R.id.name);

        List<SubCategoryClass> subcategorylist=db.getAllSubCategory(id);
        SubCategoryAdapter subcatAdapter= new SubCategoryAdapter(SubCategoryActivity.this,R.layout.item_list_content,subcategorylist);
        msublist.setAdapter(subcatAdapter);
        msublist.deferNotifyDataSetChanged();

        msublist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(SubCategoryActivity.this,CategoryActivity.class));
            }
        });


        FloatingActionButton addsubcat = (FloatingActionButton) findViewById(R.id.addsubcat);
        addsubcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_addsubcategory);
                dialog.setTitle("Title...");

                mName = (EditText) dialog.findViewById(R.id.activity_addsubcatagory_name);
                mType = (EditText) dialog.findViewById(R.id.activity_addsubcatagory_type);
                mCatid = (EditText) dialog.findViewById(R.id.activity_addsubcatagory_catid);
                mCatid.setText(id.toString());
                mRank = (EditText) dialog.findViewById(R.id.activity_addsubcatagory_rank);
                mSave = (Button) dialog.findViewById(R.id.activity_addsubcatagory_save);

                mSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = mName.getText().toString();
                        type = mType.getText().toString();
                        cat_id = mCatid.getText().toString();
                        rank = mRank.getText().toString();

                        db.add_subcategory(new SubCategoryClass(name,type,cat_id,rank));

                        Toast.makeText(SubCategoryActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });
    }
}
