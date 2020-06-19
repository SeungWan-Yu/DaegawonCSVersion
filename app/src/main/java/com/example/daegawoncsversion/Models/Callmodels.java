package com.example.daegawoncsversion.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Callmodels {

    @SerializedName("caheader")
    @Expose
    private Header header;

    @SerializedName("cabody")
    @Expose
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
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
}