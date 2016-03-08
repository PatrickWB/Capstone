/*
 *
 * Author:      Patrick Bartholomew
 * Date:        2/25/2016
 * Modified:    2/29/2016
 * Description: This bean is repsonsible for recording the Adminstrator's notes
                to the db.
 *
 */
package secure.unite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.*;
import org.primefaces.context.RequestContext;
import unite.login.beans.DataConnect;
import secure.unite.beans.adminDashboardBean;


@ManagedBean
@ApplicationScoped
public class adminNotes {

    // varables-properties
    @NotNull
    private String noteName;
    @NotNull
    private String notes;
    private int noteId;

    // getters-setters
    public String getNoteName() {
        return noteName;
    }

    public String getNotes() {
        return notes;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void saveNote() {
        Connection con = null;
        PreparedStatement ps = null;
        java.util.Date now = new java.util.Date();
        java.sql.Timestamp cdt = new java.sql.Timestamp(now.getTime());

        int adminID = Integer.parseInt(SessionState.getAdminID());

        try {
            con = DataConnect.getConnection();
            // get the admin tasks for the admin to display on their dashboard
            String query = "INSERT INTO AdminNotesTable ( N_Name , N_Note , A_ID, N_CreatedDate ) "
                    + " VALUES (?,?,?, ?);";

            ps = con.prepareStatement(query);
            ps.setString(1, noteName);
            ps.setString(2, notes);
            ps.setInt(3, adminID);
            ps.setTimestamp(4, cdt);
            //System.out.println(ps);
            int result = ps.executeUpdate();

            if (result == 1) {
                // let admin know the update worked.
                RequestContext requestContext = RequestContext.getCurrentInstance();
                FacesContext context = FacesContext.getCurrentInstance();
                // executes Javascript from backing bean
                requestContext.execute("PF('taskDialog').hide()");
                context.addMessage(null, new FacesMessage("Successful", "Your note was saved!"));

                //reload the tasks panel after closing task. -- not working
                // requestContext.update("adminTasks:Tasks");
            } else {
                // let admin know the update worked.
                RequestContext requestContext = RequestContext.getCurrentInstance();
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Failure", "The note was not saved!"));                
            }

        } catch (SQLException ex) {
            Logger.getLogger(taskServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
