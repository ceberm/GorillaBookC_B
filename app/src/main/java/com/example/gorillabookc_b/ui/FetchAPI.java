package com.example.gorillabookc_b.ui;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchAPI extends AsyncTask<Integer, Integer, String> {
    @Override
    protected String doInBackground(Integer... params) {
        for (int count = 0; count <= params[0]; count++) {
            try {
                Thread.sleep(1000);

                publishProgress(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONArray response = getJSONObjectFromURL("https://gl-endpoint.herokuapp.com/feed");

            Log.d("", "doInBackground: " + response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Task Completed.";
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    }

    public static JSONArray getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;
        String result="";
        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream=urlConnection.getInputStream();
        InputStreamReader reader=new InputStreamReader(inputStream);

        BufferedReader br = new BufferedReader(reader);

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);
        urlConnection.disconnect();

        return new JSONArray(jsonString);
    }

    public static String getData(InputStreamReader reader) throws IOException{
        String result="";
        int data=reader.read();
        while (data!=-1){
            char now=(char) data;
            result+=data;
            data=reader.read();
        }
        return result;
    }

}