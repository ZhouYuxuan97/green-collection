package com.jingling.cmsservice.service.impl;

import com.jingling.basic.po.Announce;
import com.jingling.cmsservice.mapper.AnnounceMapper;
import com.jingling.cmsservice.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 通知公告管理
 * @Source: JDK 1.8
 * @Author: ZhangXiaoxin
 * @Date: 2018-04-06 15:43
 * @Since: version 1.0
 **/
@Service
public class AnnounceServiceImpl implements AnnounceService {
    @Autowired
    public AnnounceMapper announceMapper;

    @Override
    public List<Announce> queryAnnounceList(Integer page, Integer size) {
        if(page == null || page <= 0){
            page = 1;
        }
        if (size == null || size <= 0){
            size = 10;
        }

        Integer start = (page - 1) * size;
        return  announceMapper.queryAnnounceList(start,size);
    }

    @Override
    public Integer countAnnounce() {
        return announceMapper.countAnnounce();
    }

    @Override
    public Integer delAnnounceById(Integer id) {
        return  announceMapper.delAnnounceById(id);
    }

    @Override
    public Integer addAnnounce(Announce announce) {
        return announceMapper.addAnnounce(announce);
    }

    @Override
    public Announce viewAnnounceDetail(Integer id) {
        return  announceMapper.viewAnnounceDetail(id);
    }

    @Override
    public List<Announce> queryUpdatedAnnounce(Integer size) {
        return announceMapper.queryUpdatedAnnounce(size);
    }
}
