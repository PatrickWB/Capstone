/**
 *
 * @author Patrick
 */


package secure.unite.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import secure.unite.entities.ChatRoom;
import secure.unite.entities.Task;
import unite.login.beans.DataConnect;


@ManagedBean(name="chatRooms")
public class ChatRooms {
    
    private String room;
    private List<ChatRoom> list;
    
    
    @PostConstruct
    public void init() {
    
        //build the list of chat rooms available to the user.
         setList(new ArrayList<ChatRoom>());
        
        // query/loop to create rooms for chat.
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            // get the list of chat rooms that are active for the admin.
            String query = "SELECT V_Group_Name FROM UniteDB.VolunteeringGroupInformationTable WHERE V_isActive = 1;";

            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // build the list
            while (rs.next()) {
                // need to make a new tasklist entity class for each new record.
                ChatRoom room = new ChatRoom();
                room.setRoom(rs.getString("V_Group_Name"));
                list.add(room);
            }

        } catch (Exception ex) {
            System.out.println("ChatRoom list Creation Error (ChatRooms Bean) --> " + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }       

        setList(list);
    }

    public String getRoom() {
        return room;
    }
    
    public void setRoom(String room) {
        this.room = room;
    }

    public List<ChatRoom> getList() {
        return list;
    }

    public void setList(List<ChatRoom> list) {
        this.list = list;
    }

}
