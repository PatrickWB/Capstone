/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 *
 * Author:      Patrick Bartholomew
 * Date:        2/23/2016
 * Description: Managed Bean to handle the login authentication process.
 *              Connects to the DB and verifies user is not disabled.
 *              Creates the user HTTP stateful session when authenticated.
 *              Contains the SQL driver manager using JDBC(java database connectivity)
 *
 */
package unite.login.beans;

//imports
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

// required to store the logged in user's session after login
import unite.DAO.adminLoginDAO;
import secure.unite.beans.SessionState;

@ManagedBean
@SessionScoped
public class adminLogin implements Serializable {

    //private variables
    @NotNull
    private String user;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // validate login
    public String CheckLogin() {
        int userID = adminLoginDAO.validate(user, pwd);
        // if authentication is good, check for permissions levels.
        if (userID != 0) {
            HttpSession session = SessionState.getSession();
            session.setAttribute("adminId", userID);
            session.setAttribute("adminName" , user);
            // set session interval to 15 minutes.  after that the session is to expire
            session.setMaxInactiveInterval(1800);
            System.out.println("login success for" + user);
            return "/auth/unite_admins/uniteAdminDashboard.xhtml?faces-redirect=true";
        } // failed, send back to login.
        else {
            System.out.println("login failure for " + user);
            return "/auth/unite_admins/adminLogin.xhtml?faces-redirect=true";
        }
    }

    public String Logout() {
        HttpSession session = SessionState.getSession();
        session.invalidate();
        System.out.println("unite admin " + user + " was logged out");
        // should redirect the user after logging them out
        return "/auth/unite_admins/adminLogin.xhtml?faces-redirect=true";
    }

}
