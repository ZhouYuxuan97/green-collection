package com.jingling.wasteservice.controller;
import com.google.gson.Gson;
import com.jingling.basic.vo.LayuiReplay;
import com.jingling.basic.vo.RecycleResult;
import com.jingling.wasteservice.service.GarbageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.jingling.wasteservice.po.Garbage;
import com.jingling.wasteservice.po.FirstGarbageCategory;
import com.jingling.wasteservice.po.SecondGarbageCategory;
import java.math.BigInteger;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/garbage")
public class GarbageController {

    @Autowired
    private GarbageService garbageService;

    /**
     * @description:
     * @auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/listGarbageByIds",produces = {"application/json;charset=UTF-8"})
    public String listGarbageByIds(@RequestParam("ids") Integer[] ids) {
        List<Garbage> garbageList = garbageService.listByIds(ids);
        return new Gson().toJson(garbageList);
    }

    @GetMapping("/queryFirstGarbageList")
    public Object queryFirstCarbageList(){

        List<FirstGarbageCategory> firstgarbageList = garbageService.getFirstGarbageList();
        if (firstgarbageList == null){
            return  RecycleResult.build(500,"当前第一级废品目录为空，查找错误");
        }
        //count并没有单独计算
        return firstgarbageList;
    }

    @GetMapping("/querySecondGarbageList/{id}")
    public Object querySecondGarbageList(@PathVariable("id") BigInteger id){
        List<SecondGarbageCategory> secondgarbageList = garbageService.getSecondGarbageByfirstId(id);
        if (secondgarbageList == null){
            return  RecycleResult.build(500,"当前第二级废品目录为空，查找错误");
        }
        //count并没有单独计算
        return secondgarbageList;
    }

    @GetMapping("/queryGarbageListBySid/{id}")
    public Object queryGarbageListBySid(@PathVariable("id") BigInteger id){
        List<Garbage> garbageList = garbageService.getGarbageBysecondId(id);
        if (garbageList == null){
            return  RecycleResult.build(500,"当前第二级废品目录为空，查找错误");
        }
        //count并没有单独计算
        return garbageList;
    }

    @GetMapping("/queryGarbageList")
    public Object queryGarbageList(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        List<Garbage> garbageList = garbageService.getGarbageList(page,size);
        if (garbageList == null){
            return  RecycleResult.build(500,"当前废品目录为空，查找错误");
        }
        Integer count =garbageService.countGarbage();
        return new LayuiReplay<Garbage>(0, "OK", count, garbageList);
    }

    @GetMapping("/delGarbageById/{id}")
    //@PathVariable可以用来映射URL中的占位符到目标方法的参数中
    public RecycleResult delGarbageById(@PathVariable("id") BigInteger id){
        garbageService.deleteGarbageByID(id);
        return  RecycleResult.ok();
    }

    @PostMapping("/addFirst")
    public RecycleResult addFirst(FirstGarbageCategory firstGarbageCategory){
//        firstGarbageCategory.setId(UID());
        if (firstGarbageCategory!=null){
            garbageService.add_first(firstGarbageCategory);
        }
        return RecycleResult.ok();
    }

    @PostMapping(value = "/addSecond")
    public RecycleResult addSecond(SecondGarbageCategory secondGarbageCategory){
//        secondGarbageCategory.setId(UID());
        if (secondGarbageCategory!=null){
            garbageService.add_second(secondGarbageCategory);
        }
        return RecycleResult.ok();
    }

    @PostMapping(value = "/addGarbage")
    public RecycleResult addGarbage(Garbage garbage){
//        garbage.setId(UID());
        if (garbage!=null){
            garbageService.add_garbage(garbage);
        }
        return RecycleResult.ok();
    }

    @GetMapping("/viewGarbageDetail/{id}")
    public Object viewGarbageDetail(@PathVariable("id") BigInteger id){
        if (id != null){
            Object o=garbageService.ViewGarbageByID(id);
            return garbageService.ViewGarbageByID(id);
        }else {
            return RecycleResult.build(400,"查询无此记录");
        }
    }

//    public BigInteger UID(){
//        long w = 10000;
//        long r = 0;
//        r = (long) ((Math.random() + 1) * w);
//        String s= System.currentTimeMillis() + String.valueOf(r).substring(1);
//        BigInteger b=new BigInteger(s,10);
//        return b;
//    }
}
