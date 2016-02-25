/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package unite.beans;
import java.beans.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unite.beans.DataConnect;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import java.util.*;

public class DashboardDAO  {
    //return just the string containing the group a user is in
    private LinkedList<Group> GL;
    //@PostConstruct
   
    public void init(){
      //  GL = new LinkedList<Group>();
        
    }
    public void DashboardDAO(String userID) {

      //Connection con = null;
      //PreparedStatement ps = null;
      //String groupsList = "";

      //try{
        /*  con = DataConnect.getConnection();
          ps = con.prepareStatement("SELECT groupID FROM users WHERE username = ? ");
          ps.setString(1, userID);
            //write a sql procedure send parameters
          ResultSet rs = ps.executeQuery();
          if(rs.next()){
              groupsList = rs.getString("g_ID");
          }
          
          List<String> List = Arrays.asList(groupsList.split(","));
          ps = con.prepareStatement("SELECT * FROM groups WHERE G_ID = ?" );
          int j = 0;
          for (Iterator i = List.iterator(); i.hasNext(); ) { 
                          
              Integer e = Integer.parseInt(i.toString());
              ps.setInt(1, e);
              rs = ps.executeQuery();
              if(rs.next()){ 
                String name = rs.getString("g_ID");
                Integer active = rs.getInt("is_active");
                String description = rs.getString("g_description");
                String address = rs.getString("g_address");
                String state = rs.getString("g_state");
                Integer zip = rs.getInt("g_zip");
                String category = rs.getString("g_category");

                //Group Group1 = new Group(e, name, active, description, address, state, zip, category);
                GL.add(Group1);
                j++;
                  
            }
          }
         return GL ;
          
    } catch(Exception ex) {
            System.out.println("Login Error --> " + ex.getMessage());
  
    } finally {
         DataConnect.close(con);
        return GL;
        }
    }
*/
}
}
   
   
