/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
