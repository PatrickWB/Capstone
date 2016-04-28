/**
 *
 * Author:      Patrick Bartholomew
 * Date:        1/29/2016
 * Modified:    2/25/2016
 * Description: This bean is responsible for creating the user HTTP session
 *              when the user is authenticated.
 *
 */
package secure.unite.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public class SessionState {

    //used to check the session and make sure a new one is not created.
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
    }

    // used to destroy the current session
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest();
    }

    // session user names for all three types
    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
    
    public static String getUserID(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("userID").toString();
    }
    
    public static String getAdminName(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("adminName").toString();
    }
    
    public static String getAdminID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("adminId").toString();
    }
    
    public static String getMasterID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("MasterID").toString();
    }
    
    public static String getMasterName(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("Mastername").toString();
    }

//    public static String getUserID() {
//        HttpSession session = getSession();
//
//        if (session != null) {
//            return (String) session.getAttribute("userid");
//        } else {
//            return null;
//        }
//    }

}
