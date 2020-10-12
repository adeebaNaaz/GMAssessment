package com.gmassessment;

import com.gmassessment.model.GitHubServiceInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class CommitDetailModule {

    @Provides
    public GitHubServiceInterface provideGitHubServiceInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GitHubServiceInterface.class);
    }
}
