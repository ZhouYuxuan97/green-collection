package com.jingling.cmsservice.mapper;

import com.jingling.basic.po.Carousel;
import com.jingling.basic.po.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhangXiaoxin
 * @Date: 2018-03-31 14:23
 * @Since: version 1.0
 **/
@Mapper
public interface CarouselMapper {
    /**
     * 查询所有轮播图信息
     *
     * @return
     */
    List<Carousel> queryCarousel(@Param("start") Integer start, @Param("size") Integer size);

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
