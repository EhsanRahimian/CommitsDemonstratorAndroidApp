package com.nicootech.commitsdemonstratorandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.nicootech.commitsdemonstratorandroidapp.adapter.CommitAdapter;
import com.nicootech.commitsdemonstratorandroidapp.model.Commit;
import com.nicootech.commitsdemonstratorandroidapp.network.AsyncTaskInterface;
import com.nicootech.commitsdemonstratorandroidapp.network.NetworkCall;
import com.nicootech.commitsdemonstratorandroidapp.network.NetworkTags;
import com.nicootech.commitsdemonstratorandroidapp.utils.Utility;

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
        if (internetConnectionCheck(MainActivity.this)) {


              final Toast toast =Toast.makeText(getApplicationContext(), "Internet Connection is available !!!", Toast.LENGTH_LONG);
              toast.show();
              Handler handler = new Handler();
              handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }

              }, 1200);
              initializeViews();

        } else

        {
            Toast.makeText(getApplicationContext(), "No Internet Connection Try Later !", Toast.LENGTH_LONG).show();
        }

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

        mProgressDialog = Utility.showProgressDialog(this, getString(R.string.fetching_commits));
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
            for (int i=0;i<25;i++) {
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
    public static boolean internetConnectionCheck(Activity CurrentActivity) {
        Boolean Connected = false;
        ConnectivityManager connectivity = (ConnectivityManager) CurrentActivity.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) for (int i = 0; i < info.length; i++)
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    Log.e("My Network is: ", "Connected");
                    Connected = true;
                } else {}
        } else {
            Log.e("My Network is: ", "Not Connected");

            Toast.makeText(CurrentActivity.getApplicationContext(),
                    "Please Check Your internet connection",
                    Toast.LENGTH_LONG).show();
            Connected = false;

        }
        return Connected;
    }
}