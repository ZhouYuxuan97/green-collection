package com.jingling.cmsservice.service.impl;

import com.jingling.basic.po.Carousel;
import com.jingling.basic.po.News;
import com.jingling.cmsservice.mapper.CarouselMapper;
import com.jingling.cmsservice.mapper.NewsMapper;
import com.jingling.cmsservice.service.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 内容管理业务实现
 * @Source: JDK 1.8
 * @Author: ZhangXiaoxin
 * @Date: 2018-03-31 14:28
 * @Since: version 1.0
 **/
@Service
public class CmsServiceImpl implements CmsService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<Carousel> queryCarousel(Integer page,Integer size) {
        if(page == null || page <= 0){
           page = 1;
        }
        if (size == null || size <= 0){
            size = 10;
        }

        Integer start = (page - 1) * size;
        return  carouselMapper.queryCarousel(start,size);
    }

    @Override
    public Integer countCarousel() {
        return carouselMapper.countCarousel();
    }

    @Override
    public void delCarouselById(Integer id) {
        carouselMapper.delCarouselById(id);
    }

    @Override
    public Integer addCarousel(Carousel carousel) {
        return  carouselMapper.addCarousel(carousel);
    }

    @Override
    public List<News> queryNews(Integer page, Integer size) {
        if(page == null || page <= 0){
            page = 1;
        }
        if (size == null || size <= 0){
            size = 10;
        }
        Integer start = (page - 1) * size;
        return  newsMapper.queryNews(start,size);
    }

    @Override
    public Integer countNews() {
        return newsMapper.countNews();
    }

    @Override
    public Integer delNewsById(Integer id) {
        if (id != null) {
            return newsMapper.delNewsById(id);
        }else {
            return -1;
        }
    }

    @Override
    public Integer addNews(News news) {
       return  newsMapper.addNews(news);
    }

    @Override
    public News viewNewsDetailById(Integer id) {
        return  newsMapper.viewNewsDetailById(id);
    }

    @Override
    public List<News> queryUpdatedNews(Integer size) {
        return newsMapper.queryUpdatedNews(size);
    }

    @Override
    public List<Carousel> queryUpdatedCarousel(Integer size) {
        return carouselMapper.queryUpdatedCarousel(size);
    }

    @Override
    public Carousel viewCarouselDetailById(Integer id) {
        return carouselMapper.viewCarouselDetailById(id);
    }
}
