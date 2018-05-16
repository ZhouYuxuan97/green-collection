package com.jingling.mailservice.service.impl;

import com.jingling.basic.enums.ExceptionEnums;
import com.jingling.basic.exceptions.ServiceException;
import com.jingling.mailservice.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>提供邮件服务的实现类</p>
 *
 * @Auther: zyy-finalcola
 * @Date: 2018-02-03 19:57
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 发送纯文本邮件
     *
     * @param to
     * @param title
     * @param content
     */
    @Override
    public void sendSimpleMail(String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);

        try {
            sender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
            throw new ServiceException(ExceptionEnums.Error);
        }
    }

    /**
     * 发送HTML格式的邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            sender.send(message);
            logger.info("html邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
            throw new SecurityException(ExceptionEnums.Error.getMessage());
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param inputStream 文件输入流
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, InputStream inputStream) {
        byte[] bytes = null;
        try {
            int available = inputStream.available();
            bytes = new byte[available];
            inputStream.read(bytes);
        } catch (IOException e) {
            throw new ServiceException(ExceptionEnums.Error);
        }

        MimeMessage message = sender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.addAttachment("附件", new ByteArrayResource(bytes));
            sender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }
}
