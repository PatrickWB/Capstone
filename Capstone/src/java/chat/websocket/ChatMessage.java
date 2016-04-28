/*
 *
 * Author:      Patrick Bartholomew
 * Date:        2/24/2016
 * Modified:    3/2/2016
 * Description: This bean is repsonsible for the chat message system
 *
 */

package chat.websocket;

import java.util.Date;

class ChatMessage {
    
    private String message;
    private String sender;
    private Date received;

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public Date getReceived() {
        return received;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceived(Date received) {
        this.received = received;
    }
    
    
    
}
