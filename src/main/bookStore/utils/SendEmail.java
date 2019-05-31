package utils;


import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @ClassName SendEmail
 * @Description TODO
 * @Author yzy
 * @Date 2019-05-29 21:31
 * @Version 1.0
 */
public class SendEmail {
    //yumeiqun@qq.com
    //授权码：oitnvssifdzzbibe
    //      vbryqjalyyhbbhbe
    //
    //  接收服务器：imap.qq.com 993
    //  发送服务器smtp.qq.com 465

    //
    static final private String stmp = "smtp.163.com";
    static final private  String from = "yzy_yuzhiyuan@163.com"; // 邮件发送人的邮件地址
    static final String username = "yzy_yuzhiyuan@163.com";  	//发件人的邮件帐户
    static final String password = "Thinker123";   					//发件人的授权码
    public static boolean sendMail(String email, String emailMsg) {
        String to = email; 										// 邮件接收人的邮件地址


        //定义Properties对象,设置环境信息
        Properties props = System.getProperties();

        //设置邮件服务器的地址
        props.setProperty("mail.smtp.host", stmp); // 指定的smtp服务器
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
        //创建Session对象,session对象表示整个邮件的环境信息
        Session session = Session.getInstance(props);
        //设置输出调试信息
        session.setDebug(true);
        try {
            //Message的实例对象表示一封电子邮件
            MimeMessage message = new MimeMessage(session);
            //设置发件人的地址
            message.setFrom(new InternetAddress(from));
            //设置主题
            message.setSubject("干");
            //设置邮件的文本内容
            //message.setText("Welcome to JavaMail World!");
            message.setContent((emailMsg),"text/html;charset=utf-8");

            //设置附件
            //message.setDataHandler(dh);

            //从session的环境中获取发送邮件的对象
            Transport transport=session.getTransport();
            //连接邮件服务器
            transport.connect(stmp,25, username, password);
            //设置收件人地址,并发送消息
            transport.sendMessage(message,new Address[]{new InternetAddress(to)});
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
