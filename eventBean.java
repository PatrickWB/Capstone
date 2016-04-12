/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import secure.unite.entities.Event;
import unite.login.beans.DataConnect;
import unite.login.beans.Login;



/**
 *
 * @author Heather
 */


@ManagedBean(name = "eBean")

@ViewScoped
public class eventBean {
    private List<Event> eventList = new ArrayList<Event>();
    private Event event;
    Time start;
    Time end;
    int value = -1;
    @PostConstruct
    public void init(){      
      
    }
        public List<Event> getEventList() throws ClassNotFoundException, SQLException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        Event event = null;
        
        ResultSet preRS = null;
        PreparedStatement prePS = null;
        int groupID = 0;
        
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/record", "root", "root");
            con =  DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
            String preSQL = "select U_V_Group_ID from UserInformationTable WHERE U_Email = ?";
            prePS = con.prepareStatement(preSQL);
            prePS.setString(1, "hreneeg@gmail.com");
            preRS = prePS.executeQuery();
            while(preRS.next()){
                groupID = preRS.getInt("U_V_Group_ID");
            }
            
            String sql = "select * from Event where E_GroupID = ?";
            ps= con.prepareStatement(sql); 
            ps.setInt(1, groupID);
            rs= ps.executeQuery(); 
            //System.out.println("Made it before while rs.next()!");
            while (rs.next()){
                event = new Event();  
                event.seteGroup(rs.getInt("E_GroupID"));
                event.setEventname(rs.getString("E_Name"));
                event.setEventdesc(rs.getString("E_Desc"));
                //TODO Figure out how and add the times
                rs.getInt("E_Cancelled");
                
                event.setStartTime(rs.getTimestamp("E_StartDateTime"));
                event.setEndTime(rs.getTimestamp("E_EndDateTime"));
                event.setAddress(rs.getString("E_Address"));
                event.setCity(rs.getString("E_City"));
                event.setState(rs.getString("E_State"));
                event.setZip(rs.getString("E_Zip"));
                event.setEventNeedStatus(rs.getString("E_Need_Status"));
                eventList.add(event);
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
        return eventList;
    }
    private void signUp(int value){
        if(value != -1){
            //update EventSignup table \
            //eventID 
            //volunteerID
            //signupTime
            
            
        }
    }
    /**
     * @param eventList the eventList to set
     */
    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
