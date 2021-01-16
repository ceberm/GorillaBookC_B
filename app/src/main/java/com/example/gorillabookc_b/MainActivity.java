package com.example.gorillabookc_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.gorillabookc_b.ui.FetchAPI;
import com.example.gorillabookc_b.ui.JSONAdapter;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    ListView lstTest;
    //Array Adapter that will hold our ArrayList and display the items on the ListView
    JSONAdapter jSONAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            String result = new FetchAPI().execute(1).get();

            setContentView(R.layout.activity_main);

            //Initialize ListView
            lstTest= (ListView)findViewById(R.id.list);

            //link the activity to the adapter
            jSONAdapter = new JSONAdapter(MainActivity.this, new JSONArray(result));

            //link the adapter to the list
            lstTest.setAdapter(jSONAdapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}