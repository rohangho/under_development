package com.example.mat.model;

public class TimeDetails {
    int date;
    int hours;
    int minutes;
    int month;
    int seconds;

    public TimeDetails(int date, int hours, int minutes, int month, int seconds) {
        this.date = date;
        this.hours = hours;
        this.minutes = minutes;
        this.month = month;
        this.seconds = seconds;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
