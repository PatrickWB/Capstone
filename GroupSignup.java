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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import unite.beans.SignupDAO;

@ManagedBean
@ViewScoped
public class GroupSignup {

    // group creation input variables
    private String groupname;
    private String groupdesc;
    private String groupcat;
    private String address;
    private String city;
    private String state;
    private String zip;

    // Getters -- Setters
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupdesc() {
        return groupdesc;
    }

    public void setGroupdesc(String groupdesc) {
        this.groupdesc = groupdesc;
    }
    
    public String getGroupcat() {
        return groupcat;
    }
 
    public void setGroupcat(String groupcat) {
        this.groupcat = groupcat;
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

    // get data from groupsignup page.
    public String setupNewGroup() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("Made it Here 1");
        boolean valid = GroupSignupDAO.CreateGroup(groupname, groupdesc, groupcat, address, city, state, zip);

            if (valid) {                
                // let user know their account was created.                
                context.addMessage(null, new FacesMessage("Group Created", "Pending Admin Approval"));                
                return "success";
            } else {
                // don't redirect.  show the user an error message.
                context.addMessage(null, new FacesMessage("Failed"));
                System.out.println("Missing Information");
                return "failure";
            }
    }

    public static String Cancel() {
        System.out.println("Group Signup was Cancelled");
        return "cancel";
    }

}
