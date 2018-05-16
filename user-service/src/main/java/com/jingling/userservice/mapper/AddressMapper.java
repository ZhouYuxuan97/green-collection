package com.jingling.userservice.mapper;

import com.jingling.basic.po.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-12 19:17
 * @Since: version 1.0
 **/
@Mapper
public interface AddressMapper {

    /**
     * 根据用户id获取地址
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
