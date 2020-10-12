package com.gmassessment.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GitHubServiceImplementationTest {

    private static final String GOOGLE_OWNER_NAME = "google";
    private static final String DAGGER_REPO_NAME = "dagger";

    @Mock
    private GitHubServiceInterface mockGitHubServiceInterface;
    @Mock
    private Call<List<CommitDetail>> mockCall;
    @Mock
    private Callback<List<CommitDetail>> mockCallback;
    @InjectMocks
    private GitHubServiceImplementation subject;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void subject_should_getCommitList_and_enqueue_response_callback() {
        when(mockGitHubServiceInterface.getCommitList(GOOGLE_OWNER_NAME, DAGGER_REPO_NAME)).thenReturn(mockCall);

        subject.getCommitList(mockCallback);

        verify(mockGitHubServiceInterface).getCommitList(GOOGLE_OWNER_NAME, DAGGER_REPO_NAME);
        verify(mockCall).enqueue(mockCallback);
    }
}
