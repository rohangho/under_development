package com.example.rohan.myapplication.presenter;

import com.example.rohan.myapplication.views.MainActivityMethods;

public class MainActivityPresenter {

    MainActivityMethods callMainActivity;

    public MainActivityPresenter(MainActivityMethods callMainActivity)
    {
        this.callMainActivity=callMainActivity;
    }

    public void callMain()
    {
        callMainActivity.onClickSignUp();
    }
}
