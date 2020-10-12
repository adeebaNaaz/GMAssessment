package com.gmassessment;

import com.gmassessment.view.MainActivity;

import dagger.Component;

@Component(modules = {CommitDetailModule.class})
public interface CommitDetailComponent {

     void inject(MainActivity mainActivity);
}
