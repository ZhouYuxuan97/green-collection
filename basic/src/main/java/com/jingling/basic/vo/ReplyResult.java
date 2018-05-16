package com.jingling.basic.vo;


import com.jingling.basic.enums.ReplyCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.Optional;

/**
 *
 * @Description: 封装状态信息--status和message
 * @Auther: zyy-finalcola
 * @Date: 2018-01-25 18:31
 */
public class  ReplyResult {

    private Integer status;
    private String message;
    private Object data;

    public ReplyResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ReplyResult() {
    }

    public ReplyResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static String toJson(Integer code, String msg){
        Gson gson = new Gson();
        return gson.toJson(new ReplyResult(code, msg));
    }

    public static String toJson(ReplyCode replyCode){
        Gson gson = new Gson();
        return gson.toJson(new ReplyResult(replyCode.getCode(), replyCode.getMessage()));
    }

    public static String toJsonWithData(ReplyCode replyCode,Object data){
        ReplyResult replyResult = new ReplyResult(replyCode.getCode(), replyCode.getMessage(), data);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(replyResult);
    }

    public static ReplyResult fromJson(String json){
        Gson gson = new Gson();
        ReplyResult replyResult = null;
        try {
            replyResult = gson.fromJson(json, ReplyResult.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
        return replyResult;
    }

    /**
     * 返回的json字符串是否表示请求成功
     * @param json
     * @return
     */
    public static boolean isSuccess(String json) {
//        ReplyResult replyResult = fromJson(json);
//        Integer status = replyResult.status;
        Optional<ReplyResult> optional = Optional.ofNullable(fromJson(json));
        Optional<Integer> status = optional.map(ReplyResult::getStatus);
        Integer successStatus = Integer.valueOf(ReplyCode.OK.getCode());
        boolean result = successStatus.equals(status);
        return result;
    }

    public static String toJsonWithData(Object data){
        return toJsonWithData(ReplyCode.OK, data);
    }

    @Override
    public String toString() {
        return "ReplyResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * 测试代码，运行结果为: {"status":1,"message":"请上传图片"}
     */
    public static void main(String[] args) {
        String json = "{\"status\":0,\"message\":\"OK\"}";
        ReplyResult replyResult = ReplyResult.fromJson(json);
        System.out.println(replyResult);
    }

}