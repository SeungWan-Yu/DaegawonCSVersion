package com.example.daegawoncsversion.Object;

public class KakaoObject {

    private String keyCd, phone, cnt;

    public KakaoObject(String keyCd, String phone, String cnt){
        this.keyCd = keyCd;
        this.phone = phone;
        this.cnt = cnt;
    }

    public String getKeyCd() {
        return keyCd;
    }

    public void setKeyCd(String keyCd) {
        keyCd = keyCd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
}