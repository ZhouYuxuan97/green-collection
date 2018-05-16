package com.jingling.mailservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/3/28 12:09
 * @Description:
 */
@RestController
public class TestController {

   /* @Value("${test}")
    private String value;*/
    /**
     * @Description:
     * @Auther: finalcola-zyy
     * @param
     * @return
     */
    @RequestMapping(value = "/test")
    public String test() {
        //return value;
        return "";
    }
}
