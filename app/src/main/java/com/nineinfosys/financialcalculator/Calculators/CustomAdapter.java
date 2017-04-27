package com.nineinfosys.financialcalculator.Calculators;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nineinfosys.financialcalculator.R;

import java.util.ArrayList;

/**
 * Created by Dev on 01-04-2017.
 */
public class CustomAdapter extends BaseAdapter
{
    private ArrayList<String> listCalculator;
    private ArrayList<Integer> listCount;
    private Activity activity;

    public CustomAdapter(Activity activity, ArrayList<String> listCalculator, ArrayList<Integer> listCount) {
        super();
        this.listCalculator = listCalculator;
        this.listCount = listCount;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listCalculator.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return listCalculator.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView txtViewTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.mylist, null);

            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);
            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);

            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }

        view.txtViewTitle.setText(listCalculator.get(position));
        view.imgViewFlag.setImageResource(listCount.get(position));

        return convertView;
    }

}