package secure.unite.beans;

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

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ejb.EJB;


@ManagedBean
@RequestScoped
public class UserState implements Serializable {
    
    //private variables
    private String username;
    private String userpassword;
    private String message;
        
    public UserState(){    
    }
    
    public UserState(String username, String userpassword)
    {
        this.username = username;
        this.userpassword = userpassword;
    }
    
    public String GetuserName()
    {
        return username;
    } 
    
   
}
