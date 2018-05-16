package com.jingling.orderservice.service.remote.fallback;

import com.jingling.orderservice.service.remote.MailService;
import org.springframework.stereotype.Component;

/**
 * @Auther: finalcola-zyy
 * @Date: 2018/3/28 10:30
 * @Description: 邮箱服务降级服务
 */
@Component
public class MailServiceFallback implements MailService {
    /**
     * 发送文本邮件
     *
     * @param to      发送对象的邮箱地址
     * @param title   标题
     * @param content 正文
     * @return
     */
    @Override
    public String sendSimpleMail(String to, String title, String content) {
        return "服务调用时间过长，请重试";
    }
}
