package com.example.springbootdemo.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 发送邮件
 */
public class SendEmail {

    private static final String  HOST = "smtp.qq.com";  //qq邮箱服务器
    private static final String ORIGINMAIN = "1187697635@qq.com";       //发送人的邮箱号

    /**
     * 发送没有附件的邮箱
     * 发送邮件时，需要知道
     * @param destMail  需要发送的邮箱
     * @param subject     发送邮件的标题
     * @param content   发送邮件的内容
     * @return
     * @throws GeneralSecurityException
     */
    public static boolean sendEmailWithoutAffix(String destMail,String subject,String content) throws GeneralSecurityException {
        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", HOST);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("1187697635@qq.com", "xldowlpdptydgjii"); //发件人邮件用户名、密码
            }
        });
        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(ORIGINMAIN));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destMail));

            // Set Subject: 头部头字段
            message.setSubject(subject);

            // 设置消息体
            message.setText(content);

            //设置消息的发送时间
            message.setSentDate(new Date());


            // 发送消息
            Transport.send(message);

            System.out.println("Sent message successfully....from runoob.com");
        }catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    //判断邮箱正确

    /**
     * 如果返回为true,则邮箱正确
     * 如果返回为false,则邮箱错误
     * @param mail
     * @return
     */
    public static boolean validateMail(String mail){
        String regexp = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
        Pattern pattern = Pattern.compile(regexp);
        return pattern.matcher(mail).matches();
    }

    public static void main(String[] args) {
        System.out.println("125063980@qq.com".matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$"));
        System.out.println(validateMail("125063980@qq.com"));
    }

}
