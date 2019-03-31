package com.example.mat.utils;

import com.example.mat.model.TimeDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DiffernceTime {

    public static Long differenceBetweenTime(ArrayList<TimeDetails> details) throws ParseException {
        long differentInMilli = 0;

        for (int i = 0; i < details.size() - 1; i++) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
            Date start = simpleDateFormat.parse(details.get(i).getDate() + "/" + (details.get(i).getMonth() + 1) + "/" + "2019"
                    + " " + details.get(i).getHours() + ":" + details.get(i).getMinutes() + ":" + details.get(i).getSeconds());

            Date end = simpleDateFormat.parse(details.get(i + 1).getDate() + "/" + (details.get(i + 1).getMonth() + 1) + "/" + "2019"
                    + " " + details.get(i + 1).getHours() + ":" + details.get(i + 1).getMinutes() + ":" + details.get(i + 1).getSeconds());
            differentInMilli = end.getTime() - start.getTime();

        }
        return differentInMilli;
    }
}
