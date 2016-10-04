package com.example.prakash.prakash;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prakash.prakash.Database.ProductClass;
import com.example.prakash.prakash.Database.SubCategoryClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prakash on 10/5/16.
 */

public class ProductAdapter extends ArrayAdapter<ProductClass> {
    Context context;
    int resource_id;


    List<ProductClass> productClassList= new ArrayList<>();

    public ProductAdapter(Context context, int resource_id, List<ProductClass> productClassList) {
        super(context, resource_id,productClassList);
        this.context = context;
        this.resource_id = resource_id;
        this.productClassList = productClassList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row= convertView;
       ProductAdapter.product holder = null;

        if (row ==null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(resource_id,parent,false);

            holder = new ProductAdapter.product();
            holder.name = (TextView) row.findViewById(R.id.name);



            row.setTag(holder);
        }
        else {
            holder= (ProductAdapter.product)row.getTag();
        }


        ProductClass productClass= productClassList.get(position);

        holder.name.setText(productClass.getName());

        return row;
    }

    static class product{
        TextView name;
    }
}


