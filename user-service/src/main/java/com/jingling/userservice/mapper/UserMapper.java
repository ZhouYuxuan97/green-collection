package com.jingling.userservice.mapper;

import com.jingling.basic.po.User;
import com.jingling.userservice.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户Mapper
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-03-29 15:14
 * @Since: version 1.0
 **/
@Mapper
public interface UserMapper {

    long insertUser(User user);

    /**
     * 根据openid查询用户信息
     *
     * @param openid
     * @return
     */
    User getUserInfoByOpenid(String openid);

    /**
     * 根据username获取user信息
     *
     * @param username
     * @return
     */
    User getUserInfoByUsername(String username);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    long updateUserInfo(User user);

    /**
     * 根据激活码更新用户状态
     *
     * @param validateCode
     * @return
     */
    User getUserInfoByValidateCode(String validateCode);

    /**
     * 获取所有用户信息
     *
     * @param start
     * @param size
     * @return
     */
    List<User> getAllUserInfo(@Param("start") Integer start, @Param("size") Integer size);

    /**
     * 查询用户数量
     *
     * @return
     */
    int getUserCount();


}
