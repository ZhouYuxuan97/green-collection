package com.jingling.userservice.service;

import com.jingling.basic.po.Address;
import com.jingling.basic.po.User;
import com.jingling.basic.vo.RecycleResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 用户业务接口
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-29 16:07
 * @Since: version 1.0
 **/
public interface UserService {

    long addUser(User user);

    /**
     * 微信登录认证
     *
     * @param request
     * @param response
     */
    void doWxLogin(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 微信登录回调业务处理
     *
     * @param access_token_url
     * @param request
     * @param response
     * @return
     */
    RecycleResult doWxCallBack(String access_token_url, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 登录认证业务处理
     *
     * @param username
     * @param password
     * @param openid
     * @return
     */
    RecycleResult doLogin(String username, String password, String openid);

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    RecycleResult doRegister(User user);

    /**
     * 激活账号
     *
     * @param validateCode
     * @return
     */
    RecycleResult doValidate(String validateCode);

    /**
     * 查询所有用户信息
     *
     * @param page
     * @param size
     * @return
     */
    List<User> getAllUserInfo(Integer page, Integer size);

    /**
     * 获取用户数量
     *
     * @return
     */
    int getUserCount();

    /**
     * 更新用户状态
     *
     * @param status
     * @param username
     * @return
     */
    RecycleResult updateUserStatus(String status, String username);

    /**
     * 为主页获取用户信息
     *
     * @param username
     * @return
     */
    RecycleResult getUserInfoForIndex(String username);

    /**
     * 根据userid查询地址
     *
     * @param userId
     * @return
     */
    Address getAddressByUserId(Integer userId);

    /**
     * 根据id获取地址
     *
     * @param addressId
     * @return
     */
    Address getAddressById(Integer addressId);
}
