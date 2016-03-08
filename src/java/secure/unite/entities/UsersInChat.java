
package secure.unite.entities;

public class UsersInChat {
    
    private int chatUserId;
    private String chatUserFirstName;
    private String chatUserLastName;
    private String chatUserEmail;

    public int getChatUserId() {
        return chatUserId;
    }

    public String getChatUserFirstName() {
        return chatUserFirstName;
    }

    public String getChatUserLastName() {
        return chatUserLastName;
    }

    public String getChatUserEmail() {
        return chatUserEmail;
    }

    public void setChatUserId(int chatUserId) {
        this.chatUserId = chatUserId;
    }

    public void setChatUserFirstName(String chatUserFirstName) {
        this.chatUserFirstName = chatUserFirstName;
    }

    public void setChatUserLastName(String chatUserLastName) {
        this.chatUserLastName = chatUserLastName;
    }

    public void setChatUserEmail(String chatUserEmail) {
        this.chatUserEmail = chatUserEmail;
    }   
    
    
}
