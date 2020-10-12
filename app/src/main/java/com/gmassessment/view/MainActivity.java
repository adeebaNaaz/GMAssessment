package com.gmassessment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gmassessment.CommitDetailComponent;

import com.gmassessment.DaggerCommitDetailComponent;
import com.gmassessment.R;
import com.gmassessment.model.CommitDetail;
import com.gmassessment.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements CommitView {

    private ProgressBar progressBar;
    @Inject
    CommitAdaptor commitAdaptor;
    @Inject
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectDependency();
        initializeViews();
        mainActivityPresenter.setCommitView(this);
    }

    private void initializeViews() {
        progressBar = findViewById(R.id.indeterminate_progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(commitAdaptor);
        commitAdaptor.updateCommitList(new ArrayList<>());
        recyclerView.setHasFixedSize(true);
    }

    private void injectDependency() {
        CommitDetailComponent commitDetailComponent = DaggerCommitDetailComponent
                .builder()
                .build();
        commitDetailComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityPresenter.getCommitList();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void setCommitDetails(List<CommitDetail> commitDetailList) {
        commitAdaptor.updateCommitList(commitDetailList);
    }

    @Override
    public void showFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}