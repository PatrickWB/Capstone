
package unite.chat.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import secure.unite.beans.SessionState;
import secure.unite.entities.UsersInChat;
import unite.login.beans.DataConnect;
 
@ManagedBean
@ApplicationScoped
public class ChatUsers implements Serializable {
     
    private List<UsersInChat> chatUsers;
    private List<String> tempUser;
     
    @PostConstruct
    public void init() {
        chatUsers = getUsers();
    }
 
    public List<UsersInChat> getUsers() {        
        List<UsersInChat> chatUsersList = new ArrayList<UsersInChat>();
        Connection con = null;
        PreparedStatement ps = null;
        int adminID = Integer.parseInt(SessionState.getAdminID());
        try {
            con = DataConnect.getConnection();
            // get the admin tasks for the admin to display on their dashboard
            String query = "SELECT U_ID, U_FName, U_LName, U_Email FROM UserInformationTable WHERE U_LoggedIn = ? AND U_Disabled = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            // build the list
            while (rs.next()) {
                // need to make a new tasklist entity class for each new record.
                UsersInChat userschat = new UsersInChat();
                userschat.setChatUserId(rs.getInt("U_ID"));
                userschat.setChatUserFirstName(rs.getString("U_FName"));
                userschat.setChatUserLastName(rs.getString("U_LName"));
                userschat.setChatUserEmail(rs.getString("U_Email"));
                chatUsersList.add(userschat);
            }
        } catch (Exception ex) {
            System.out.println("User chat list generation Error (ChatUsers Class) --> " + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }                
        return chatUsersList;
    }         
    
//    public void remove(int userId) {
//        this.chatUsers.remove(userId);
//    }
//     
//    public void add(String user) {
//        this.tempUser.add(user);
//    }
//    
//         
//    public boolean contains(String user) {
//        return this.tempUser.contains(user);
//    }


}