package com.jingling.cmsservice.service;

import com.jingling.basic.po.Carousel;
import com.jingling.basic.po.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 内容管理业务接口
 * @Source: JDK 1.8
 * @Author: ZhangXiaoxin
 * @Date: 2018-03-31 14:27
 * @Since: version 1.0
 **/
public interface CmsService {
    /**
     * 查询全部轮播信息
     *
     * @return
     */
    List<Carousel> queryCarousel(Integer page,Integer size);

    /**
     * 查询轮播记录条数
     *
     * @return
     */
    Integer countCarousel();

    /**
     * 根据id删除轮播图记录
     *
     * @param id
     */
    void delCarouselById(Integer id);

    /**
     * 添加轮播记录
     *
     * @param carousel
     * @return
     */
    Integer addCarousel(Carousel carousel);

    /**
     * 查询所有的新闻信息
     *
     * @return
     */
    List<News> queryNews(Integer page, Integer size);

    /**
     * 查询新闻记录总条数
     *
     * @return
     */
    Integer countNews();

    /**
     *根据id删除新闻记录
     *
     * @param id
     * @return
     */
    Integer delNewsById(Integer id);

    /**
     * 添加新闻记录
     *
     * @param news
     * @return
     */
    Integer addNews(News news);

    /**
     * 根据id查询新闻详细信息
     *
     * @param id
     * @return
     */
    News viewNewsDetailById(Integer id);

    /**
     * 查询最新的新闻记录
     *
     * @param size
     * @return
     */
    List<News> queryUpdatedNews(Integer size);

    /**
     * 查询最新的轮播记录
     *
     * @param size
     * @return
     */
    List<Carousel> queryUpdatedCarousel(Integer size);

    /**
     * 根据id查询轮播图详细信息
     *
     * @param id
     * @return
     */
    Carousel viewCarouselDetailById(Integer id);
}
