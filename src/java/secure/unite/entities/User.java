/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Heather
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.*;

@ManagedBean(name="User")
@ViewScoped
public class User {
    private int uId;
    private String uEmail;
    private String uFirst;
    private String uLast;
    private String uPass;
    private String uPhone;
    private String uAddress;
    private String uState;
    private int uZip;
    private int uAdmin;
    private String uGname;
    private Date uCdate;
    private int uRpoints;
    private int uRlevel;    
    private int uDisabled;
    private int uGid;
    private int uFirstTime;
    
    
    public void setuId(int id){
        this.uId = id;
    }
    public void setuEmail(String email){
        this.uEmail = email;
    }
    public void setuFirst(String first){
        this.uFirst = first;
    }
    public void setuLast(String last){
        this.uLast = last;
    }
    public void setuPass(String pass){
        this.uPass = pass;
    }
    public void setuPhone(String phone){
        this.uPhone = phone;
    }
    public void setuAddress(String address){
        this.uAddress = address;
    }
    public void setuState(String state){
        this.uState = state;
    }
    public void setuZip(int zip){
        this.uZip = zip;        
    }
    public void setuAdmin(int admin){
        this.uAdmin = admin;
    }
    public void setuGname(String gname){
       this.uGname = gname;
    }
    public void setuCdate(Date date){
         this.uCdate = date;
    }
    public void setuRpoints(int rpoints){
        this.uRpoints = rpoints;
    }
    public void setuRlevel(int level){
        this.uRlevel = level;
    }
        
    public void setuDisabled(int disabled){
        this.uDisabled = disabled;
    }

    public void setuGid(int gid){
        this.uGid = gid;
    }
    public void setuFirstTime(int firsttime){
        this.uFirstTime = firsttime;
    }
    
    
    public int getuId(){
        
        return uId;
    }
    public String getuEmail(){
    
        return uEmail;
    }
    public String getuFirst(){
        
        return uFirst;
    }
   public String getuLast(){
        
        return uLast;
    }
    public String getuPass(){
       
       uPass = "";
       return uPass;
    }
    public String getuPhone(){
        
        return uPhone;
    }
    public String getuAddress(){
        
        return uAddress;
    }
    public String getuState(){
        
        return uState;
    }
    public int getuZip(){
        
        return uZip;
    }
    public int getuAdmin(){
        return uAdmin;
    }
    public String getuGname(){
        return uGname;
    }
    public Date getuCdate(){
        
        return uCdate;
    }
    public int getuRpoints(){
       return uRpoints;
    }
    public int getuRlevel(){
        return uRlevel;
    }
    public int getuDisabled(){
        return uDisabled;
    }
    public int getuGid(){
        
        return uGid;
    }
    public int getuFirstTime(){
        
        return uFirstTime;
    }
    
    
    
    
}