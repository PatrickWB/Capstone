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



package unite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import secure.unite.beans.SessionState;

// import the DB connector
import unite.beans.DataConnect;

public class adminLoginDAO {
    
    public static boolean validate(String user, String pw)
    {
        Connection con = null;
        PreparedStatement ps = null;
        
        try
        {
            con = DataConnect.getConnection();
            // check results, look for an actual result.  should only be one from the query.
            // this will need to be updated to check for specific flags on the database, such as disbled, admin, ect..
            ps = con.prepareStatement("Select Ad_Email, Ad_ID from UniteAdminsTable where Ad_Email = ? and Ad_Password = ? and Ad_Disabled = ?");
            ps.setString(1, user);
            ps.setString(2, pw);
            // this is hard coded to make sure that disabled accounts cannot authenticate.
            ps.setInt(3, 0);
            
            ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return true;
                }
        }
        catch(Exception ex)
        {
            System.out.println("Login Error (adminLoginDAO Bean) --> " + ex.getMessage());
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
