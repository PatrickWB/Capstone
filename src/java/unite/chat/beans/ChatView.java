package unite.chat.beans;

import java.io.Serializable;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import secure.unite.beans.SessionState;

@ManagedBean(name="chatView")
@ViewScoped

public class ChatView implements Serializable {

    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();

    @ManagedProperty("#{chatUsers}")
    private ChatUsers users;

    private String privateMessage;

    private String globalMessage;

    private String username;

    private boolean loggedIn;

    private String privateUser;

    private final static String CHANNEL = "/{room}/";

    public ChatUsers getUsers() {
        return users;
    }

    public void setUsers(ChatUsers users) {
        this.users = users;
    }

    public String getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(String privateUser) {
        this.privateUser = privateUser;
    }

    public String getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }

    public String getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(String privateMessage) {
        this.privateMessage = privateMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void sendGlobal() {
        eventBus.publish(CHANNEL + "*", username + ": " + globalMessage);

        globalMessage = null;
    }

    public void sendPrivate() {
        eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);

        privateMessage = null;
    }

    // should auto login the user
    public void login() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
                        
        if (SessionState.getAdminName() != null)
        {
            username = SessionState.getAdminName();
            loggedIn = true;
        }                    
        else if (SessionState.getUserName() != null)
        {
            username = SessionState.getUserName();
            loggedIn = true;
        }
        else
        {
            loggedIn = false;
        }         
        users.add(username);
        requestContext.execute("PF('subscriber').connect('/" + username + "')");       

    }

    public void disconnect() {
        //remove user and update ui
        users.remove(username);
        RequestContext.getCurrentInstance().update("adminTasks:users");

        //push leave information
        eventBus.publish(CHANNEL + "*", username + " left the channel.");

        //reset state
        loggedIn = false;
        username = null;
    }

}
