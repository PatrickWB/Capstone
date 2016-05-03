/*
 *
 * Author:      Patrick Bartholomew
 * Date:        1/29/2016
 * Description: Managed Bean to handle the login authentication process.
 *              Connects to the DB and verifies user is not disabled.
 *              Contains the SQL driver manager using JDBC(java database connectivity)
 *
 */

package unite.login.beans;
//imports
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.*;
import secure.unite.beans.SessionState;
import unite.DAO.LoginDAO;


@ManagedBean(name="login")
@SessionScoped
public class Login implements Serializable {
    // not used right now.
    //private static final long serialVersionUID = 1094801825228386363L;

    //private variables
    @NotNull
    private String user;
    private String userName;
    @NotNull
    private String pwd;
    public String msg;

    // constructor left out to default to no parameters.
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }
    
    public String getUserName() {
        return userName = SessionState.getUserName();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // validate login
    public String CheckLogin() {
        int uId = LoginDAO.validate(user, pwd);
        // if authentication is good, check for permissions levels.
        if (uId > 0) {            
            HttpSession session = SessionState.getSession();
            session.setAttribute("username", user);
            session.setAttribute("authenticated", "true");
            session.setAttribute("userID", uId);
            session.setMaxInactiveInterval(1800);
            System.out.println("login success");
            return "/auth/volunteers/userDashboard.xhtml?faces-redirect=true";
        } // failed, send back to login.
        else {
            System.out.println("login failure");
            return "/index.xhtml?faces-redirect=true";
        }
    }
    
    public String logout() {
        HttpSession session = SessionState.getSession();
        session.invalidate();
        System.out.println("user was logged out");
        return "/index.xhtml?faces-redirect=true";
    }    
}
