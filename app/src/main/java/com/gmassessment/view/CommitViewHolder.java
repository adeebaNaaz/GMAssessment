package com.gmassessment.view;

import android.view.View;
import android.widget.TextView;

import com.gmassessment.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommitViewHolder extends RecyclerView.ViewHolder {
    public TextView authorName;
    public TextView commitHash;
    public TextView commitMessage;

    public CommitViewHolder(@NonNull View itemView) {
        super(itemView);

        authorName = itemView.findViewById(R.id.textView1);
        commitHash = itemView.findViewById(R.id.textView2);
        commitMessage = itemView.findViewById(R.id.textView3);
    }
}
