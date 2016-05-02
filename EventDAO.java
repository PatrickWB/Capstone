/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unite.DAO;


import static com.sun.xml.bind.util.CalendarConv.formatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import secure.unite.beans.SessionState;

// import the DB connector
import unite.login.beans.DataConnect;

public class EventDAO {

    //GroupSignup.setupGroup(groupname, groupdesc, groupcat, address, city, state, zip);
    public static boolean CreateEvent(String ename, String edesc, String estartdate, String eenddate, String eneed, String address, String city, String state, String zip)
    {
        // basic connector to the db.
        Connection con = null;
        // string that uses sql syntax.
        PreparedStatement ps = null;
        try
        {
            // attempt to establish connection to the DB.
            con = DataConnect.getConnection();
            String eventowner = SessionState.getUserID();
            //set up the basic user insert string. This string works for the UniteDB not the test db.
            String InsertIntoSQL = "INSERT INTO Event"
                    + "(E_Name, E_Desc, E_Cancelled, E_StartDateTime, E_EndDateTime, E_Need_Status, E_Address, E_City, E_State, E_Zip, User_ID) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?)";            
            
            // prepared statement creates the user with the correct mysql commands.
            ps = con.prepareStatement(InsertIntoSQL);
           
            Integer zipcode = Integer.parseInt(zip);
            Integer u_id = Integer.parseInt(eventowner);
                  
            ps.setString(1, ename);
            ps.setString(2, edesc);
            ps.setInt(3, 0);
            ps.setTimestamp (4, getStartDate());
            ps.setTimestamp(5, getEndDate());
            ps.setString(6, eneed);
            ps.setString(7, address);
            ps.setString(8, city);
            ps.setString(9, state);
            ps.setInt(10, zipcode);
            ps.setInt(11, u_id);
            
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
    
   
    private static java.sql.Timestamp getStartDate()
    {
        //java.util.Date starttime = new java.util.Date();
        //return new java.sql.Time(starttime.getTime());
        java.util.Date estartdate = new java.util.Date();
        return new java.sql.Timestamp(estartdate.getTime());
    }
    
    private static java.sql.Timestamp getEndDate()
    {
        java.util.Date eenddate = new java.util.Date();
        return new java.sql.Timestamp(eenddate.getTime());
    }
} 