
package unite.chat.beans;

import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import javax.inject.Inject;
import javax.servlet.ServletContext;
import secure.unite.beans.SessionState;
 
@PushEndpoint("/chat")
@Singleton
public class ChatResource {
 
    private final Logger logger = LoggerFactory.getLogger(ChatResource.class);
 
//    @PathParam("room")
//    private String room = "chat";
 
//    @PathParam("user")
//    private String username = SessionState.getAdminName();
 
    @Inject
    private ServletContext ctx;
 
    @OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus) {
        logger.info("OnOpen {}", r); 
        System.out.println("open chat room value ");
        //eventBus.publish("has entered the room");
    }
// 
    @OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus) {
        ChatUsers users= (ChatUsers) ctx.getAttribute("chatUsers");
        //users.remove(username);         
        System.out.println("close chat room value ");
        //eventBus.publish("left the room");
    }
 
    @OnMessage(decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
    public Message onMessage(Message message) {
        return message;
    }
 
}
