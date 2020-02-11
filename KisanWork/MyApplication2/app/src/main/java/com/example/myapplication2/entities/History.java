package com.example.myapplication2.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class History {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int uid;

    @ColumnInfo(name = "name")
    public String Name;

    @ColumnInfo(name = "otp")
    public int otp;

    @ColumnInfo(name = "time")
    public String timestamp;

    public History()
    {

    }

    @Ignore
    public History(String name, int otp, String timestamp) {
        Name = name;
        this.otp = otp;
        this.timestamp = timestamp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
