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
 *              Contains the SQL driver manager using JDBC(java database connectivity)
 *
 */

package unite.beans;

//imports
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.*;
import secure.unite.beans.SessionState;
import unite.beans.madLoginDAO;

@ManagedBean
@SessionScoped
public class madLogin implements Serializable {

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
        boolean valid = madLoginDAO.validate(user, pwd);
        // if authentication is good, check for permissions levels.
        if (valid) {
            HttpSession session = SessionState.getSession();
            session.setAttribute("madAdmin", user);
            session.setMaxInactiveInterval(1800);
            System.out.println("login success");
            return "/auth/master_admins/maDashboard.xhtml?faces-redirect=true";
        } // failed, send back to login.
        else {
            System.out.println("login failure");
            return "/auth/master_admins/masterAdminsLogin.xhtml";
        }
    }

    public String logout() {
        HttpSession session = SessionState.getSession();
        session.invalidate();
        System.out.println("master admin was logged out");
        return "logout";
    }

}
