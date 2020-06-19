package com.example.daegawoncsversion.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Relistmodels{

    @SerializedName("lheader")
    @Expose
    private Header header;
    @SerializedName("lbody")
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

        @SerializedName("bookingCd")
        @Expose
        private String bookingCd;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("visitDt")
        @Expose
        private String visitDt;
        @SerializedName("cnt")
        @Expose
        private String cnt;
        @SerializedName("tnames")
        @Expose
        private List<String> tnames = null;

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

        public List<String> getTnames() {
            return tnames;
        }

        public void setTnames(List<String> tnames) {
            this.tnames = tnames;
        }

    }
}