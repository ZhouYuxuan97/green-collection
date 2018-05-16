package com.jingling.cmsservice.controller;

import com.jingling.basic.po.Announce;
import com.jingling.basic.vo.LayuiReplay;
import com.jingling.basic.vo.RecycleResult;
import com.jingling.cmsservice.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 通知公告管理
 * @Source: JDK 1.8
 * @Author: ZhangXiaoxin
 * @Date: 2018-04-06 15:44
 * @Since: version 1.0
 **/
@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/cms")
public class AnnounceController {
    @Autowired
    public AnnounceService announceService;

    /**
     * 分页查询通知信息
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/queryAnnounceList")
    public Object queryAnnounceList(@RequestParam("page") Integer page,@RequestParam("size") Integer size){

        List<Announce> announcelList = announceService.queryAnnounceList(page,size);
        if (announcelList == null){
            return  RecycleResult.build(500,"通知记录为空");
        }
        Integer count = announceService.countAnnounce();
        return new LayuiReplay<Announce>(0, "OK", count, announcelList);
    }

    /**
     * 根据id删除通知信息
     *
     * @param id
     * @return
     */
    @GetMapping("/delAnnounce/{id}")
    public RecycleResult delAnnounceById(@PathVariable("id") Integer id){
        announceService.delAnnounceById(id);
        return  RecycleResult.ok();
    }

    /**
     * 添加通知信息
     *
     * @param announce
     * @return
     */
    @PostMapping("/addAnnounce")
    public RecycleResult addAnnounce(Announce announce){
        announce.setPosterId(1004);
        announce.setPosterName("李明");

        if (announce != null){
            announceService.addAnnounce(announce);
        }
        return  RecycleResult.ok();
    }

    /**
     * 根据id查询通知想情
     *
     * @param id
     * @return
     */
    @GetMapping("/viewAnnounceDetail/{id}")
    public Object viewAnnounceDetail(@PathVariable("id") Integer id){
        if (id != null){
            return announceService.viewAnnounceDetail(id);
        }else {
            return RecycleResult.build(400,"查询无此记录");
        }
    }

    /**
     * 查询最新通知
     *
     * @param size
     * @return
     */
    @GetMapping("/queryUpdatedAnnounce/{size}")
    public Object queryUpdatedAnnounce(@PathVariable("size") Integer size){
        if (size > 0){
            List<Announce> announceList = announceService.queryUpdatedAnnounce(size);
            return RecycleResult.build(0,"success",announceList);
        }else {
            return RecycleResult.build(400,"error");
        }

    }
}
