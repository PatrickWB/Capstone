/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Author:      Kaitlin Russ
 * Date:        2/25/2016
 * Description: Form that allows users to create a group in Unite.
 * Modified by: Heather Guyll
 * Date:        3/01/2016
 *
 */
package secure.unite.entities;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import unite.DAO.EventDAO;
import unite.DAO.SignupDAO;

@ManagedBean(name="eventBean")
@ViewScoped
public class Event {
    // group creation input variables
    
    
    //private int eGroup;
    //private int eId;
    private int eventGroup;
    private int eventID;
    private String eventname;
    private String eventNeedStatus;
    private String eventdesc;
    private String eventdate;
    private String eventstart;
    private String eventend;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String startdatetime;
    private String enddatetime;
    private Timestamp startTime;
    private Timestamp endTime;        
    
    // get data from groupsignup page.
    
    public String setupNewEvent() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println(getStartdatetime());
        System.out.println(getEnddatetime());
        boolean valid = EventDAO.CreateEvent(getEventname(), getEventdesc(), getStartdatetime(), getEnddatetime(), getEventNeedStatus(), getAddress(), getCity(), getState(), getZip());

        if (valid) {
            // let user know their account was created.                
            context.addMessage(null, new FacesMessage("Event Created", "Event Created Successfully"));
            return "success";
        } else {
            // don't redirect.  show the user an error message.
            context.addMessage(null, new FacesMessage("Event Creation Failed", "Missing Information"));
            System.out.println("Missing Information");
            return "failure";
        }
    }

    public static String Cancel() {
        System.out.println("Event Creation was Cancelled");
        return "cancel";
    }

    public String getEventname() {
        return eventname;
    }

    public String getEventNeedStatus() {
        return eventNeedStatus;
    }

    public String getEventdesc() {
        return eventdesc;
    }

    public String getEventdate() {
        return eventdate;
    }

    public String getEventstart() {
        return eventstart;
    }

    public String getEventend() {
        return eventend;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getStartdatetime() {
        return startdatetime;
    }

    public String getEnddatetime() {
        return enddatetime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setEventNeedStatus(String eventNeedStatus) {
        this.eventNeedStatus = eventNeedStatus;
    }

    public void setEventdesc(String eventdesc) {
        this.eventdesc = eventdesc;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public void setEventstart(String eventstart) {
        this.eventstart = eventstart;
    }

    public void setEventend(String eventend) {
        this.eventend = eventend;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime;
    }

    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getEventGroup() {
        return eventGroup;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventGroup(int eventGroup) {
        this.eventGroup = eventGroup;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }


}
