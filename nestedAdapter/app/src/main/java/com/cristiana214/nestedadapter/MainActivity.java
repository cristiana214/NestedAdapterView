package com.cristiana214.nestedadapter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by cristiana214 on 27/07/2015.
 */

public class MainActivity extends AppCompatActivity {
    JSONObject object;
    JSONArray array;
    ListView listview;
    Adapter adapter;
    ArrayList<HashMap<String, String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Downloadsub().execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class Downloadsub extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected  Void doInBackground(Void... params) {
            // Create an array
            list = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
           String strng ="[" +
                   "{\"factCount\":1,\"factName\":[{\"n1\":\"1name1\",\"n2\":\"name2\"},{\"n1\":\"name3\",\"n2\":\"name3\"},{\"n1\":\"name4\",\"n2\":\"name4\"}]}," +
                   "{\"factCount\":2,\"factName\":[{\"n1\":\"2name1\",\"n2\":\"name2\"},{\"n1\":\"name3\",\"n2\":\"name3\"},{\"n1\":\"name4\",\"n2\":\"name5\"}]}," +
                   "{\"factCount\":3,\"factName\":[{\"n1\":\"3name1\",\"n2\":\"name2\"},{\"n1\":\"name3\",\"n2\":\"name3\"},{\"n1\":\"name4\",\"n2\":\"name5\"},{\"n1\":\"name5\",\"n2\":\"name6\"}]}," +
                   "{\"factCount\":4,\"factName\":[{\"n1\":\"4name1\",\"n2\":\"name2\"},{\"n1\":\"name3\",\"n2\":\"name3\"},{\"n1\":\"name4\",\"n2\":\"name5\"}]}," +
                   "{\"factCount\":5,\"factName\":[{\"n1\":\"5name1\",\"n2\":\"name2\"},{\"n1\":\"name3\",\"n2\":\"name3\"},{\"n1\":\"name4\",\"n2\":\"name5\"},{\"n1\":\"name4\",\"n2\":\"name5\"}]}," +
                   "{\"factCount\":6,\"factName\":[{\"n1\":\"6name1\",\"n2\":\"name2\"},{\"n1\":\"name3\",\"n2\":\"name3\"},{\"n1\":\"name4\",\"n2\":\"name5\"},{\"n1\":\"namedf\",\"n2\":\"name56\"}]}," +
                   "{\"factCount\":7,\"factName\":[{\"n1\":\"7name1\",\"n2\":\"name2\"},{\"n1\":\"name3\",\"n2\":\"name3\"},{\"n1\":\"name4\",\"n2\":\"name5\"}]}," +
                   "{\"factCount\":8,\"factName\":[{\"n1\":\"8name1\",\"n2\":\"name2\"}]}," +
                   "{\"factCount\":9,\"factName\":[{\"n1\":\"9name1\",\"n2\":\"name2\"},{\"n1\":\"name3\",\"n2\":\"name3\"},{\"n1\":\"name4\",\"n2\":\"name5\"},{\"n1\":\"name4\",\"n2\":\"name5\"},{\"n1\":\"name4\",\"n2\":\"name5\"},{\"n1\":\"name4\",\"n2\":\"name5\"}]}" +
                   "]";
            try {
                    // Locate the array name in JSON
                    array = new JSONArray(strng);
                    for (int i = 0; i < array.length(); i++) {
                        HashMap<String, String> each = new HashMap<String, String>();
                        object = array.getJSONObject(i);
                        // Retrive JSON Objects
                        each.put("factcount", object.getString("factCount"));
                        each.put("factname", object.getString("factName"));
                        // Set the JSON Objects into the array
                       list.add(each);
                    }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void args) {
            listview = (ListView) findViewById(R.id.listView);
            adapter = new Adapter(MainActivity.this, list);
            listview.setAdapter(adapter);
        }
    }
}
