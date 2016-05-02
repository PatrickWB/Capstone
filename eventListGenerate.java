/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import secure.unite.entities.Event;

/**
 *
 * @author CeDale
 */

@ManagedBean(name= "eventListGenerate")
@ViewScoped 
public class eventListGenerate implements Serializable {
    
    private List<Event> eventlist;
    private Event selectedEvent;
    private List<Event> selectedEvents;
    
    @ManagedProperty("#(EventServices)")
    private EventServices eventservices;
    
    @PostConstruct
    public void init() {
        //generate the list for xhtml
        eventlist = eventservices.gatherAllEvents();
    }

    public List<Event> getEventlist() {
        return eventlist;
    }

    public void setEventlist(List<Event> eventlist) {
        this.eventlist = eventlist;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public List<Event> getSelectedEvents() {
        return selectedEvents;
    }

    public void setSelectedEvents(List<Event> selectedEvents) {
        this.selectedEvents = selectedEvents;
    }

    public EventServices getEventservices() {
        return eventservices;
    }

    public void setEventservices(EventServices eventservices) {
        this.eventservices = eventservices;
    }
    
}