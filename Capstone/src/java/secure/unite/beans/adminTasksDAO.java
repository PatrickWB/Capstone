/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import unite.beans.DataConnect;
import secure.unite.entities.Task;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="taskBean")

@ViewScoped
public class adminTasksDAO {

    List<Task> list = new ArrayList<Task>();
    Task task = null;
    
    
    // Task List Creation
    public List<Task> createTasksList(int user) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            // get the admin tasks for the admin to display on their dashboard
            String query = "SELECT T_Cat_Desc, Task_Severity_Level, DateTime_Created "
                    + "FROM AdminTasksTable a "
                    + "JOIN UniteAdminsTable b ON a.Admin_ID = b.Ad_ID "
                    + "JOIN AdminTaskCategoryTable c ON a.Task_ID = c.T_Cat_ID "
                    + "WHERE a.Admin_ID = 1;";

            ps = con.prepareStatement(query);
            // ps.setInt(1, user);
            ResultSet rs = ps.executeQuery();

            // build the list
            while (rs.next()) {
                // need to make a new tasklist entity class for each new record.
                task  = new Task();
                task.setTaskDesc(rs.getString("T_Cat_Desc"));
                task.setSeverityLevel(rs.getString("Task_Severity_Level"));
                task.setDateTimeCreated(rs.getString("DateTime_Created"));
                list.add(task);
            }
        } catch (Exception ex) {
            System.out.println("Login Error (LoginDAO Bean) --> " + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }
        return list;
    }
}
