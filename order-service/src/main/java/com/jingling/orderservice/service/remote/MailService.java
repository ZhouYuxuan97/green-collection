package com.jingling.orderservice.service.remote;

import com.jingling.orderservice.service.remote.fallback.MailServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/3/28 09:59
 * @Description: 远程的邮箱服务
 */
@FeignClient(value = "mail-service",fallback = MailServiceFallback.class)
public interface MailService {
    /**
     * 发送文本邮件
     * @param to    发送对象的邮箱地址
     * @param title 标题
     * @param content   正文
     * @return
     */
    @RequestMapping("/mail/sendSimpleMail")
    String sendSimpleMail(@RequestParam("to") String to, @RequestParam("title") String title
            , @RequestParam("content") String content);
}
