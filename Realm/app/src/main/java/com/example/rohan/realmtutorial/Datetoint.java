package com.example.rohan.realmtutorial;

import java.util.Calendar;
import java.util.Date;

public class Datetoint {

    public static int returnmonthinnumber(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekOfYear = cal.get(Calendar.MONTH);
        return weekOfYear;
    }
}
