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
 * Created by prakash on 10/3/16.
 */

public class CategoryActivity extends AppCompatActivity {
    final Context context = this;
    DBhandler db;
    String name,type,spec,rank,id;
    EditText mName,mType,mSpec,mRank;
    Button mSave;
    ListView mlist;
    CategoryClass c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        db= new DBhandler(this);
        mlist= (ListView) findViewById(R.id.name);

        final List<CategoryClass> categorylist=db.getAllCategory();
        CategoryAdapter catAdapter= new CategoryAdapter(CategoryActivity.this,R.layout.item_list_content,categorylist);
        mlist.setAdapter(catAdapter);
        mlist.deferNotifyDataSetChanged();

       mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              c= categorylist.get(position);


               Intent intent = new Intent(CategoryActivity.this,ProductActivity.class);
               intent.putExtra("name", name);
               intent.putExtra("type", type);
               intent.putExtra("spec", spec);
               intent.putExtra("rank", rank);
               intent.putExtra("id",c.getId());

               startActivity(intent);
           }
       });


        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_addcategory);
                dialog.setTitle("Title...");

                 mName = (EditText) dialog.findViewById(R.id.activity_addcatagory_name);
                 mType = (EditText) dialog.findViewById(R.id.activity_addcatagory_type);
                 mSpec = (EditText) dialog.findViewById(R.id.activity_addcatagory_spec);
                 mRank = (EditText) dialog.findViewById(R.id.activity_addcatagory_rank);
                mSave = (Button) dialog.findViewById(R.id.activity_addcatagory_save);

                mSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = mName.getText().toString();
                        type = mType.getText().toString();
                        spec = mSpec.getText().toString();
                        rank = mRank.getText().toString();

                        db.add_category(new CategoryClass(name,type,rank,spec,id));

                        Toast.makeText(CategoryActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CategoryActivity.this,CategoryActivity.class));
                    }
                });

                dialog.show();
            }
        });
    }
}
