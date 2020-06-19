package com.example.daegawoncsversion.Object;

public class Loginobject {
    public Loginobject() {
    }

    private String keyCd;
    private String id;
    private String pw;

    public Loginobject(String id, String pw,String keycode){
        this.keyCd=keycode;
        this.id=id;
        this.pw=pw;
    }
    public String getKeycode() {
        return keyCd;
    }

    public void setKeycode(String keycode) {
        this.keyCd = keycode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}