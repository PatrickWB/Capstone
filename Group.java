/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Heather
 */
package unite.beans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.*;

@ManagedBean(name="Group")
@ViewScoped
public class Group {

    private int g_id;
    private String g_name;
    private int g_active;
    private String g_description;
    private String g_address;
    private String g_city;
    private String g_state;
    private int g_zip;
    private String g_category;
    private Date CreatedDate;
    
   /* public Group(String g_name, int g_active, String g_description, String g_address, String g_state, int g_zip, String g_category){
        
        this.g_name = g_name;
        this.g_active = g_active;
        this.g_description = g_description;
        this.g_address = g_address;
        this.g_state = g_state;
        this.g_zip = g_zip;
        this.g_category = g_category;        
    }
*/
    public void setCreatedDate(Date date){
        this.CreatedDate = date;
    }
    public Date getCreatedDate(){
        return CreatedDate;
    }
    public long getG_id() {
        
         return g_id;
    }
    public void setG_id(int g_id) {
        this.g_id = g_id;
    }
    public String getG_name() {
        return g_name;
    }
    public void setG_name(String g_name) {
        this.g_name = g_name;
    }
    public int getG_active(){
        return g_active;
    }
    public void setG_active(int g_active){
        this.g_active = g_active;
    }
    public String getG_description(){
        return g_description;
    }
    public void setG_description(String g_description){
        this.g_description = g_description;
    }
    public String getG_address() {
        return g_address;
    }
    public void setG_address(String address) {
        this.g_address = address;
    }
    public String getG_state() {
        return g_state;
    }
    public void setG_state(String state) {
        this.g_state = state;
    }
    public void setG_city(String city){
        this.g_city = city;
    }
    public String getG_city(){
        return g_city;
    }
    public int getG_zip(){
        return g_zip;
    }
    public void setG_zip(int g_zip){
        this.g_zip = g_zip;
    }
    public String getG_category(){
        return g_category;
    }
    public void setG_category(String g_category){
        this.g_category = g_category;

    }
    
}
