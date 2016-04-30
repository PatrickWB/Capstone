/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Heather
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import unite.login.beans.DataConnect;
import java.util.*;
import secure.unite.entities.User;

@ManagedBean(name = "profilebean")
@ViewScoped
public class ProfileBean implements Serializable{
    private List<User> ulist;
    private User user;
    private String newId;
    private String newFirst;
    private String newLast;
    private int newPhone;
    private String newAddress; 
    private String newState;
    private int newZip;

    @PostConstruct
    public void init(){      
      ulist = new ArrayList<User>();
    }
   
    public void updateProfile() throws SQLException{
       //Update the old value to the new one in DB
        Connection con = null;
        // string that uses sql syntax.
        PreparedStatement ps = null;
        con = DataConnect.getConnection();
        String InsertIntoSQL = "UPDATE UserInformationTable SET U_Email = ?, U_FName = ?, U_LName = ?, U_Phone = ?, U_Address = ?, U_State = ?, U_Zip = ? WHERE U_Email = ?";           
                 ps = con.prepareStatement(InsertIntoSQL);

         ps.setString(1, newId);
         ps.setString(2, newFirst);
         ps.setString(3, newLast);
         ps.setInt(4, newPhone);
         ps.setString(5, newAddress);
         ps.setString(6, newState);
         ps.setInt(7, newZip);
         ps.setString(8, SessionState.getUserName());
         ps.executeUpdate();
        
        
    }
    public User getUserInfo(){
                
        String email = SessionState.getUserName();
        PreparedStatement ps;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DataConnect.getConnection();
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/record", "root", "root");
            ps = con.prepareStatement("Select * from UserInformationTable where U_Email = ?");
            ps.setString(1, email);
            rs= ps.executeQuery(); 
            while (rs.next()){
                user = new User();  
                user.setuId(rs.getInt("U_ID"));
                user.setuEmail(rs.getString("U_Email"));
                user.setuFirst(rs.getString("U_FName"));
                user.setuLast(rs.getString("U_LName"));
                user.setuPass(rs.getString("U_Password"));
                user.setuPhone(rs.getString("U_Phone"));
                user.setuAddress(rs.getString("U_Address"));
                user.setuState(rs.getString("U_State"));
                user.setuZip(rs.getInt("U_Zip"));
                user.setuAdmin(rs.getInt("U_GO_Admin"));
                user.setuGname(rs.getString("U_GO_Group_Name"));
                user.setuCdate(rs.getTimestamp("U_Create_Date"));
                user.setuRpoints(rs.getInt("U_Reward_Points"));
                user.setuRlevel(rs.getInt("U_Reward_Level"));
                user.setuDisabled(rs.getInt("U_Disabled"));
                user.setuGid(rs.getInt("U_V_Group_ID"));
                user.setuFirstTime(rs.getInt("U_First_Time"));
               
            } 
        }
        catch(Exception e){
             //e.printStackTrace();
             System.out.println("Get list error--> " + e.getMessage());
        }
        finally{
            try{
                DataConnect.close(con);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
      return user;          
        
    }  

    /**
     * @return the newId
     */
    public String getNewId() {
        return newId;
    }

    /**
     * @param newId the newId to set
     */
    public void setNewId(String newId) {
        this.newId = newId;
    }

    /**
     * @return the newFirst
     */
    public String getNewFirst() {
        return newFirst;
    }

    /**
     * @param newFirst the newFirst to set
     */
    public void setNewFirst(String newFirst) {
        this.newFirst = newFirst;
    }

    /**
     * @return the newLast
     */
    public String getNewLast() {
        return newLast;
    }

    /**
     * @param newLast the newLast to set
     */
    public void setNewLast(String newLast) {
        this.newLast = newLast;
    }

    /**
     * @return the newPhone
     */
    public int getNewPhone() {
        return newPhone;
    }

    /**
     * @param newPhone the newPhone to set
     */
    public void setNewPhone(int newPhone) {
        this.newPhone = newPhone;
    }

    /**
     * @return the newAddress
     */
    public String getNewAddress() {
        return newAddress;
    }

    /**
     * @param newAddress the newAddress to set
     */
    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    /**
     * @return the newState
     */
    public String getNewState() {
        return newState;
    }

    /**
     * @param newState the newState to set
     */
    public void setNewState(String newState) {
        this.newState = newState;
    }

    /**
     * @return the newZip
     */
    public int getNewZip() {
        return newZip;
    }

    /**
     * @param newZip the newZip to set
     */
    public void setNewZip(int newZip) {
        this.newZip = newZip;
    }
        
}