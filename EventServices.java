/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import org.primefaces.context.RequestContext;
import secure.unite.beans.SessionState;
import secure.unite.beans.taskServices;

import unite.login.beans.DataConnect;
import secure.unite.entities.Event;
 
/**
 *
 * @author CeDale
 */

@ManagedBean(name = "EventServices")
@ApplicationScoped
public class EventServices {
    
    @NotNull
    private String eventName;
    @NotNull
    private int event_id;
    @NotNull
    private int user_id;
    
    //Getters and Setters

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEvent_id() {
        return event_id;
    }
    
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    
    //Create a list of Events
    //NEED AN IF STATEMENT SOMEWHERE TO IDENTIFY THE CORRECT USER EVENTS
    
    public List<Event> gatherAllEvents(){
        
        List<Event> listOfEvents = new ArrayList<Event>();
        Connection connect = null;
        PreparedStatement ps = null;
        
        int volunteerID = Integer.parseInt(SessionState.getUserID());
        
        try{
            //Connect to DB
            connect = DataConnect.getConnection();
            
            //Query to get all events --- Validate if its the right user
            String query = "SELECT E_Name , E_Desc, E_StartDateTime, "
                    + "E_EndDateTime, E_Address, E_City, E_State, E_Zip, "
                    + "E_NeedStatus FROM Event"
                    + "WHERE User_ID = ? ;" ;
            
            ps = connect.prepareStatement(query);
            ps.setInt(1, volunteerID);
            ResultSet rs = ps.executeQuery();
            
            //build the list
            while (rs.next()){
                
                //need to make a new event list for each new record.
                Event selectedEvent = new Event();
                
                selectedEvent.setEventname(rs.getString("E_Name"));
                selectedEvent.setEventdesc(rs.getString("E_Desc"));
                selectedEvent.setEventstart(rs.getString("E_StartDateTime"));
                selectedEvent.setEventend(rs.getString("E_EndDateTime"));
                selectedEvent.setAddress(rs.getString("E_Address"));
                selectedEvent.setCity(rs.getString("E_City"));
                selectedEvent.setState(rs.getString("E_State"));
                selectedEvent.setZip(rs.getString("E_Zip"));
                listOfEvents.add(selectedEvent);
            }
        }catch (Exception e){
            System.out.println("Error on build --> " + e.getMessage());
        }finally{
            DataConnect.close(connect);
        }
        
        return listOfEvents;
    }
    
    public void closeAndUpdateEvent(String name, String desc, Date start, Date end, String addr, String city, String state, int zip){
        Connection con = null;
        PreparedStatement ps = null;
        java.util.Date now = new java.util.Date();
        java.sql.Timestamp cdt = new java.sql.Timestamp(now.getTime());
        
        int userID = Integer.parseInt(SessionState.getUserID());
        
        try{
            con = DataConnect.getConnection();
            
            //get the user events for the iser tp display on their dashboard
            String query = "UPDATE Event SET E_Name = ?, E_Desc = ?, E_StartDateTime = ?,"
                    + "E_EndDateTime = ?, E_Address = ?, E_City = ?, E_State = ?, E_Zip = ?"
                    + " WHERE User_ID = ?;";
            
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setDate(3, start);
            ps.setDate(4, end);
            ps.setString(5, addr);
            ps.setString(6, city);
            ps.setString(7, state);
            ps.setInt(8, zip);
            ps.setInt(9, userID);
            
            System.out.println(ps);
            
            int rs = ps.executeUpdate();
            
            if (rs == 1)
            {
               // let admin know the update worked.
                RequestContext requestContext = RequestContext.getCurrentInstance();
                FacesContext context = FacesContext.getCurrentInstance();
                
                // executes Javascript from backing bean
                requestContext.execute("PF('editEvent').hide()");                
                context.addMessage(null, new FacesMessage("Successful",  "Event updated") ); 
                
                //reload the tasks panel after closing task. -- not working
                // requestContext.update("adminTasks:Tasks"); 
            }
            
        }catch (SQLException e){
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    //DELETE AN EVENT ************
    public void DeleteEvent(){
        Connection con = null;
        PreparedStatement ps = null;
        java.util.Date now = new java.util.Date();
        java.sql.Timestamp cdt = new java.sql.Timestamp(now.getTime());
        
        int userID = Integer.parseInt(SessionState.getUserID());
        
        try{
            con = DataConnect.getConnection();
            
            //get the user events for the iser tp display on their dashboard
            String query = "DELETE FROM Event WHERE User_ID = ?;";
            
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            
            System.out.println(ps);
            
            int rs = ps.executeUpdate();
            
            if (rs == 1)
            {
               // let admin know the update worked.
                RequestContext requestContext = RequestContext.getCurrentInstance();
                FacesContext context = FacesContext.getCurrentInstance();
                
                // executes Javascript from backing bean
                requestContext.execute("PF('eventListDialog2').hide()");                
                context.addMessage(null, new FacesMessage("Successful",  "Event Cancelled") ); 
                
                //reload the tasks panel after closing task. -- not working
                // requestContext.update("adminTasks:Tasks"); 
            }
            
        }catch (SQLException e){
            Logger.getLogger(EventServices.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
}