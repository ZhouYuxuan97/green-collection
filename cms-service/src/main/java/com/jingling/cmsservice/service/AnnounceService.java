package com.jingling.cmsservice.service;

import com.jingling.basic.po.Announce;

import java.util.List;

/**
 * @Description: 通知公告管理
 * @Source: JDK 1.8
 * @Author: ZhangXiaoxin
 * @Date: 2018-04-06 15:42
 * @Since: version 1.0
 **/
public interface AnnounceService {
    /**
     * 分页查询通知信息
     *
     * @param page
     * @param size
     * @return
     */
    List<Announce> queryAnnounceList(Integer page,Integer size);

    /**
     * 查询通知条数
     *
     * @return
     */
    Integer countAnnounce();

    /**
     * 根据id删除通知信息
     *
     * @param id
     * @return
     */
    Integer delAnnounceById(Integer id);

    /**
     * 添加通知
     *
     * @param announce
     * @return
     */
    Integer addAnnounce(Announce announce);

    /**
     * 根据id获取通知详情
     *
     * @param id
     * @return
     */
    Announce viewAnnounceDetail(Integer id);

    /**
     * 获取最新的通知信息
     *
     * @param size
     * @return
     */
    List<Announce> queryUpdatedAnnounce(Integer size);
}
