package com.example.daegawoncsversion.Object;

public class Keyobject {
    public Keyobject() {
    }

    private String keyCd;

    public Keyobject(String keycode){
        this.keyCd = keycode;
    }

    public String getKeycode() {
        return keyCd;
    }

    public void setKeycode(String keycode) {
        this.keyCd = keycode;
    }
}