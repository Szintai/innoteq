package com.Innoteq.innoteq.model;

import java.time.Month;

public class YearAndMonth {

    private int id;

    private int year;

    private int month;

    public YearAndMonth() {
    }

    public YearAndMonth(int id, int year, int month) {
        this.id = id;
        this.year = year;
        this.month = month;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

}
