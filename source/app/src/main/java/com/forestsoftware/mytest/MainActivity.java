package com.forestsoftware.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.forestsoftware.mytest.adapter.CommitAdapter;
import com.forestsoftware.mytest.model.MainResponse;
import com.forestsoftware.mytest.rest.ApiClient;
import com.forestsoftware.mytest.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiInterface service;
    private List<MainResponse> commitList;
    private RecyclerView commitRecycler;
    private CommitAdapter commitAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commitRecycler = findViewById(R.id.commit_recycler);
        loadCommit();
    }


    private void loadCommit() {
        service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<MainResponse>> userCall = service.getCurrent();
        userCall.enqueue(new Callback<List<MainResponse>>() {

            @Override
            public void onResponse(Call<List<MainResponse>> call,
                                   Response<List<MainResponse>> response) {


                if (response.isSuccessful()) {
                    commitList  = response.body();


                    commitAdapter = new CommitAdapter(MainActivity.this, commitList);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
                    commitRecycler.setLayoutManager(mLayoutManager);
                    commitRecycler.setItemAnimator(new DefaultItemAnimator());
                    commitRecycler.setAdapter(commitAdapter);

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<MainResponse>> call, Throwable t) {
                Log.wtf(TAG, "Error: " + t);

            }
        });
    }
}
