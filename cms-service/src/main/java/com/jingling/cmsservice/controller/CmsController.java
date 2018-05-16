package com.jingling.cmsservice.controller;

import com.jingling.basic.po.Carousel;
import com.jingling.basic.po.News;
import com.jingling.basic.utils.FieldTransfer;
import com.jingling.basic.vo.LayuiReplay;
import com.jingling.basic.vo.RecycleResult;
import com.jingling.cmsservice.service.CmsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhangXiaoxin
 * @Date: 2018-03-31 14:21
 * @Since: version 1.0
 **/
@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/cms")
public class CmsController {

    @Autowired
    public CmsService cmsService;

    /**
     * 查询轮播图信息
     *
     * @return
     */
    @GetMapping("/queryCarouselList")
    public Object queryCarouselList(@RequestParam("page") Integer page, @RequestParam("size") Integer size){

        List<Carousel> carouselList = cmsService.queryCarousel(page,size);
        if (carouselList == null){
            return  RecycleResult.build(500,"轮播图为空");
        }
        //return RecycleResult.ok(carouselList);
        //return carouselList;
        Integer count = cmsService.countCarousel();
        return new LayuiReplay<Carousel>(0, "OK", count, carouselList);
    }

    /**
     * 根据id删除轮播图信息
     *
     * @param id
     * @return
     */
    @GetMapping("/delCarousel/{id}")
    public RecycleResult delCarouselById(@PathVariable("id") Integer id){

        cmsService.delCarouselById(id);
        return RecycleResult.ok();
    }

    /**
     * 添加轮播信息
     *
     * @param request
     * @param file
     * @param carousel
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addCarousel")
    public RecycleResult addCarousel(HttpServletRequest request, MultipartFile file, Carousel carousel) throws IOException {
        if (file == null || StringUtils.isBlank(file.getOriginalFilename())){
            RecycleResult.build(500, "文件不能为空");
        }

        String rootPath = ResourceUtils.getURL("").getPath() + "static/upload/";
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        String realPath = new Date().getTime() + file.getOriginalFilename();
        //存储文件
        File storeFile = new File(rootPath + realPath);

        file.transferTo(storeFile);

        //写入数据库
        carousel.setPhotoPath(realPath);

        carousel.setPosterId("1001");
        carousel.setPosterName("张三");

        if (carousel != null){
            cmsService.addCarousel(carousel);
        }
        return RecycleResult.ok();
    }

    /**
     * 查询新闻信息
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/queryNews")
    public Object queryNews(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        //response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域的问题
        List<News> newslList = cmsService.queryNews(page,size);
        if (newslList == null) {
            return RecycleResult.build(500, "新闻为空");
        }
        Integer count = cmsService.countNews();
        return new LayuiReplay<News>(0, "OK", count, newslList);
    }

    /**
     * 根据id删除用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/delNews/{id}")
    public RecycleResult delNewsById(@PathVariable("id") Integer id){
        Integer count = cmsService.delNewsById(id);
        if (count == -1){
            return  RecycleResult.build(400,"删除失败新闻！！");
        }else {
            return  RecycleResult.ok();
        }
    }

    /**
     * 添加新闻信息
     *
     * @param request
     * @param file
     * @param news
     * @return
     */
    @RequestMapping(value = "/addNews")
    public RecycleResult addNews(HttpServletRequest request,MultipartFile file,News news) throws IOException {
        if (file == null || StringUtils.isBlank(file.getOriginalFilename())){
            RecycleResult.build(500, "文件不能为空");
        }

        //容器名
        String rootPath = ResourceUtils.getURL("").getPath() + "static/upload/";
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        //文件名
        String realPath = new Date().getTime() + file.getOriginalFilename();
        //存储文件
        File storeFile = new File(rootPath + realPath);

        file.transferTo(storeFile);

        //写入数据库
        news.setPhotoPath(realPath);

        news.setPosterId(1002);
        news.setPosterName("李四");

        if (news!=null){
            cmsService.addNews(news);
        }
        return RecycleResult.ok();
    }

    /**
     * 根据id查询新闻详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/viewNewsDetail/{id}")
    public Object viewNewsDetailById(@PathVariable("id") Integer id){
        if (id != null){
            return cmsService.viewNewsDetailById(id);
        }else {
            return RecycleResult.build(400,"查询无此记录");
        }
    }

    /**
     * 查询跟新新闻最新记录
     *
     * @param size
     * @return
     */
    @GetMapping("/queryUpdatedNews/{size}")
    public Object queryUpdatedNews(@PathVariable("size") Integer size){
        if (size > 0){
            List<News> newsList = cmsService.queryUpdatedNews(size);
            return RecycleResult.build(0,"success",newsList);
        }else {
            return RecycleResult.build(400,"error");
        }

    }

    /**
     * 查询最新轮播记录
     *
     * @param size
     * @return
     */
    @GetMapping("/queryUpdatedCarousel/{size}")
    public Object queryUpdatedCarousel(@PathVariable("size") Integer size){
        if (size > 0){
            List<Carousel> carouselList = cmsService.queryUpdatedCarousel(size);
            return RecycleResult.build(0,"success",carouselList);
        }else {
            return RecycleResult.build(400,"error");
        }
    }

    /**
     * 根据id查询轮播详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/viewCarouselDetail/{id}")
    public Object viewCarouselDetailById(@PathVariable("id") Integer id){
        if (id != null){
            return cmsService.viewCarouselDetailById(id);
        }else {
            return RecycleResult.build(400,"查询无此记录");
        }
    }
}
