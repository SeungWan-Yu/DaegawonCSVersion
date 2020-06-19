package com.example.daegawoncsversion.Object;

import java.util.ArrayList;

public class ReserveObject {

    public String toString(){
        return "name : " + name + ",phone : " + phone+", visitDt : "+visitDt+", cnt : "+cnt+", tnames : "+tnames;
    }

    private String keyCd;
    private String name;
    private String phone;
    private String visitDt;
    private String cnt;
    private ArrayList<String> tnames;

    public ReserveObject(String keyCd, String name, String phone, String visitDt, String cnt, ArrayList<String> tnames){
        this.keyCd=keyCd;
        this.name=name;
        this.phone=phone;
        this.visitDt=visitDt;
        this.cnt=cnt;
        this.tnames=tnames;
    }

    public String getKeyCd() {
        return keyCd;
    }

    public void setKeyCd(String keyCd) {
        this.keyCd = keyCd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVisitDt() {
        return visitDt;
    }

    public void setVisitDt(String visitDt) {
        this.visitDt = visitDt;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public ArrayList<String> getTnames() {
        return tnames;
    }

    public void setTnames(ArrayList<String> tnames) {
        this.tnames = tnames;
    }
}