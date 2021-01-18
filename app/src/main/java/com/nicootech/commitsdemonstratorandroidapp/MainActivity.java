package com.nicootech.commitsdemonstratorandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nicootech.commitsdemonstratorandroidapp.adapter.CommitAdapter;
import com.nicootech.commitsdemonstratorandroidapp.model.Commit;
import com.nicootech.commitsdemonstratorandroidapp.network.AsyncTaskInterface;
import com.nicootech.commitsdemonstratorandroidapp.network.NetworkCall;
import com.nicootech.commitsdemonstratorandroidapp.network.NetworkTags;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncTaskInterface {
    private static final String TAG = "MainActivity";
    private ProgressDialog mProgressDialog;
    private List<Commit> mCommitList;
    private RecyclerView mRecyclerView;
    private CommitAdapter mCommitAdapter;

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
        mCommitAdapter = new CommitAdapter(this, mCommitList, R.layout.row_item);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCommitAdapter);
    }
    private void getCommitsData() {

        new NetworkCall(null, this, NetworkTags.COMMITS_URL).execute();
    }
    @Override
    public void onTaskCompleted(String response, String urlIdentifier) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {

            mProgressDialog.dismiss();
        }

        Log.d(TAG, "Responses : "+response);

        try {
            JSONArray jsonArray = new JSONArray(response);
            // Logic to fetch 25 commits only latest.
            for (int i=0;i<15;i++) {
                Commit newCommit = new Commit();
                newCommit.setmSha("Commit Hash: "+jsonArray.getJSONObject(i).getString("sha"));
                newCommit.setmAuthor("Author: "+jsonArray.getJSONObject(i).getJSONObject("commit").getJSONObject("author").getString("name"));
                newCommit.setmMessage("Message: "+jsonArray.getJSONObject(i).getJSONObject("commit").getString("message"));
                mCommitList.add(newCommit);
            }
            mCommitAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}