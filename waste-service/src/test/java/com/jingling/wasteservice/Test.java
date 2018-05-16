package com.jingling.wasteservice;
import com.jingling.wasteservice.po.FirstGarbageCategory;
import com.jingling.wasteservice.po.SecondGarbageCategory;
import com.jingling.wasteservice.po.Garbage;
import com.jingling.wasteservice.service.impl.GarbageServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.math.BigInteger;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private GarbageServiceImpl garbageService;

    @org.junit.Test
    public void test() {
//        FirstGarbageCategory first=new FirstGarbageCategory();
//        SecondGarbageCategory second=new SecondGarbageCategory();
//        Garbage garbage=new Garbage();
//        //添加测试已通过
//
//        //查找通过
//        List<Garbage> garbageListbyname=garbageService.findGarbageByName("杂塑料");
//        //删除通过
//        garbageService.deleteGarbageById(BigInteger.valueOf(66666));
//       List<FirstGarbageCategory> listfirst=garbageService.getFirstGarbageList();
//       List<SecondGarbageCategory> listsecond=garbageService.getSecondGarbageByfirstId(BigInteger.valueOf(1));
//       List<Garbage> listgarbageID=garbageService.getGarbageBysecondId(BigInteger.valueOf(1));
//
////       garbageService.deleteGarbageByID(BigInteger.valueOf(1));
//       Garbage garbage1=garbageService.ViewGarbageByID(BigInteger.valueOf(1));



    }

//   public static void main(String[] args) {
//       FirstGarbageCategory first=new FirstGarbageCategory();
//       SecondGarbageCategory second=new SecondGarbageCategory();
//       Garbage garbage=new Garbage();
//       first.setId(BigInteger.valueOf(666666));
//       second.setId(BigInteger.valueOf(666666));
//       garbage.setId(BigInteger.valueOf(666666));
//
//       GarbageServiceImpl garbageService=new GarbageServiceImpl();
//
//       garbageService.add_first(first);
//       garbageService.add_second(second);
//       garbageService.add_garbage(garbage);
//
////       List<Garbage> garbageListbyname=garbageService.findGarbageByName("塑料");
////
////       garbageService.deleteGarbageById(BigInteger.valueOf(666666));
////       List<FirstGarbageCategory> listfirst=garbageService.getFirstGarbageList();
////       List<SecondGarbageCategory> listsecond=garbageService.getSecondGarbageByfirstId(BigInteger.valueOf(1));
////       List<Garbage> listgarbageID=garbageService.getGarbageBysecondId(BigInteger.valueOf(1));
////
////       garbageService.deleteGarbageByID(BigInteger.valueOf(1));
////       Garbage garbage1=garbageService.ViewGarbageByID(BigInteger.valueOf(1));
//
//    }

}
