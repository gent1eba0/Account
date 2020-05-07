package com.example.a23534.account;

import java.util.Comparator;

/**
 * Created by 23534 on 2020/4/28.
 */

public class Info implements Comparator<Info> {
     String id;
     String date;
     double money;
     String place;
     String whichway;
     String classify;

    @Override
    public String toString() {
        return id + " " + date + " " + money + " " + place + " " + whichway + " " + classify ;
    }
    public Info(String id, String date, double money, String place ,String whichway ,String classify){
        this.id = id;
        this.date = date;
        this.money = money;
        this.place = place;
        this.whichway = whichway;
        this.classify = classify;
    }
    @Override
    public int compare(Info o1, Info o2) {
        return 0;
    }
}
