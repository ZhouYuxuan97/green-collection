package com.jingling.wasteservice.mapper;
import com.jingling.wasteservice.po.Garbage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface GarbageMapper {

    void add(Garbage garbage);

    void deleteGarbageById(BigInteger garbage_Id);

    Garbage getGarbageById(BigInteger garbage_Id);

    List<Garbage> findGarbageByName(String garbage_name);

    List<Garbage> findGarbageBySecond_Id(BigInteger second_id);

    List<Garbage> getGarbageName();

    List<Garbage> getGarbageList(@Param("start") Integer start, @Param("size") Integer size);

    int countGarbage();

    List<Garbage> listByIds(List<Integer> ids);
}
