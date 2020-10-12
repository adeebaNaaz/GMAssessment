package com.gmassessment.model;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;

public class GitHubServiceImplementation {

    private static final String OWNER_NAME = "google";
    private static final String REPO_NAME = "dagger";

    private GitHubServiceInterface service;

    @Inject
    public GitHubServiceImplementation(GitHubServiceInterface service) {
        this.service = service;
    }

    public void getCommitList(@NonNull Callback<List<CommitDetail>> callback) {
        Call<List<CommitDetail>> call = service.getCommitList(OWNER_NAME, REPO_NAME);
        call.enqueue(callback);
    }
}
