package com.example.gorillabookc_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.gorillabookc_b.ui.FetchAPI;
import com.example.gorillabookc_b.ui.JSONAdapter;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    //Array Adapter that will hold our ArrayList and display the items on the ListView
    JSONAdapter jSONAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize ListView
        listView= (ListView)findViewById(R.id.list);

        //link the activity to the adapter
        jSONAdapter = new JSONAdapter(MainActivity.this);

        //link the adapter to the list
        listView.setAdapter(jSONAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Send the Thread to retrieve the list of objects
        new FetchAPI().execute(1);
    }
}