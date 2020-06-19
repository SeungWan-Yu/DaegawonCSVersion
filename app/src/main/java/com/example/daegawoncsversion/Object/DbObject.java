package com.example.daegawoncsversion.Object;

public class DbObject {
    DbObject(){

    }

    public DbObject(int waititng, String messege, int total, int current) {
        this.waititng = waititng;
        this.messege = messege;
        this.total = total;
        this.current = current;
    }

    private int waititng;
    private String messege;
    private int total;
    private int current;

    public int getWaititng() {
        return waititng;
    }

    public void setWaititng(int waititng) {
        this.waititng = waititng;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}