package com.gmassessment.view;

import com.gmassessment.model.CommitDetail;

import java.util.List;

public interface CommitView {

    void showProgress();

    void hideProgress();

    void setCommitDetails(List<CommitDetail> commitDetailList);

    void showFailure(String message);
}
