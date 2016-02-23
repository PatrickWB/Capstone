/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Author:      Patrick Bartholomew
 * Date:        1/29/2016
 * Description: Managed Bean responsible for sending the sign-up data to the DB
 *
 */



package unite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import the DB connector
import unite.beans.DataConnect;

public class SignupDAO {

    
    //Signup.setupNewVolunteer(firstname, lastname, password, email, phone, address, city, state, zip);
    public static boolean CreateUser(String fname, String lname, String pwd, String email, String phone, String address, String city, String state, String zip)
    {
        // basic connector to the db.
        Connection con = null;
        // string that uses sql syntax.
        PreparedStatement ps = null;
        
        try
        {
            // attempt to establish connection to the DB.
            con = DataConnect.getConnection();
            
            // set up the basic user insert string. This string works for the UniteDB not the test db.
            //String InsertIntoSQL = "INSERT INTO users"
            //        + "(U_FName, U_LName, U_Password, U_Email, U_Phone, U_Address, U_City, U_State, U_Zip, U_Create_Date) VALUES"
            //        + "(?,?,?,?,?,?,?,?,?)";
            
            // simple test db
            String InsertIntoSQL = "INSERT INTO users (username, password, datetime) VALUES (?,?,?)";
            
            // prepared statement creates the user with the correct mysql commands.
            ps = con.prepareStatement(InsertIntoSQL);
            
            // for the test db
            ps.setString(1, fname);
            ps.setString(2, pwd);
            ps.setTimestamp(3, getCurrentTimeStamp());
            Integer zipcode = Integer.parseInt(zip);
            
            /* -- this is for the real db      
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, pwd);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, city);
            ps.setString(8, state);
            ps.setInt(9, zipcode);
            ps.setTimestamp(9, getCurrentTimeStamp());
            */
            
            int n = ps.executeUpdate();
            
                if (n == 1)
                {
                    return true;
                }                    
        }
        catch(Exception ex)
        {
            System.out.println("Volunteer Creation Error (SignupDAO Bean)--> " + ex.getMessage());
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
