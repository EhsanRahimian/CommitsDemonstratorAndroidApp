package com.nicootech.commitsdemonstratorandroidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicootech.commitsdemonstratorandroidapp.R;
import com.nicootech.commitsdemonstratorandroidapp.model.Commit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommitAdapter extends  RecyclerView.Adapter<CommitAdapter.MyViewHolder> {
    List<Commit> mCommits;
    private Context mContext;
    private int mLayout;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView sha;
        public TextView author_name;
        public TextView message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sha = itemView.findViewById(R.id.sha);
            author_name = itemView.findViewById(R.id.author_name);
            message = itemView.findViewById(R.id.message);
        }

    }

    public CommitAdapter(Context context, List<Commit> commits, int layout) {
        this.mCommits = commits;
        this.mContext = context;
        mLayout = layout;
        LayoutInflater mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CommitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayout,parent,false);
        return new CommitAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.sha.setText(mCommits.get(position).getmSha());
        holder.author_name.setText(mCommits.get(position).getmAuthor());
        holder.message.setText(mCommits.get(position).getmMessage());

    }

    @Override
    public int getItemCount() {
        return mCommits.size();
    }


}
