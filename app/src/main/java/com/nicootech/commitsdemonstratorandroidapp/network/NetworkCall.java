package com.nicootech.commitsdemonstratorandroidapp.network;

import android.util.Log;

import com.android.volley.BuildConfig;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nicootech.commitsdemonstratorandroidapp.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

public class NetworkCall {
    private static final String TAG = "NetworkCall";
    int TIMEOUT_MILLISEC = 30000;
    private JSONObject mJson;
    private AsyncTaskInterface mAsyncTaskInterface;
    private String mUrl;
    private String mCallingId;

    public NetworkCall(JSONObject json, AsyncTaskInterface asyncTaskInterface, String url) {
        mJson = json;
        mAsyncTaskInterface = asyncTaskInterface;
        mUrl = url;
    }

    public void execute() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "Response : " + response.toString());
                        }

                        if (mAsyncTaskInterface != null) {
                            mAsyncTaskInterface.onTaskCompleted(response.toString(), mUrl);
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "Response : " + error.toString());
                        }

                        if (mAsyncTaskInterface != null) {
                            mAsyncTaskInterface.onTaskCompleted(error.toString(), mUrl);
                        }
                    }
                }
        );

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MILLISEC,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

}


