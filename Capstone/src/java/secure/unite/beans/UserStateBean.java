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
import java.sql.Connection;
import java.sql.DriverManager;

public class UserStateBean {
    
    //private variables
    private String userid;
    private String userpw;
    
    public String GetUserid(){
        return userid;
    }
    
    public void SetUserid(String userid)
    {
        this.userid = userid;
    }
    
    public String GetUserpw()
    {
        return userpw;
    }
    
    public void Setuserpw(String userpw)
    {
        this.userpw = userpw;
    }
    
}
