package com.gmassessment.presenter;

import com.gmassessment.model.CommitDetail;
import com.gmassessment.model.GitHubServiceImplementation;
import com.gmassessment.view.CommitView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.http.RealResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainActivityPresenterTest {

    @Mock
    private GitHubServiceImplementation mockGitHubServiceImplementation;
    @Mock
    private CommitView mockCommitView;
    @Mock
    private Call mockCall;
    @Captor
    private ArgumentCaptor<Callback<List<CommitDetail>>> argumentCaptor;
    @InjectMocks
    private MainActivityPresenter subject;

    @Before
    public void setUp() {
        initMocks(this);
        subject.setCommitView(mockCommitView);
    }

    @Test
    public void getCommitList_should_show_progress_and_call_gitHubServiceImplementation_getCommitList_and_handle_success_response() {
        List<CommitDetail> commitDetailList = getCommitDetailList();
        Response mockSuccessResponse = Response.success(commitDetailList);
        subject.getCommitList();

        verify(mockCommitView).showProgress();
        verify(mockGitHubServiceImplementation).getCommitList(argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(mockCall, mockSuccessResponse);

        verify(mockCommitView).hideProgress();
        verify(mockCommitView).setCommitDetails(commitDetailList);
    }

    @Test
    public void getCommitList_should_show_progress_and_call_gitHubServiceImplementation_getCommitList_and_handle_failure_response() {
        RealResponseBody responseBody = new RealResponseBody("someContentType", 0L, null);
        Response mockErrorResponse = Response.error(500, responseBody);

        subject.getCommitList();

        verify(mockCommitView).showProgress();
        verify(mockGitHubServiceImplementation).getCommitList(argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(mockCall, mockErrorResponse);

        verify_failure("Response.error()");
    }

    @Test
    public void getCommitList_should_show_progress_and_call_gitHubServiceImplementation_getCommitList_and_handle_onFailure_response() {
        subject.getCommitList();

        verify(mockCommitView).showProgress();
        verify(mockGitHubServiceImplementation).getCommitList(argumentCaptor.capture());

        argumentCaptor.getValue().onFailure(mockCall, new Exception("Network Error"));

        verify_failure("Network Error");
    }

    private List<CommitDetail> getCommitDetailList() {
        CommitDetail commitDetail1 = mock(CommitDetail.class);
        CommitDetail commitDetail2 = mock(CommitDetail.class);
        CommitDetail commitDetail3 = mock(CommitDetail.class);
        List<CommitDetail> commitDetailList = new ArrayList<>();
        commitDetailList.add(commitDetail1);
        commitDetailList.add(commitDetail2);
        commitDetailList.add(commitDetail3);
        return commitDetailList;
    }

    private void verify_failure(String message) {
        verify(mockCommitView).hideProgress();
        verify(mockCommitView).showFailure(message);
    }
}