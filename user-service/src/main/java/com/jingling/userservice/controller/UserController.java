package com.jingling.userservice.controller;

import com.jingling.basic.po.Address;
import com.jingling.basic.po.User;
import com.jingling.basic.vo.LayuiReplay;
import com.jingling.basic.vo.RecycleResult;
import com.jingling.userservice.constant.WxConstants;
import com.jingling.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 用户接口
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-29 15:13
 * @Since: version 1.0
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 微信登录
     *
     * @param request
     * @param response
     */
    @GetMapping("/wx/wxLogin")
    public void doWxLogin (HttpServletRequest request, HttpServletResponse response) {

        try {
            userService.doWxLogin(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 微信登录回调
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/wx/callBack")
    public void wxCallBack(HttpServletRequest request, HttpServletResponse response) {

        //获取回调信息中携带的code信息
        String code = request.getParameter("code");

        try {

            RecycleResult recycleResult =  userService.doWxCallBack(code, request, response);
            if (recycleResult.getStatus() == 401) {
                System.out.println(recycleResult.getData());
                response.sendRedirect(WxConstants.WECHAT_LOGIN + "?openid=" + recycleResult.getData());
            }
            else {
                response.sendRedirect(WxConstants.SUCCESS_INDEX + "?username=" + recycleResult.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 登录认证
     *
     * @param username
     * @param password
     * @param openid
     * @return
     */
    @PostMapping("/login")
    public RecycleResult doLogin(String username, String password, String openid) {

        if (openid == null || "".equals(openid)) {
            return RecycleResult.build(500, "无法认证");
        }
        if (username == null || "".equals(username) || password == null || "".equals(password)) {
            return RecycleResult.build(500, "信息不完整");
        }

        RecycleResult result = userService.doLogin(username, password, openid);

        return result;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public RecycleResult doRegister(User user, HttpServletResponse response) {

        if (user == null) {
            return RecycleResult.build(400, "用户信息不能为空");
        }

        RecycleResult result = userService.doRegister(user);
        try {
            response.sendRedirect(WxConstants.REGISTER_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 账号激活验证
     *
     * @param validateCode
     * @return
     */
    @GetMapping("/validate/{validateCode}")
    public RecycleResult doValidate(HttpServletResponse response, @PathVariable("validateCode") String validateCode) {

        if (validateCode == null || "".equals(validateCode)) {
            return RecycleResult.build(400, "激活码不能为空");
        }

        try {
            response.sendRedirect(WxConstants.VALIDATE_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userService.doValidate(validateCode);
    }

    /**
     * 分页查询所有用户信息
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getAllUserInfo")
    public Object getAllUserInfo(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        int count = userService.getUserCount();
        List<User> userList  = userService.getAllUserInfo(page, size);
        return new LayuiReplay<User>(0, "OK", count, userList);

    }

    /**
     * 锁定/解锁 用户
     *
     * @param status
     * @param username
     * @return
     */
    @GetMapping("/lockUser/{status}/{username}")
    public RecycleResult lockUser(@PathVariable("status") String status, @PathVariable("username") String username) {

        return userService.updateUserStatus(status, username);
    }

    /**
     * 主页加载用户信息
     *
     * @param username
     * @return
     */
    @GetMapping("/getUserInfoForIndex/{username}")
    public RecycleResult getUserInfoForIndex(@PathVariable("username") String username) {

        return userService.getUserInfoForIndex(username);
    }

    /**
     * 根据userid获取用户地址id
     *
     * @param userId
     * @return
     */
    @GetMapping("/getAddressId/{userId}")
    public String getAddressByUserId(@PathVariable("userId") Integer userId) {

        Address address = userService.getAddressByUserId(userId);
        return address.getAddressId().toString();
    }

    /**
     * 根据id获取地址
     *
     * @param addressId
     * @return
     */
    @GetMapping("/getAddress/{addressId}")
    public Address getAddressById(@PathVariable("addressId") Integer addressId) {

        return userService.getAddressById(addressId);
    }


}
