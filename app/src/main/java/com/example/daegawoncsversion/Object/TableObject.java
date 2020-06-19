package com.example.daegawoncsversion.Object;

public class TableObject {

    private String keyCd, tname, idx, phone;

    public TableObject(String keyCd, String tname, String idx, String phone){
        this.keyCd = keyCd;
        this.tname = tname;
        this.idx = idx;
        this.phone = phone;
    }

    public String getKeyCd() {
        return keyCd;
    }

    public void setKeyCd(String keyCd) {
        keyCd = keyCd;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String cnt) {
        this.idx = cnt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}