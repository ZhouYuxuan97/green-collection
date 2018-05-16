package com.jingling.mailservice.web;

import com.jingling.basic.enums.ReplyCode;
import com.jingling.basic.exceptions.ServiceException;
import com.jingling.basic.utils.AccountValidatorUtil;
import com.jingling.basic.vo.ReplyResult;
import com.jingling.mailservice.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>发送邮件的controller</p>
 *
 * @Auther: zyy-finalcola
 * @Date: 2018-02-03 20:17
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private MailService mailService;

    /**
     * 发送普通的文本邮件
     * @param to
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/sendSimpleMail", produces = {"application/json;charset=UTF-8"})
    public String sendSimpleMail(String to, String title, String content) {
        logger.info(String.format("to:%s,title:%s,content:%s", to, title, content));
        //验证邮箱格式
        boolean isEmail = AccountValidatorUtil.isEmail(to);
        if (!isEmail) {
            return ReplyResult.toJson(ReplyCode.WRONG_PATTERN);
        }

        try {
            mailService.sendSimpleMail(to, title, content);
            return ReplyResult.toJson(ReplyCode.OK);
        } catch (ServiceException e) {
//            logger.info("MailController.sendSimpleMail",e);
            return ReplyResult.toJson(ReplyCode.CUSTOM.setMessage(e.getMessage()));
        }
    }

    /**
     * 发送html的邮件
     * @param to
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/sendHtmlMail" ,produces = {"application/json;charset=UTF-8"})
    public String sendHtmlMail(String to, String title, String content){
        //验证邮箱格式
        boolean isEmail = AccountValidatorUtil.isEmail(to);
        if (!isEmail) {
            return ReplyResult.toJson(ReplyCode.WRONG_PATTERN);
        }

        try {
            mailService.sendHtmlMail(to, title, content);
            return ReplyResult.toJson(ReplyCode.OK);
        } catch (Exception e) {
            return ReplyResult.toJson(ReplyCode.CUSTOM.setMessage(e.getMessage()));
        }
    }

    /**
     * 发送带有附件的邮件
     * @param to
     * @param title
     * @param content
     * @param file
     * @return
     */
    @RequestMapping(value = "/sendAttachmentsMail" ,produces = {"application/json;charset=UTF-8"})
    public String sendAttachmentsMail(String to, String title, String content,
                           @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            mailService.sendAttachmentsMail(to, title, content, inputStream);
            return ReplyResult.toJson(ReplyCode.OK);
        } catch (Exception e) {
            return ReplyResult.toJson(ReplyCode.REQUEST_FAILED);
        }
    }

}
