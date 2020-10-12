package com.gmassessment.presenter;

import com.gmassessment.model.CommitDetail;
import com.gmassessment.model.GitHubServiceImplementation;
import com.gmassessment.view.CommitView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {

    private final GitHubServiceImplementation gitHubServiceImplementation;
    private CommitView commitView;

    @Inject
    public MainActivityPresenter(GitHubServiceImplementation gitHubServiceImplementation){
        this.gitHubServiceImplementation = gitHubServiceImplementation;
    }

    public void setCommitView(CommitView commitView) {
        this.commitView = commitView;
    }

    public void getCommitList() {
        commitView.showProgress();
        gitHubServiceImplementation.getCommitList(new Callback<List<CommitDetail>>() {
            @Override
            public void onResponse(Call call, Response response) {
                commitView.hideProgress();
                handleResponse(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                commitView.hideProgress();
                commitView.showFailure(t.getLocalizedMessage());
            }
        });
    }

    private void handleResponse(Response response) {
        if(response.isSuccessful()) {
            List<CommitDetail> commitDetailList = (List<CommitDetail>) response.body();
            commitView.setCommitDetails(commitDetailList);
        } else {
            commitView.showFailure(response.message());
        }
    }
}
