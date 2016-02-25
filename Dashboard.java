/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
*/
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import unite.beans.DashboardDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
  /*  <p:dataList value="#{dataListView.cars3}" var="car" type="unordered" itemType="none" paginator="true" rows="10" styleClass="paginated">
        <f:facet name="header">
            Paginator
        </f:facet>
        <p:commandLink update=":form:carDetail" oncomplete="PF('carDialog').show()" title="View Detail" styleClass="ui-icon ui-icon-search" style="float:left;margin-right:10px">
            <f:setPropertyActionListener value="#{car}" target="#{dataListView.selectedCar}" />
            <h:outputText value="#{car.brand}, #{car.year}" />
        </p:commandLink>
        <h:outputText value="#{car.brand}, #{car.year}" style="display:inline-block"/>
    </p:dataList>

*/
@ManagedBean
@SessionScoped

public class Dashboard implements Serializable {
   
/* private Group selectedGroup;
    private LinkedList<Group> GL;
    private LinkedList<Group> groupList;
    @PostConstruct
    public void init(){
        List groupList = new LinkedList<String>();
        List selectedGroup = new LinkedList<String>();
    }
public List getGroups(String username){
        //List groupList = new Vector();
    //List groupList = DashboardDAO.DashboardDAO(username);
    
    return groupList;
}
public void setSelectedGroup(String group){
    this.selectedGroup = group;
}
public Group getSelectedGroup(){
    
    return selectedGroup;
}
*/
      
}
