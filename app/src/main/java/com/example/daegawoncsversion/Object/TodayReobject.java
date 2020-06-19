package com.example.daegawoncsversion.Object;

public class TodayReobject {

    private String keyCd;
    private String visitDt;

    public TodayReobject(String keycode,String visitDt){
        this.keyCd = keycode;
        this.visitDt = visitDt;
    }


    public String getKeyCd() {
        return keyCd;
    }

    public void setKeyCd(String keyCd) {
        this.keyCd = keyCd;
    }

    public String getVisitDt() {
        return visitDt;
    }

    public void setVisitDt(String visitDt) {
        this.visitDt = visitDt;
    }
}