package com.jingling.userservice.constant;

/**
 * @Description: 微信常量
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-31 09:42
 * @Since: version 1.0
 **/
public final class WxConstants {

    public static final String APPID = "wx3aacff44502eddf9";
    public static final String APPSECRET = "340e733410b970771c8ac579a727e1d5";

    //授权
    public static final String AUTH_BASE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?";
    //获取token
    public static final String ACCESS_TOKEN_BASE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";
    //获取用户信息
    public static final String INFO_BASE_URL = "https://api.weixin.qq.com/sns/userinfo?";
    //回调
    public static final String REDIRECT_URL = "http://wx.monkeypaixiaoxin.cn/user/wx/callBack";
    //允许的范围
    public static final String SCOPE = "snsapi_userinfo";
    //token
    public static final String TOKEN = "zhangxiaoxin";

    //微信请求跳转到的登录页面
    public static final String WECHAT_LOGIN = "http://1l8u025289.iok.la:12206/green-collection/html/wechat/login_wechat.html";
    //微信授权成功的页面
    public static final String SUCCESS_INDEX = "http://1l8u025289.iok.la:12206/green-collection/html/wechat/index.html";
    //注册成功后的过渡页面
    public static final String REGISTER_SUCCESS = "http://1l8u025289.iok.la:12206/green-collection/html/wechat/register_success.html";
    //激活成功后的过渡页面
    public static final String VALIDATE_SUCCESS = "http://1l8u025289.iok.la:12206/green-collection/html/wechat/validate_success.html";

    private WxConstants(){}


}
