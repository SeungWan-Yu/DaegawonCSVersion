package com.example.daegawoncsversion.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Seatschmodels {

    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("body")
    @Expose
    private List<Body> body = null;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Body> getBody() {
        return body;
    }

    public void setBody(List<Body> body) {
        this.body = body;
    }

    public class Header {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("msg")
        @Expose
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }

    public class Body {

        @SerializedName("tname")
        @Expose
        private String tname;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("bookingCd")
        @Expose
        private String bookingCd;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("visitDt")
        @Expose
        private String visitDt;
        @SerializedName("cnt")
        @Expose
        private String cnt;

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBookingCd() {
            return bookingCd;
        }

        public void setBookingCd(String bookingCd) {
            this.bookingCd = bookingCd;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    }
}