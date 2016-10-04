package com.example.prakash.prakash;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prakash.prakash.Database.CategoryClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prakash on 10/4/16.
 */

public class CategoryAdapter extends ArrayAdapter<CategoryClass>{
    Context context;
    int resource_id;


    List<CategoryClass> categoryClassList= new ArrayList<>();

    public CategoryAdapter(Context context, int resource_id, List<CategoryClass> categoryClassList) {
        super(context, resource_id,categoryClassList);
        this.context = context;
        this.resource_id = resource_id;
        this.categoryClassList = categoryClassList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row= convertView;
        category holder = null;

        if (row ==null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(resource_id,parent,false);

            holder = new category();
            holder.name = (TextView) row.findViewById(R.id.name);


            row.setTag(holder);
        }
        else {
            holder= (category)row.getTag();
        }


        CategoryClass categoryClass= categoryClassList.get(position);

        holder.name.setText(categoryClass.getName());


        return row;
    }

    static class category{
        TextView name;
    }
}
