/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Author:      Kaitlin Russ
 * Date:        2/25/2016
 * Description: Managed Bean responsible for sending the group sign-up data to the DB
 *
 */
package unite.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;

// import the DB connector
import unite.login.beans.DataConnect;

public class GroupSignupDAO {

    //GroupSignup.setupGroup(groupname, groupdesc, groupcat, address, city, state, zip);
    public static boolean CreateGroup(String gname, String gdesc, String gcat, String address, String city, String state, String zip)
    {
        // basic connector to the db.
        Connection con = null;
        // string that uses sql syntax.
        PreparedStatement ps = null;
        System.out.println("Made it Here");
        try
        {
            // attempt to establish connection to the DB.
            con = DataConnect.getConnection();
            
            //set up the basic user insert string. This string works for the UniteDB not the test db.
            String InsertIntoSQL = "INSERT INTO VolunteeringGroupInformationTable"
                    + "(V_Group_Name, V_isActive, V_Desc, V_Address, V_City, V_State, V_Zip, V_Group_Category, CreatedDate) VALUES"
                    + "(?,?,?,?,?,?,?,?,?)";
            
            // simple test db
            //String InsertIntoSQL = "INSERT INTO users (username, password, datetime) VALUES (?,?,?)";
            
            // prepared statement creates the user with the correct mysql commands.
            ps = con.prepareStatement(InsertIntoSQL);
            
            // for the test db
            //ps.setString(1, fname);
            //ps.setString(2, pwd);
            //ps.setTimestamp(3, getCurrentTimeStamp());
            Integer zipcode = Integer.parseInt(zip);
            Integer category = Integer.parseInt(gcat);
                  
            ps.setString(1, gname);
            ps.setInt(2, 0);
            ps.setString(3, gdesc);
            ps.setString(4, address);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setInt(7, zipcode);
            ps.setInt(8, category);
            ps.setTimestamp(9, getCurrentTimeStamp());
            
            
            int n = ps.executeUpdate();
            
                if (n == 1)
                {
                    return true;
                }                    
        }
        catch(Exception ex)
        {
            System.out.println("Volunteer Group Creation Error (GroupSignupDAO Bean)--> " + ex.getMessage());
            return false;
        }
        finally
        {
            DataConnect.close(con);
        }
     
        return false;       
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp()
    {
        java.util.Date today= new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }
}