package com.example.daegawoncsversion.Object;

public class CheckReobject {

    private String keyCd;
    private String bookingCd;

    public CheckReobject(String keycode, String bookingCd){
        this.keyCd = keycode;
        this.bookingCd = bookingCd;
    }


    public String getKeyCd() {
        return keyCd;
    }

    public void setKeyCd(String keyCd) {
        this.keyCd = keyCd;
    }

    public String getBookingCd() {
        return bookingCd;
    }

    public void setBookingCd(String bookingCd) {
        this.bookingCd = bookingCd;
    }
}