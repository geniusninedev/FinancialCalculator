package com.nineinfosys.financialcalculator.DashBord;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nineinfosys.financialcalculator.R;


/**
 * Created by Dev on 09-03-2017.
 */

public class NewsAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;



    public NewsAdapter(Context context,  int layoutResourceId) {
        super(context, layoutResourceId);
        this.context = context;
        this.layoutResourceId = layoutResourceId;


    }
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        final String currentItem = getItem(position);
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.row_list_category, parent, false);

        }
        row.setTag(currentItem);

        final TextView textViewCategory = (TextView)row.findViewById(R.id.textViewCategories);

        textViewCategory.setText(currentItem);



        return row;

    }
}