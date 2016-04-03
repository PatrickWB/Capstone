/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * Author:      Patrick Bartholomew
 * Date:        1/29/2016
 * Description: 
 *                  
 */

package unite.DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import the DB connector
import unite.login.beans.DataConnect;
import unite.login.beans.Login;


public class LoginDAO implements Serializable{
    
    public static boolean validate(String user, String pw)
    {
        // basic connector to the db.
        Connection con = null;
        // string that uses sql syntax.
        PreparedStatement ps = null;
        
        try
        {
            con = DataConnect.getConnection();
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

    private String datcon(String username, String password)
    {
        // load authenticated user.        
        // basic connector to the db.
        Connection con = null;
        // string that uses sql syntax.
        PreparedStatement ps = null;
        
        try
        {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select U_Email, U_Password from UserInformationTable where U_Email = ? and U_Password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
//                    Authorities auth = new Authorities();
//                    auth.setAuthority("volunteer");
                }
        }
        catch(Exception ex)
        {
            System.out.println("Login Error (LoginDAO Bean) --> " + ex.getMessage());            
        }
        finally
        {
            DataConnect.close(con);
        }
     
        // if the try catch block fails return a false result for validation
        return username;
    }
        
}
