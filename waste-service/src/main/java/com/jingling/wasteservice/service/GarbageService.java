package com.jingling.wasteservice.service;

import com.jingling.wasteservice.po.Garbage;
import com.jingling.wasteservice.mapper.FirstGarbageCategoryMapper;
import com.jingling.wasteservice.mapper.SecondGarbageCategoryMapper;
import com.jingling.wasteservice.po.FirstGarbageCategory;
import com.jingling.wasteservice.po.SecondGarbageCategory;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
public interface GarbageService {

    List<Garbage> listByIds(Integer[] ids);

    //添加一-三级废品信息
    void add_first(FirstGarbageCategory firstGarbagecategory);
    void add_second(SecondGarbageCategory secondGarbagecategory);
    void add_garbage(Garbage garbage);

    //模糊搜索得到第三级废品信息
     List<Garbage> findGarbageByName(String garbagename);

    //设置第一级和第二级的废品信息无法删除，第三级目录可以删除(删除只移出表，并不实际删除)
    void deleteGarbageById(BigInteger garbageByNameId);

    //连级操作,得到第一级
     List<FirstGarbageCategory> getFirstGarbageList();

    //连级操作，由第一级得到第二级
     List<SecondGarbageCategory> getSecondGarbageByfirstId(BigInteger first_id);

    //连级操作，由第二级得到第三级
     List<Garbage> getGarbageBysecondId(BigInteger second_id);

    //连级操作，由第二级得到第三级
    List<Garbage> getGarbageList(Integer page,Integer size);

    //删除第三级垃圾信息
     void deleteGarbageByID(BigInteger garbage_id);

    //查看第三级垃圾信息
     Garbage ViewGarbageByID(BigInteger garbage_id);

     int countGarbage();

//  public FirstGarbageCategory findFirstGarbagecategoryByName(String firstGarbagecategory);
//  public void deleteFirstGarbagecategoryByName(int firstgarbagecategoryByNameId);
//  public void updateName(int GarbageId);
//  public void updateName(int firstGarbagecategoryId);
//  public void updateName(int secondGarbageCategoryId);
//  public FirstGarbageCategory getFirstGarbageCategoryById(int firstgarbagecategory_id);
//  public SecondGarbageCategory findsecondGarbageCategoryByName(String secondGarbageCategory);
//  public SecondGarbageCategory getsecondGarbageCategoryById(int secondGarbageCategory_id);

}
