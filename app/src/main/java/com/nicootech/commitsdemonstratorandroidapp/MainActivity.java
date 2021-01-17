package com.nicootech.commitsdemonstratorandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.nicootech.commitsdemonstratorandroidapp.adapter.CommitAdapter;
import com.nicootech.commitsdemonstratorandroidapp.model.Commit;
import com.nicootech.commitsdemonstratorandroidapp.network.AsyncTaskInterface;
import com.nicootech.commitsdemonstratorandroidapp.network.NetworkCall;
import com.nicootech.commitsdemonstratorandroidapp.network.NetworkTags;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncTaskInterface {
    private static final String TAG = "MainActivity";
    private ProgressDialog mProgressDialog;
    private List<Commit> mCommitList;
    private RecyclerView mRecyclerView;
    private CommitAdapter mCommitListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }
    private void initializeViews() {
        findViewById(R.id.btn_fetch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCommitsData();
            }
        });

        //Adapter initializing
        mCommitList = new ArrayList<>();
        mCommitListAdapter = new CommitAdapter(this, mCommitList, R.layout.row_item);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCommitListAdapter);
    }
    private void getCommitsData() {

        new NetworkCall(null, this, NetworkTags.COMMITS_URL).execute();
    }
    @Override
    public void onTaskCompleted(String response, String urlIdentifier) {

    }
}