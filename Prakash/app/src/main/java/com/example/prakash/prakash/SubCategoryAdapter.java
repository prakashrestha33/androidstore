package com.example.prakash.prakash;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prakash.prakash.Database.CategoryClass;
import com.example.prakash.prakash.Database.SubCategoryClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prakash on 10/4/16.
 */
public class SubCategoryAdapter extends ArrayAdapter<SubCategoryClass> {
    Context context;
    int resource_id;


    List<SubCategoryClass> subcategoryClassList= new ArrayList<>();

    public SubCategoryAdapter(Context context, int resource_id, List<SubCategoryClass> subcategoryClassList) {
        super(context, resource_id,subcategoryClassList);
        this.context = context;
        this.resource_id = resource_id;
        this.subcategoryClassList = subcategoryClassList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row= convertView;
        SubCategoryAdapter.subcategory holder = null;

        if (row ==null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            row = layoutInflater.inflate(resource_id,parent,false);

            holder = new SubCategoryAdapter.subcategory();
            holder.name = (TextView) row.findViewById(R.id.name);

            row.setTag(holder);
        }
        else {
            holder= (SubCategoryAdapter.subcategory)row.getTag();
        }


        SubCategoryClass subcategoryClass= subcategoryClassList.get(position);

        holder.name.setText(subcategoryClass.getName());

        return row;
    }

    static class subcategory{
        TextView name;
    }
}











