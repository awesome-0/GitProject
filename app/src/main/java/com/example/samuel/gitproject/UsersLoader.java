package com.example.samuel.gitproject;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

/**
 * Created by Samuel on 12/09/2017.
 */

public class UsersLoader extends AsyncTaskLoader<ArrayList<Users>> {
    public UsersLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Users> loadInBackground() {
        return FetchUsers.getUsers();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
