/*
 *
 * Author:      Patrick Bartholomew
 * Date:        2/24/2016
 * Modified:    3/2/2016
 * Description: This bean is repsonsible for the email system
 *
 */

package secure.unite.email.system;

import java.util.Date;
import java.util.Properties;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.*;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "emailBean")
@ApplicationScoped
public class EmailSessionBean {

    @Resource(lookup = "mail/UniteEmailService")
    private Session mailSession;

    private String approvalStatus;
    private String volunteerEmail;
    private String to;
    private String subject;
    private String body;

    public void sendEmail() {

        MimeMessage message = new MimeMessage(mailSession);

        try {
            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
            InternetAddress[] address = {new InternetAddress(getTo())};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(getSubject());
            message.setSentDate(new Date());
            message.setText(getBody());
            Transport.send(message);           
            // let the user know the email was sent
            RequestContext requestContext = RequestContext.getCurrentInstance();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful",  "Email has been sent") );
            
        } catch (MessagingException ex) {
            
            RequestContext requestContext = RequestContext.getCurrentInstance();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Failure",  "Email was not sent") );
            
            ex.printStackTrace();
        }
    }

    public void sendGroupApprovalEmail() {
        // pass in the volunteer email and approval status.  
        
        boolean groupApproved = false;        
        
        String tmpSubjectApproved = "Organization Approved";
        String tmpBodyApproved = "Unite Volunteering would like to thank you for "
                + "creating a volunteering organization with us.  We hope to make "
                + "volunteering fun and easy for everyone.";

        String tmpSubjectDenied = "Organization Denied";
        String tmpBodyDenied = "Unite Volunteering would like to thank you for "
                + "applying a volunteering organization with us.  We could not "
                + "approve your organization for any number of reasons.  If you "
                + "feel that you recieved this email in error feel free to email "
                + "the admin team at Unite for futher investigation.  Thank you.";

        String bodyFollowup = "";
        String subjectFollowup = "";

        MimeMessage message = new MimeMessage(mailSession);

        if (groupApproved == false) {
            bodyFollowup = tmpBodyDenied;
            subjectFollowup = tmpSubjectDenied;
        } else {
            bodyFollowup = tmpBodyApproved;
            subjectFollowup = tmpBodyApproved;
        }

        try {
            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
            InternetAddress[] address = {new InternetAddress(getTo())};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subjectFollowup);
            message.setSentDate(new Date());
            message.setText(bodyFollowup);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    // getters/setters
        
    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public String getVolunteerEmail() {
        return volunteerEmail;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setVolunteerEmail(String volunteerEmail) {
        this.volunteerEmail = volunteerEmail;
    }

}
