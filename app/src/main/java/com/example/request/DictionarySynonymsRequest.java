package com.example.request;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DictionarySynonymsRequest extends AsyncTask<String,Integer,String> {

    Context context;
    TextView showSyn;

    public DictionarySynonymsRequest(Context context, TextView textView) {
        this.context = context;
        showSyn = textView;
    }
    String myurl;

    @Override
    protected String doInBackground(String... params) {

        final String app_id = "7f7c8975";
        final String app_key = "56cea4b33d2f883b64ca3d4e70b41e8e";
        myurl = params[0];
        try {
            URL url = new URL(myurl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        String syn;
        try {
            JSONObject js = new JSONObject(s);
            JSONArray results = js.getJSONArray("results");

            JSONObject lEntries = results.getJSONObject(0);
            JSONArray laArray = lEntries.getJSONArray("lexicalEntries");

            JSONObject entries = laArray.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");

            JSONObject sy = sensesArray.getJSONObject(0);
            JSONArray syno = sy.getJSONArray("synonyms");


            syn = syno.getString(0);
            showSyn.setText("Synonyms: " + "\n" + syn);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
