/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

import static com.sun.javafx.logging.PulseLogger.addMessage;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import secure.unite.entities.Event;
import unite.login.beans.DataConnect;
import unite.login.beans.Login;
import secure.unite.beans.SessionState;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import secure.unite.entities.User;

/**
 *
 * @author Heather
 */


@ManagedBean(name = "eBean")

@ViewScoped
public class eventBean {
    private List<Event> eventList = new ArrayList<>();
    private List<Event> userEvents = new ArrayList<>();
    private Event userEvent;
    private Event event;
    private Event selectedEvent;
    
    private User tempUser;
    
    private List<Event> joinedEvents = new ArrayList<>();
    Time start;
    Time end;
    int value = -1;
    int groupID = 0;
    
    
    private List<User> eventUsers = new ArrayList<>();
     
     
    @PostConstruct
    public void init(){      
       
        
    }
    
    public List<User> getEventUsers(){
        //Select from EventSignup where
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        userEvent = null;
        
       
        try{
            String sql = "SELECT VolunteerID FROM EventSignup WHERE eventID = ?";       
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, 3);
            rs = ps.executeQuery();
            while(rs.next()){
                tempUser = new User();
                int uId = rs.getInt("volunteerID");
                //Query volunteer database and retrieve name
                String sql2 = "SELECT U_FName, U_LName FROM UserInformationTable WHERE U_ID = ?";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setInt(1, uId);
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()){
                    String first = rs2.getString("U_FName");
                    String last = rs2.getString("U_LName");
                    tempUser.setuFirst(first);
                    tempUser.setuLast(last);
                    eventUsers.add(tempUser);
                }
                    
                
            }
            
        }catch(Exception ex){
                
        }
        return eventUsers;
    }
    public List<Event> getUserEvents() throws SQLException{
         
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        userEvent = null;
        
       
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
     
            String sql = "Select * FROM Event WHERE User_ID = ? AND E_CheckIn = 0 AND E_Cancelled = 0";
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(SessionState.getUserID()));
            rs = ps.executeQuery();
            while(rs.next()){
                userEvent = new Event();  
                userEvent.seteId(rs.getInt("E_ID"));
                userEvent.seteGroup(rs.getInt("E_GroupID"));
                userEvent.setEventname(rs.getString("E_Name"));
                userEvent.setEventdesc(rs.getString("E_Desc"));
                rs.getInt("E_Cancelled");
                userEvent.setStartTime(rs.getTimestamp("E_StartDateTime"));
                userEvent.setEndTime(rs.getTimestamp("E_EndDateTime"));
                userEvent.setAddress(rs.getString("E_Address"));
                userEvent.setCity(rs.getString("E_City"));
                userEvent.setState(rs.getString("E_State"));
                userEvent.setZip(rs.getString("E_Zip"));
                userEvent.setEventNeedStatus(rs.getString("E_Need_Status"));
                userEvents.add(userEvent);
        }
            
        }catch(ClassNotFoundException | SQLException ex){
                
                
                
                
        }
        
        
        return userEvents;
    }
        public List<Event> getEventList() throws ClassNotFoundException, SQLException {
        int flag = 0;
        

        //4/21
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        event = null;
        
        ResultSet preRS = null;
        PreparedStatement prePS = null;

        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
            //String joinSQL = "SELECT * FROM Event INNER JOIN EventSignup ON E_ID.Events = eventID.EventSignup WHERE Eventsignup.volunteerID = ?";
            String preSQL = "select U_V_Group_ID from UserInformationTable WHERE U_ID = ?";
            prePS = con.prepareStatement(preSQL);
            prePS.setInt(1, Integer.parseInt(SessionState.getUserID()));
            preRS = prePS.executeQuery();
            while(preRS.next()){
                groupID = preRS.getInt("U_V_Group_ID");
            }
            
            
            
         /*
           Grab all events from event signup where the volunteer ID is not signed up
           If it is a duplicate, disregard
            
         */
         int[] events;
         int[] flagged = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         int i = 0;
         events = new int[10];
         int id = 0;
         boolean flag1 = false;
         int UID = Integer.parseInt(SessionState.getUserID());
         
         String pSQL = "SELECT eventID, volunteerID FROM EventSignup";
         PreparedStatement prep = con.prepareStatement(pSQL);
        int j = 0;
         rs = prep.executeQuery();
         while(rs.next()){
             //if volunteerID is signed up for the event, flag it by putting it in flagged[]
             id = rs.getInt("eventID");
             int u = rs.getInt("volunteerID");
         
                 if(u == UID){
                     //flag event id
                     flagged[j] = id;
                     j++;
                 }
                 
             } 
                 
             
                 //Go through the list of events and, if it's already in the list, do not show it
                 //But still flag it
               /*  if(events[i] == id){
                     flag1 = 1;
                     
                 }
              int u = rs.getInt("volunteerID");
              //if I am a part of the event, do not show it
                if(u == UID){
                    flag1 = 1;
                }
              
             }
             if(flag1 == 0){
                events[i] = id;
             }
             i++;
                 */
               
         
         
         /*
            For each of the event Ids within events[], grab the information only if the group ID matches one the user is in
         */
         
            String sql = "SELECT * FROM Event WHERE E_GroupID = ?";
            ps= con.prepareStatement(sql); 
            ps.setInt(1, 1);       
            rs= ps.executeQuery(); 
            while (rs.next()){
                flag1 = false;
                event = new Event();  
                event.seteId(rs.getInt("E_ID"));
                event.seteGroup(rs.getInt("E_GroupID"));
                event.setEventname(rs.getString("E_Name"));
                event.setEventdesc(rs.getString("E_Desc"));
                rs.getInt("E_Cancelled");
                event.setStartTime(rs.getTimestamp("E_StartDateTime"));
                event.setEndTime(rs.getTimestamp("E_EndDateTime"));
                event.setAddress(rs.getString("E_Address"));
                event.setCity(rs.getString("E_City"));
                event.setState(rs.getString("E_State"));
                event.setZip(rs.getString("E_Zip"));
                event.setEventNeedStatus(rs.getString("E_Need_Status"));
                //Go through flagged list, check if the event is not flagged
                //If it is, set the flag1 value to 1
                for(int k = 0; k < 10; k++){
                    if(event.geteId() == flagged[k]){
                        flag1 = true;
                    }
                   
                }
                if(!flag1){
                    eventList.add(event);
                }
            } 
        }
        
        catch(ClassNotFoundException | SQLException e){
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
       // return eventList;
            return eventList;
        }

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void signUp() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
            int  userId = Integer.parseInt(SessionState.getUserID());
            java.util.Date now = new java.util.Date();
            java.sql.Timestamp cdt = new java.sql.Timestamp(now.getTime());

            String x = "INSERT INTO EventSignup"
                    + "(eventID, volunteerID, SignupTime, attend, points) VALUES"
                    + "(?,?,?,?,?)";         

            PreparedStatement ps = con.prepareStatement(x);
            ps.setInt(1, selectedEvent.geteId());
            ps.setInt(2, userId);
            ps.setTimestamp(3, cdt);
            ps.setInt(4, 1);
            ps.setInt(5, 10);
            int valid = ps.executeUpdate();
            
            FacesContext context = FacesContext.getCurrentInstance();

            if (valid != 0) {                
                context.addMessage(null, new FacesMessage("Joined Event", "Event sign up was successful"));

            } else {
                context.addMessage(null, new FacesMessage("Event signup Failed", "Missing Information"));
            }
    }
    /**
     * @param eventList the eventList to set
     */
    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

}
