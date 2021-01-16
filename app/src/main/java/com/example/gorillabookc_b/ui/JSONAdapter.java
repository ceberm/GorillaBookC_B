package com.example.gorillabookc_b.ui;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorillabookc_b.MainActivity;
import com.example.gorillabookc_b.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONAdapter extends BaseAdapter implements ListAdapter {

    private final Activity activity;
    private JSONArray jsonArray = null;

    public JSONAdapter(Activity activity, JSONArray jsonArray) {
        assert activity != null;
        this.activity = activity;
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        if(jsonArray == null) return 0;
        else
            return jsonArray.length();
    }

    @Override
    public JSONObject getItem(int position) {
        if(jsonArray == null) return null;
        else
            return jsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        JSONObject jsonObject = getItem(position);

        return jsonObject.optLong("id");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);



        TextView text =(TextView)convertView.findViewById(R.id.txtAlertText);

        JSONObject json_data = getItem(position);
        if(null!=json_data ){
            String jj= null;
            try {
                jj = json_data.getString("first_name");
                jj += " " + json_data.getString("last_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            text.setText(jj);
        }

        return convertView;
    }
}
