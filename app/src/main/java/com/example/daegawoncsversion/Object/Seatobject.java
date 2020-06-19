package com.example.daegawoncsversion.Object;

public class Seatobject {

    private String keyCd, idx, phone;

    public String getKeyCd() {
        return keyCd;
    }

    public void setKeyCd(String keyCd) {
        this.keyCd = keyCd;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Seatobject(String keycode, String idx, String phone){
        this.keyCd = keycode;
        this.idx = idx;
        this.phone = phone;
    }

    public String getKeycode() {
        return keyCd;
    }

    public void setKeycode(String keycode) {
        this.keyCd = keycode;
    }
}