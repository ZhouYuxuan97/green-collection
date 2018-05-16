package com.jingling.userservice.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jingling.basic.po.Address;
import com.jingling.basic.po.User;
import com.jingling.basic.vo.RecycleResult;
import com.jingling.userservice.constant.WxConstants;
import com.jingling.userservice.mapper.AddressMapper;
import com.jingling.userservice.mapper.UserMapper;
import com.jingling.userservice.service.UserService;
import com.jingling.userservice.service.remote.MailFeignClient;
import com.jingling.userservice.util.HttpClientUtil;
import com.jingling.userservice.util.SignUtil;
import com.jingling.userservice.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: 用户业务实现
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-29 16:07
 * @Since: version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private static final String TITLE = "环保资源注册验证";
    private static final String CONTENT = "请验证：";
    private static final Integer DEFAULT_USER_INFO_SIZE = 10;

    @Value("${address.server.ip}")
    private String ADDRESS_SERVER;

    @Value("${address.server.mail.validate}")
    private String ADDRESS_SERVER_MAIL_VALIDATE;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private MailFeignClient mailFeignClient;

    @Autowired
    private Map<String, Object> userInfoMap;

    @Override
    public long addUser(User user) {

        return userMapper.insertUser(user);
    }

    @Override
    public void doWxLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String authUrl = WxConstants.AUTH_BASE_URL + "appid=" + WxConstants.APPID
                + "&redirect_uri=" + URLEncoder.encode(WxConstants.REDIRECT_URL)
                + "&response_type=code"
                + "&scope=" + WxConstants.SCOPE
                + "&state=STATE#wechat_redirect";


        String signature = request.getParameter("signature");/// 微信加密签名
        String timestamp = request.getParameter("timestamp");/// 时间戳
        String nonce = request.getParameter("nonce"); /// 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串

        PrintWriter out = response.getWriter();

        if (signature != null && timestamp != null && nonce != null && echostr != null) {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
            out.close();
        } else {
            response.sendRedirect(authUrl);
        }
    }

    @Override
    public RecycleResult doWxCallBack(String code, HttpServletRequest request, HttpServletResponse response) throws  Exception{

        //通过code换取网页授权access_token
        String access_token_url = WxConstants.ACCESS_TOKEN_BASE_URL
                + "appid=" + WxConstants.APPID
                + "&secret=" + WxConstants.APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";

        //发送请求
        String jsonResult = HttpClientUtil.doGet(access_token_url);
        if (jsonResult == null || "".equals(jsonResult)) {
            logger.debug("认证失败");
            return RecycleResult.build(500, "认证失败");
        }

        JsonObject accessJsonObject = new JsonParser().parse(jsonResult).getAsJsonObject();

        //与开放平台关联要使用unionid
        String openid = accessJsonObject.get("openid").getAsString();
        String access_token = accessJsonObject.get("access_token").getAsString();

        /**
         * 微信与用户信息的绑定
         */
        User user = userMapper.getUserInfoByOpenid(openid);
        if (user == null) {
            logger.debug("请先登录进行授权");
            //先登录，然后执行绑定授权操作
            return RecycleResult.build(401, "请先登录进行授权", openid);
        }

        /**
         * 获取用户的微信信息(需scope为 snsapi_userinfo)
         */
        String infoUrl = WxConstants.INFO_BASE_URL
                + "access_token=" + access_token
                + "&openid=" + openid
                + "&lang=zh_CN";
        String userInfoJson = HttpClientUtil.doGet(infoUrl);
        if (userInfoJson == null || "".equals(userInfoJson)) {
            logger.debug("获取用户微信信息失败");
            return RecycleResult.build(500, "获取用户微信信息失败");
        }

        //使用微信的信息(头像)
        JsonObject userInfoJsonObject = new JsonParser().parse(userInfoJson).getAsJsonObject();

        //返回用户信息
        List infoList = new ArrayList<>();
        infoList.add(userInfoJsonObject);
        infoList.add(user);

        //保存用户信息
        userInfoMap.put(user.getUsername(), infoList);
        //转到主页

        return RecycleResult.ok(user.getUsername());
    }

    @Override
    public RecycleResult doLogin(String username, String password, String openid) {

        //是否存在用户
        User user = userMapper.getUserInfoByUsername(username);
        if (user == null) {
            logger.debug("用户不存在");
            return RecycleResult.build(404, "账号或密码错误");
        }

        //验证密码是否正确
        if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
            logger.debug("密码错误");
            return  RecycleResult.build(400, "账号或密码错误");
        }
        /*if (!password.equals(user.getPassword())) {
            logger.debug("密码错误");
            return  RecycleResult.build(400, "账号或密码错误");
        }*/

        //验证用户是否正常
        RecycleResult result = null;
        if ("0".equals(user.getIsLocked())) {
            result = RecycleResult.build(401, "用户还未激活，请前往邮箱激活");
        }
        else if ("1".equals(user.getIsLocked())) {
            //写入openid，即授权
            user.setOpenid(openid);
            user.setUpdateTime(null);
            long count = userMapper.updateUserInfo(user);
            if (count == 0) {
                logger.debug("更新用户openid失败");
                result =  RecycleResult.build(500, "授权失败");
            } else {
                //存放用户信息
                userInfoMap.put(user.getUsername(), user);
                //result = RecycleResult.ok(user);
                result = RecycleResult.ok(user.getUsername());
            }
        }
        else if ("2".equals(user.getIsLocked())) {
            result = RecycleResult.build(402,"账户被锁定，请联系管理员");
        }
        else {
            result = RecycleResult.build(500,"账号异常");
        }


        return result;

    }

    @Override
    public RecycleResult doRegister(User user) {

        //补全信息
        user.setIsLocked("0");  //0表示未激活，1表示正常，2表示锁定
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        String validateCode = UUID.randomUUID().toString();
        user.setValidateCode(validateCode);

        //插入信息
        long result = userMapper.insertUser(user);
        if (result <= 0) {
            logger.debug("插入用户（注册）信息失败");
            return RecycleResult.build(500, "注册失败");
        }

        //TODO 应该有验证码失效时间的处理

        //发送邮件
        //String HTMLContent = "<a href = '" + ADDRESS_SERVER + ADDRESS_SERVER_MAIL_VALIDATE + validateCode + "'/>";
        String content = "  " + ADDRESS_SERVER + ADDRESS_SERVER_MAIL_VALIDATE + validateCode;

        mailFeignClient.sendSimpleMail(user.getEmail(), TITLE, CONTENT + content);

        //返回信息
        return RecycleResult.ok();
    }

    @Override
    public RecycleResult doValidate(String validateCode) {

        User user = userMapper.getUserInfoByValidateCode(validateCode);
        if (user == null) {
            logger.debug("此验证码无效，没有找到对应用户");
            return RecycleResult.build(500, "此验证码无效，没有找到对应用户");
        }

        user.setIsLocked("1");  //0为未激活，1为正常，2为锁定
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        long result = userMapper.updateUserInfo(user);
        if (result <= 0) {
            logger.debug("更新用户状态失败");
            return RecycleResult.build(500, "更新用户状态失败");
        }

        return RecycleResult.ok();

    }

    @Override
    public List<User> getAllUserInfo(Integer page, Integer size) {

        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = DEFAULT_USER_INFO_SIZE;
        }
        int start = (page - 1) * size;
        return userMapper.getAllUserInfo(start, size);

    }

    @Override
    public int getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public RecycleResult updateUserStatus(String status, String username) {

        User userInfo = userMapper.getUserInfoByUsername(username);
        userInfo.setIsLocked(status);
        userInfo.setUpdateTime(null);
        long result = userMapper.updateUserInfo(userInfo);
        if (result <= 0) {
            return RecycleResult.build(500, "更新用户状态失败");
        }
        return RecycleResult.ok();
    }

    @Override
    public RecycleResult getUserInfoForIndex(String username) {

        List userInfo = (List) userInfoMap.get(username);
        return RecycleResult.ok(userInfo);
    }

    @Override
    public Address getAddressByUserId(Integer userId) {
        return addressMapper.getAddressByUserId(userId);
    }

    @Override
    public Address getAddressById(Integer addressId) {
        return addressMapper.getAddressById(addressId);
    }


}
