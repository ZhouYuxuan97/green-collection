package com.jingling.wasteservice.service.impl;
import com.jingling.wasteservice.mapper.FirstGarbageCategoryMapper;
import com.jingling.wasteservice.mapper.GarbageMapper;
import com.jingling.wasteservice.mapper.SecondGarbageCategoryMapper;
import com.jingling.wasteservice.po.FirstGarbageCategory;
import com.jingling.wasteservice.po.SecondGarbageCategory;
import com.jingling.wasteservice.po.Garbage;
import com.jingling.wasteservice.service.GarbageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class GarbageServiceImpl implements GarbageService {
    @Autowired
    public FirstGarbageCategoryMapper firstGarbageMapper;

    @Autowired
    public SecondGarbageCategoryMapper scondGarbageMapper;

    @Autowired
    public GarbageMapper garbageMapper;


    @Override
    public List<Garbage> listByIds(Integer[] ids) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        List<Garbage> garbage = garbageMapper.listByIds(list);
        return garbage;
    }

    @Override
    //添加一-三级废品信息
    public void add_first(FirstGarbageCategory firstGarbagecategory){
        firstGarbageMapper.add(firstGarbagecategory);
    }
    @Override
    public void add_second(SecondGarbageCategory secondGarbagecategory){
        scondGarbageMapper.add(secondGarbagecategory);
    }
    @Override
    public void add_garbage(Garbage garbage){
        garbageMapper.add(garbage);
    }

    @Override
    //模糊搜索得到第三级废品信息
    public List<Garbage> findGarbageByName(String garbagename){
        List<Garbage> garbages= garbageMapper.findGarbageByName(garbagename);
        return garbages;
    }

    @Override
    //设置第一级和第二级的废品信息无法删除，第三级目录可以删除(删除只移出表，并不实际删除)
    public void deleteGarbageById(BigInteger garbageById){
        garbageMapper.deleteGarbageById(garbageById);
    }

    @Override
    //连级操作,得到第一级
    public List<FirstGarbageCategory> getFirstGarbageList(){
        return firstGarbageMapper.getFirstGarbageCategoryList();
    }

    @Override
    //连级操作，由第一级得到第二级
    public List<SecondGarbageCategory> getSecondGarbageByfirstId(BigInteger first_id){
        return scondGarbageMapper.getsecondGarbageCategoryByFirstId(first_id);
    }

    @Override
    //连级操作，由第二级得到第三级
    public List<Garbage> getGarbageBysecondId(BigInteger second_id){
        return  garbageMapper.findGarbageBySecond_Id(second_id);
    }
    @Override
    public List<Garbage> getGarbageList(Integer page,Integer size){
        if(page==null ||page <= 0){
            page = 1;
        }
        if (size==null||size <= 0){
            size = 10;
        }
        Integer start = (page - 1) * size;
        return  garbageMapper.getGarbageList(start,size);
    }
    @Override
    //删除第三级垃圾信息
    public void deleteGarbageByID(BigInteger garbage_id){
        garbageMapper.deleteGarbageById(garbage_id);
    }

    @Override
    //查看第三级垃圾信息
    public Garbage ViewGarbageByID(BigInteger garbage_id){
       return  garbageMapper.getGarbageById(garbage_id);
    }

    @Override
    public int countGarbage(){
       return garbageMapper.countGarbage();
    }

}
