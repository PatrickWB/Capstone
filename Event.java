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
 *
 */
package unite.beans;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import unite.beans.SignupDAO;

@ManagedBean
@ViewScoped
public class Event {

    // group creation input variables
    private String eventname;
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

    // Getters -- Setters
    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdesc() {
        return eventdesc;
    }

    public void setEventdesc(String eventdesc) {
        this.eventdesc = eventdesc;
    }
    
    public String getEventdate() {
        return eventdate;
    }
 
    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }
    
    public String getEventstart() {
        return eventstart;
    }
 
    public void setEventstart(String eventstart) {
        this.eventstart = eventstart;
    }
    
    public String getEventend() {
        
        return eventend;
    }
 
    public void setEventend(String eventend) {
        this.eventend = eventend;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String getStartDate () {
        return startdatetime;
    }
    
    public void setStartDate(String evendate, String eventstart) {
        this.eventdate = eventdate;
        this.eventstart = eventstart;
        startdatetime = eventdate + " " + eventstart;
    }
    
    public String getEndDate(){
        return enddatetime;
    }
    
    public void setEndDate(String evendate, String eventend) {
        this.eventdate = eventdate;
        this.eventend = eventend;
        enddatetime = eventdate + " " + eventstart;
    }
            
    /*private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date newdate = new java.sql.Date(uDate.getTime());
        return newdate;
    } */

    // get data from groupsignup page.
    public String setupNewEvent() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println(startdatetime);
        System.out.println(enddatetime);
        boolean valid = EventDAO.CreateEvent(eventname, eventdesc, startdatetime, enddatetime, address, city, state, zip);

            if (valid) {                
                // let user know their account was created.                
                context.addMessage(null, new FacesMessage("Event Created"));
                System.out.println("Made it!");
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

}
