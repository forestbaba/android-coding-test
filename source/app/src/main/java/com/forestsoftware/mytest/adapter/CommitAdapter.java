package com.forestsoftware.mytest.adapter;

/**
 * Created by HP-PC on 6/13/2018.
 */


import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forestsoftware.mytest.R;
import com.forestsoftware.mytest.model.Author;
import com.forestsoftware.mytest.model.Commit;
import com.forestsoftware.mytest.model.Committer;
import com.forestsoftware.mytest.model.MainResponse;
import com.forestsoftware.mytest.rest.ApiInterface;


import java.util.List;


public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.MyViewHolder> {

    private final String TAG = Message.class.getSimpleName();
    private Context context;
    private List<MainResponse> commitList;
    private ApiInterface service;
    private View rView;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author, commit,commitmessage;

        public MyViewHolder(View view) {
            super(view);
            rView = view;
            author = view.findViewById(R.id.author);
            commit = view.findViewById(R.id.commit);
            commitmessage = view.findViewById(R.id.commit_message);
        }
    }


    public CommitAdapter(Context context, List<MainResponse> commitList) {
        this.context = context;
        this.commitList = commitList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.commit_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MainResponse response = commitList.get(position);

        Committer committer = response.getCommitter();

        Author author = response.getAuthor();
        Commit commit = response.getCommit();

        holder.author.setText("Name: "+commit.getAuthor().getName());
        holder.commit.setText("\nCommit: "+commit.getTree().getSha());
        holder.commitmessage.setText("\nCommit Message: "+commit.getMessage());

    }

    @Override
    public int getItemCount() {
        return commitList.size();
    }
}