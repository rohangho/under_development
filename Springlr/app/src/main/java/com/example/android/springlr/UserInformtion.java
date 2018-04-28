package com.example.android.springlr;

public class UserInformtion {

    String ageofthecurrent;
    String emailofthecurrent;
    String nameofthecurrent;
    public UserInformtion()
    {}

    public UserInformtion(String ageofthecurrent, String emailofthecurrent, String nameofthecurrent) {
        this.ageofthecurrent = ageofthecurrent;
        this.emailofthecurrent = emailofthecurrent;
        this.nameofthecurrent = nameofthecurrent;
    }

    public String getAgeofthecurrent() {
        return ageofthecurrent;
    }

    public void setAgeofthecurrent(String ageofthecurrent) {
        this.ageofthecurrent = ageofthecurrent;
    }

    public String getEmailofthecurrent() {
        return emailofthecurrent;
    }

    public void setEmailofthecurrent(String emailofthecurrent) {
        this.emailofthecurrent = emailofthecurrent;
    }

    public String getNameofthecurrent() {
        return nameofthecurrent;
    }

    public void setNameofthecurrent(String nameofthecurrent) {
        this.nameofthecurrent = nameofthecurrent;
    }
}


