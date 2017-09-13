package com.example.samuel.gitproject;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Samuel on 12/09/2017.
 */

public class FetchUsers {

    private static final String API_URL = "https://api.github.com/search/users?q=location:lagos+language:java&per_page=30";

    public static URL createUrl() {

        URL url = null;
        try {
            url = new URL(API_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }


    public static String getJsonData() {
        URL url = createUrl();

        HttpURLConnection connection = null;
        InputStream stream = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            stream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            Log.d("buffer", "" + buffer.length());

            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {

                connection.disconnect();
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static ArrayList<Users> getUsers() {
        String jsonResponse = getJsonData();

        if (jsonResponse == null) {
            return null;
        }
        ArrayList<Users> users = new ArrayList<>();

        try {
            JSONObject rootObject = new JSONObject(jsonResponse);
            JSONArray rootArray = rootObject.getJSONArray("items");


            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject user = rootArray.getJSONObject(i);

                String user_name = user.getString("login");
                String photo = user.getString("avatar_url");
                String profile = user.getString("html_url");
                users.add(new Users(user_name, photo, profile));
            }
            return users;
        } catch (JSONException e) {
            e.printStackTrace();

        }
        Log.d("awesome", "" + users.size());

        return null;

    }
}
