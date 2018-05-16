package com.jingling.cmsservice.mapper;

import com.jingling.basic.po.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Source: JDK 1.8
 * @Author: ZhangXiaoxin
 * @Date: 2018-03-31 14:22
 * @Since: version 1.0
 **/
@Mapper
public interface NewsMapper {
    /**
     * 查询所有的新闻信息
     *
     * @return
     */
    List<News> queryNews(@Param("start") Integer start, @Param("size") Integer size);

    /**
     * 查询新闻记录条数
     *
     * @return
     */
    Integer countNews();

    /**
     * 根据id删除新闻记录
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
}
