package com.jingling.wasteservice.mapper;

import com.jingling.wasteservice.po.SecondGarbageCategory;
import org.apache.ibatis.annotations.Mapper;
import java.math.BigInteger;
import java.util.List;

@Mapper
public interface SecondGarbageCategoryMapper {
     void add(SecondGarbageCategory secondGarbageCategory);

     List<SecondGarbageCategory> findsecondGarbageCategoryByName(String secondGarbageCategory_name);

     void deletesecondGarbageCategoryById(BigInteger secondGarbageCategory_Id);

     SecondGarbageCategory getsecondGarbageCategoryById(BigInteger secondGarbageCategory_Id);

     List<SecondGarbageCategory> getsecondGarbageCategoryByFirstId(BigInteger first_id);

     List<SecondGarbageCategory> getSecondGarbageCategoryList();
}
