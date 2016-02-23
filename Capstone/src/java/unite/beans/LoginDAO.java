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



package unite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import the DB connector
import unite.beans.DataConnect;

public class LoginDAO {
    
    public static boolean validate(String user, String pw)
    {
        // basic connector to the db.
        Connection con = null;
        // string that uses sql syntax.
        PreparedStatement ps = null;
        
        try
        {
            con = DataConnect.getConnection();
            // check results, look for an actual result.  should only be one from the query.
            // this will need to be updated to check for specific flags on the database, such as disbled, admin, ect..
            // ps = con.prepareStatement("Select userid, password from users where username = ? and password = ?");
            ps = con.prepareStatement("Select U_Email, U_Password from UserInformationTable where U_Email = ? and U_Password = ?");
            ps.setString(1, user);
            ps.setString(2, pw);
            
            ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return true;
                }
        }
        catch(Exception ex)
        {
            System.out.println("Login Error (LoginDAO Bean) --> " + ex.getMessage());
            return false;
        }
        finally
        {
            DataConnect.close(con);
        }
     
        // if the try catch block fails return a false result for validation
        return false;       
    }
    
}
