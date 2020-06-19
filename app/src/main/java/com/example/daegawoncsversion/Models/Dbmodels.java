package com.example.daegawoncsversion.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dbmodels {

    @SerializedName("wlheader")
    @Expose
    private Header header;
    @SerializedName("wlbody")
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

        @SerializedName("keyCd")
        @Expose
        private String keyCd;
        @SerializedName("idx")
        @Expose
        private String idx;
        @SerializedName("cnt")
        @Expose
        private String cnt;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("valid")
        @Expose
        private String valid;
        @SerializedName("waitCnt")
        @Expose
        private String waitCnt;
        @SerializedName("dt")
        @Expose
        private String dt;

        public String getKeyCd() {
            return keyCd;
        }

        public void setKeyCd(String keyCd) {
            this.keyCd = keyCd;
        }

        public String getIdx() {
            return idx;
        }

        public void setIdx(String idx) {
            this.idx = idx;
        }

        public String getCnt() {
            return cnt;
        }

        public void setCnt(String cnt) {
            this.cnt = cnt;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public String getWaitCnt() {
            return waitCnt;
        }

        public void setWaitCnt(String waitCnt) {
            this.waitCnt = waitCnt;
        }

        public String getDt() {
            return dt;
        }

        public void setDt(String dt) {
            this.dt = dt;
        }

    }
}