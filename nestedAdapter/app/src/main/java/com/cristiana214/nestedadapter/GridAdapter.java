package com.cristiana214.nestedadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cristiana214 on 27/07/2015.
 */
public class GridAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> result= new HashMap<String, String>();
    ViewHolder h;
    public GridAdapter(Context context1,ArrayList<HashMap<String, String>> datalist) {
        this.context = context1;
       data=datalist;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.grid_item, parent, false);
            h = new ViewHolder();
            result = data.get(position);
            h.txt_first = (TextView) view.findViewById(R.id.textfirt);
            h.txt_second = (TextView) view.findViewById(R.id.textsecond);
            view.setTag(h);
        }else h = (ViewHolder) view.getTag();
             setupValue();
        return view;
    }
    private void setupValue() {
        h.txt_first.setText(result.get("n1"));
        h.txt_second.setText(result.get("n2"));
    }
    static class ViewHolder {
        TextView txt_first;
        TextView txt_second;
    }
}
