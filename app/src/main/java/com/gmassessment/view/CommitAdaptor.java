package com.gmassessment.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmassessment.R;
import com.gmassessment.model.CommitDetail;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommitAdaptor extends RecyclerView.Adapter<CommitViewHolder> {
    private List<CommitDetail> commitDetailList;

    @Inject
    public CommitAdaptor() {
    }

    public void updateCommitList(List<CommitDetail> commitDetailList) {
        this.commitDetailList = commitDetailList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_layout, parent, false);
        return new CommitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitViewHolder holder, int position) {
        CommitDetail commitDetail = commitDetailList.get(position);
        holder.authorName.setText(commitDetail.commit.author.name);
        holder.commitHash.setText(commitDetail.sha);
        holder.commitMessage.setText(commitDetail.commit.message);
    }

    @Override
    public int getItemCount() {
        return commitDetailList.size();
    }
}
