/*
 *
 * Description: This bean is repsonsible for rendering the Administration Dashboard.
 *              All data for dashboard must be in separate beans.
 *
 */
package secure.unite.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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
import javax.ejb.SessionContext;

@ManagedBean
@ViewScoped
public class volunteerDashboard implements Serializable {

    private DashboardModel model;

    // @Post construct is ran before the page is rendered.
    @PostConstruct
    public void init() {
        createDash();
    }

    private void createDash() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();

        column1.addWidget("Summary");
        column2.addWidget("events");
        column2.addWidget("Groups");
        column2.addWidget("Rewards");
        column2.addWidget("Chat");
        column2.addWidget("searching");

        model.addColumn(column1);
        model.addColumn(column2);
        
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

    }

    public void transferWidget(DashboardColumn from, DashboardColumn to, String WidgetId, int index) {

    }

    public String Logout() {
        // session needs to be destroyed
        HttpSession session = SessionState.getSession();
        session.invalidate();
        System.out.println("user was logged out from dashboard");
        return "/index.xhtml?faces-redirect=true";
    }

}
