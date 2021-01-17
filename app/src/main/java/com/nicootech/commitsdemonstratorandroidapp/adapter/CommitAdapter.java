package com.nicootech.commitsdemonstratorandroidapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.nicootech.commitsdemonstratorandroidapp.model.Commit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommitAdapter extends  RecyclerView.Adapter<CommitAdapter.MyViewHolder> {
    List<Commit> mCommits;
    private Context mContext;
    private int mLayout;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }

    public CommitAdapter(Context context, List<Commit> commits, int layout) {

    }


    @NonNull
    @Override
    public CommitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
