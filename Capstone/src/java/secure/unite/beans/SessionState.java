/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Author:      Patrick Bartholomew
 * Date:        1/29/2016
 * Description: 
 *                  
 */



package secure.unite.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;



public class SessionState {

    public static HttpSession getSession()
    {
        return (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest()
    {
        return (HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static String getUserName()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        return session.getAttribute("username").toString();
    }
    
    public static String getUserID()
    {
        HttpSession session = getSession();
        
        if (session != null)
        {
            return(String) session.getAttribute("userid");
        }
        else
            return null;
    }
    
}
