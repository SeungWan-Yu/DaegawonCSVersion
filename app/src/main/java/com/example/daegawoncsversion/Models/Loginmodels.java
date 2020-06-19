package com.example.daegawoncsversion.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loginmodels {

    @SerializedName("header")
    Header header;
    @SerializedName("body")
    Body body;

    public Header getHeader(){
        return header;
    }

    public Body getBody() {
        return body;
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
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("pw")
        @Expose
        private String pw;
        @SerializedName("type")
        @Expose
        private String type;

        public String getKeyCd() {
            return keyCd;
        }

        public void setKeyCd(String keyCd) {
            this.keyCd = keyCd;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPw() {
            return pw;
        }

        public void setPw(String pw) {
            this.pw = pw;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

}