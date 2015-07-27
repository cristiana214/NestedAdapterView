package com.cristiana214.nestedadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cristiana214 on 27/07/2015.
 */
public class Adapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    GridAdapter gridadapter;
    HashMap<String, String> resultp = new HashMap<String, String>();
    JSONObject subobject;
    JSONArray subarray;
    GridView gridview;
    ArrayList<HashMap<String, String>> sublist;
    public Adapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
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
            view = inflater.inflate(R.layout.list_item, parent, false);
            resultp = data.get(position);
          gridview = (GridView) view.findViewById(R.id.gridView);
        sublist = new ArrayList<HashMap<String, String>>();
        try {
            // Locate the array name in JSON
            subarray = new JSONArray(resultp.get("factname"));
            for (int i = 0; i < subarray.length(); i++) {
                HashMap<String, String> each = new HashMap<String, String>();
                subobject = subarray.getJSONObject(i);
                each.put("n1", subobject.getString("n1"));
                each.put("n2", subobject.getString("n2"));
                sublist.add(each);
            }
        } catch (JSONException e) {
            Log.e("Error", e.getMessage());
        }
        gridadapter = new GridAdapter(context, sublist);
        gridview.setAdapter(gridadapter);
        int totalDividersHeight = 72 * (subarray.length() - 1);
        ViewGroup.LayoutParams params = gridview.getLayoutParams();
        params.height =  totalDividersHeight;
        gridview.setLayoutParams(params);
        gridview.requestLayout();
        return view;
    }

}
