package com.example.daegawoncsversion.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountModels {

    @SerializedName("wheader")
    @Expose
    private Header header;
    @SerializedName("wbody")
    @Expose
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
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

        @SerializedName("waitCnt")
        @Expose
        private String waitCnt;

        public String getWaitCnt() {
            return waitCnt;
        }

        public void setWaitCnt(String waitCnt) {
            this.waitCnt = waitCnt;
        }

    }

}