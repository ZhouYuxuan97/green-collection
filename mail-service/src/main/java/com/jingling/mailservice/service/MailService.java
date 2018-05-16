package com.jingling.mailservice.service;

import java.io.InputStream;

/**
 * <p>发送邮件服务<p>
 *
 * @Auther: zyy-finalcola
 * @Date: 2018-02-03 19:53
 */
public interface MailService {

    /**
     * 发送纯文本邮件
     * @param to
     * @param title
     * @param content
     */
    void sendSimpleMail(String to, String title, String content);

    /**
     * 发送HTML格式的邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param inputStream   文件输入流
     */
    void sendAttachmentsMail(String to, String subject, String content, InputStream inputStream);

}
