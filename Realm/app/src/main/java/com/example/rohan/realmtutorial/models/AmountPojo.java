package com.example.rohan.realmtutorial.models;

import java.util.Date;

import io.realm.RealmObject;

public class AmountPojo extends RealmObject {
    Date date;
    String amount;
    public AmountPojo()
    {

    }

    public AmountPojo(Date date, String amount) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }




}
