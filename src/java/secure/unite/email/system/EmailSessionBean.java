package secure.unite.email.system;

import java.util.Date;
import java.util.Properties;
import javax.annotation.Resource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.*;
import javax.mail.internet.*;

@ManagedBean(name = "emailBean")
@RequestScoped
public class EmailSessionBean {

    @Resource(lookup = "mail/UniteEmailService")
    private Session mailSession;
    
    private String to;
    private String subject;
    private String body;

    public void sendEmail() {

        MimeMessage message = new MimeMessage(mailSession);

        try {
            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);
            Transport.send(message);
        } 
        catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

}
