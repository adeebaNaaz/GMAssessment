package com.gmassessment.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubServiceInterface {

    @GET("/repos/{owner}/{repo}/commits")
    Call<List<CommitDetail>> getCommitList(@Path("owner") String ownerName, @Path("repo") String repoName);
}
