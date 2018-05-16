package com.jingling.userservice.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 用户的对外接口
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-02 21:02
 * @Since: version 1.0
 **/
@FeignClient(name = "mail-service")
public interface MailFeignClient {

    /**
     * 简单邮件服务
     *
     * @param to
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/mail/sendSimpleMail")
    String sendSimpleMail(@RequestParam("to") String to, @RequestParam("title") String title
            , @RequestParam("content") String content);


    /**
     * 带有html的邮件服务
     *
     * @param to
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/mail/sendHtmlMail")
    String sendHtmlMail(@RequestParam("to") String to, @RequestParam("title") String title
            , @RequestParam("content") String content);
}
