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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.*;
import org.primefaces.context.RequestContext;
import secure.unite.entities.Task;
import unite.login.beans.DataConnect;

@ManagedBean(name = "eventServices")
@ApplicationScoped
public class eventServices {

    @NotNull
    private String res;
    @NotNull
    private int a_t_id;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public int getA_t_id() {
        return a_t_id;
    }

    public void setA_t_id(int a_t_id) {
        this.a_t_id = a_t_id;
    }

    // task resolution updater
    public void closeTask() {
        Connection con = null;
        PreparedStatement ps = null;
        java.util.Date now = new java.util.Date();
        java.sql.Timestamp cdt = new java.sql.Timestamp(now.getTime());
/*
        int adminID = Integer.parseInt(SessionState.getAdminID());

        try {
            con = DataConnect.getConnection();
            // get the admin tasks for the admin to display on their dashboard
            String query = "UPDATE AdminTasksTable "
                    + "SET DateTime_Resolved = ? , Resolution = ? , Resolved = ? "
                    + "WHERE A_T_ID = ?;";

            ps = con.prepareStatement(query);
            ps.setTimestamp(1, cdt);
            ps.setString(2, res);
            ps.setInt(3, 1);
            ps.setInt(4, a_t_id);
            //System.out.println(ps);
            int rs = ps.executeUpdate();
            
            if (rs == 1)
            {
                // let admin know the update worked.
                RequestContext requestContext = RequestContext.getCurrentInstance();
                FacesContext context = FacesContext.getCurrentInstance();
                // executes Javascript from backing bean
                requestContext.execute("PF('taskDialog').hide()");                
                context.addMessage(null, new FacesMessage("Successful",  "Task was closed") ); 
                
                //reload the tasks panel after closing task. -- not working
                // requestContext.update("adminTasks:Tasks");
            }
            else
            {
                // update failed
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(taskServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }

    // task list creator
    public List<Task> gatherAdminTasks() {

        List<Task> list = new ArrayList<Task>();
        Connection con = null;
        PreparedStatement ps = null;

        int adminID = Integer.parseInt(SessionState.getAdminID());

        try {
            con = DataConnect.getConnection();
            // get the admin tasks for the admin to display on their dashboard
            String query = "SELECT T_Cat_Desc, Task_Severity_Level, DateTime_Created, A_T_ID "
                    + "FROM AdminTasksTable a "
                    + "JOIN UniteAdminsTable b ON a.Admin_ID = b.Ad_ID "
                    + "JOIN AdminTaskCategoryTable c ON a.Task_ID = c.T_Cat_ID "
                    + "WHERE a.Admin_ID = ? AND Resolved <> 1 ;";

            ps = con.prepareStatement(query);
            ps.setInt(1, adminID);
            ResultSet rs = ps.executeQuery();

            // build the list
            while (rs.next()) {
                // need to make a new tasklist entity class for each new record.
                Task selectedTask = new Task();
                selectedTask.setAdminTaskId(rs.getInt("A_T_ID"));
                selectedTask.setTaskDesc(rs.getString("T_Cat_Desc"));
                selectedTask.setSeverityLevel(rs.getString("Task_Severity_Level"));
                selectedTask.setDateTimeCreated(rs.getString("DateTime_Created"));
                list.add(selectedTask);
            }

        } catch (Exception ex) {
            System.out.println("Login Error (LoginDAO Bean) --> " + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return list;
    }
}

