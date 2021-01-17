package com.nicootech.commitsdemonstratorandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.nicootech.commitsdemonstratorandroidapp.model.Commit;
import com.nicootech.commitsdemonstratorandroidapp.network.AsyncTaskInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncTaskInterface {
    private static final String TAG = "MainActivity";
    private ProgressDialog mProgressDialog;
    private List<Commit> mCommitList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //commit change test
    }
    @Override
    public void onTaskCompleted(String response, String urlIdentifier) {

    }
}