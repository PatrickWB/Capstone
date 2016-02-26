/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 *
 * Author:      Patrick Bartholomew
 * Date:        2/24/2016
 * Description: this bean is responsible for gathering vital task information 
 *              and displaying this to the admin.
 *
 */
package secure.unite.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

//security imports
import secure.unite.beans.SessionState;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.SQLException;
import javax.ejb.SessionContext;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

// dashboard beans imports
import secure.unite.beans.adminTasksDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import secure.unite.entities.Task;

@ManagedBean
@SessionScoped
public class adminDashboard implements Serializable {

    private DashboardModel model;
    private List<Task> tasklist;

    // @Post construct is ran before the page is rendered.
    @PostConstruct
    public void init(){


        // If logic to check if the user has a session state and was logged in.  If not redirect to the login page.
        HttpSession session = SessionState.getSession();
        System.out.println(session.getAttributeNames());

        if (session != null && !session.isNew()) {
            model = new DefaultDashboardModel();
            DashboardColumn column1 = new DefaultDashboardColumn();
            DashboardColumn column2 = new DefaultDashboardColumn();

            column1.addWidget("Tasks");
            column2.addWidget("Tickets");

            model.addColumn(column1);
            model.addColumn(column2);
            

        } else {
            //redirect user and don't display any data.
            Deny();
        }
    }
    
    public String Deny() {
        return "redirect";
    }

    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }

    public void HandleReorder(DashboardReorderEvent event) {

        /*
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
        addMessage(message);
         */
    }

    public void transferWidget(DashboardColumn from, DashboardColumn to, String WidgetId, int index) {

    }

    public String Logout() {
        // session needs to be destroyed
        HttpSession session = SessionState.getSession();
        session.invalidate();
        System.out.println("admin was logged out from dashboard");
        return "logout";
    }

}
