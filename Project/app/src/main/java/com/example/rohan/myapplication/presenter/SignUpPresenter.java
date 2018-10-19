package com.example.rohan.myapplication.presenter;

import com.example.rohan.myapplication.views.SignUpMethods;

public class SignUpPresenter {

    SignUpMethods signUpMethod;

    public SignUpPresenter(SignUpMethods signUpMethod)
    {
        this.signUpMethod=signUpMethod;
    }
    public void CallPlus()
    {
        signUpMethod.onClickPlus();
    }
    public void CallSignUp()
    {
        signUpMethod.onClickSingUp();
    }
}
