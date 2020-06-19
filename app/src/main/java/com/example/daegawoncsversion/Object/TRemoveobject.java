package com.example.daegawoncsversion.Object;

public class TRemoveobject {

    private String keyCd, tname;

    public TRemoveobject(String keycode, String tname){
        this.keyCd = keycode;
        this.tname = tname;
    }
    public String getKeycode() {
        return keyCd;
    }

    public void setKeycode(String keycode) {
        this.keyCd = keycode;
    }
    public String getTnum() {
        return tname;
    }

    public void setTnum(String tname) {
        this.tname = tname;
    }
}