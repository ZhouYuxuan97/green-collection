package com.jingling.wasteservice.mapper;
import com.jingling.wasteservice.po.FirstGarbageCategory;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface FirstGarbageCategoryMapper {
     void add(FirstGarbageCategory firstGarbagecategory);

     List<FirstGarbageCategory> findFirstGarbagecategoryByName(String firstGarbagecategory_name);

     void deleteFirstGarbagecategoryById(BigInteger firstGarbagecategory_Id);

     FirstGarbageCategory getFirstGarbageCategoryById(BigInteger firstgarbagecategory_Id);

     List<FirstGarbageCategory> getFirstGarbageCategoryList();

}
