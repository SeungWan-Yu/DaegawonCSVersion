package com.example.daegawoncsversion.Object;

public class RePhoneObject {
    public RePhoneObject() {
    }

    private String keyCd;
    private String phone;

    public RePhoneObject(String keycode, String phone){
        this.keyCd = keycode;
        this.phone = phone;
    }

    public String getKeyCd() {
        return keyCd;
    }

    public void setKeyCd(String keyCd) {
        this.keyCd = keyCd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}