package com.jingling.basic.vo;

import com.google.gson.Gson;

public class DataResult <T> {
    private int code;
    private String message;
    private T data;

    public DataResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
