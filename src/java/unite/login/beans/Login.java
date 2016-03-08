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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import unite.spring.security.Authorities;
import secure.unite.beans.SessionState;
import unite.DAO.LoginDAO;


@ManagedBean
@SessionScoped
public class Login implements UserDetails {
    // not used right now.
    //private static final long serialVersionUID = 1094801825228386363L;

    //private variables
    @NotNull
    private String user;
    @NotNull
    private String pwd;
    private Set<Authorities> authorities;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // validate login
    public String CheckLogin() {
        boolean valid = LoginDAO.validate(user, pwd);
        // if authentication is good, check for permissions levels.
        if (valid) {
            HttpSession session = SessionState.getSession();
            session.setAttribute("username", user);
            session.setAttribute("authenticated", "true");
            session.setMaxInactiveInterval(1800);
            System.out.println("login success");
            return "/auth/volunteers/userDashboard.xhtml?faces-redirect=true";
        } // failed, send back to login.
        else {
            System.out.println("login failure");
            return "/index.xhtml";
        }
    }

    public String logout() {
        HttpSession session = SessionState.getSession();
        session.invalidate();
        System.out.println("user was logged out");
        return "logout";
    }

    
    // spring security implementation    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
