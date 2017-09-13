package com.example.samuel.gitproject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Users>> {
    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private TextView errorText;
    private ArrayList<Users> users = new ArrayList<>();
    static int position;
    private static final int LOADER_ID = 0;
    private SwipeRefreshLayout refresh;
    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        bar = (ProgressBar) findViewById(R.id.progressbar);
        errorText = (TextView) findViewById(R.id.error_text);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                restartLoader();
            }
        });
        if (isConnected()) {
            getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            noInternetConnection();
        }
    }

    private void restartLoader() {
        getSupportLoaderManager().restartLoader(LOADER_ID,null,MainActivity.this);
        errorText.setVisibility(View.INVISIBLE);
    }


    @Override
    public Loader<ArrayList<Users>> onCreateLoader(int id, Bundle args) {
        bar.setVisibility(View.VISIBLE);
        return new UsersLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Users>> loader, ArrayList<Users> data) {
        refresh.setRefreshing(false);
        if (data != null) {
            if (!data.isEmpty()) {

                bar.setVisibility(View.INVISIBLE);
                if(users != null){
                users.clear();
                users.addAll(data);}
                else{
                 users = data;
                }
                adapter = new UsersAdapter(users, MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }
        }
        else{
            showError();
        }

    }

    private void noInternetConnection() {
        bar.setVisibility(View.GONE);
        errorText.setText(R.string.no_internet);
        errorText.setVisibility(View.VISIBLE);

    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    private void showError() {
        bar.setVisibility(View.GONE);
        errorText.setText(R.string.broken_connection);
        errorText.setVisibility(View.VISIBLE);


    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Users>> loader) {

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
