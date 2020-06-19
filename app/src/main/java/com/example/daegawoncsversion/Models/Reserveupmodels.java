package com.example.daegawoncsversion.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reserveupmodels {

    @SerializedName("cbheader")
    @Expose
    private Waitingmodels.Header header;

    @SerializedName("cbbody")
    @Expose
    private Waitingmodels.Body body;

    public Waitingmodels.Header getHeader() {
        return header;
    }

    public void setHeader(Waitingmodels.Header header) {
        this.header = header;
    }

    public Waitingmodels.Body getBody() {
        return body;
    }

    public void setBody(Waitingmodels.Body body) {
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

        public String getBookingCd() {
            return bookingCd;
        }

        public void setBookingCd(String bookingCd) {
            this.bookingCd = bookingCd;
        }
    }
}