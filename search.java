/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

/**
 *
 * @author Heather
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.*;
import org.primefaces.context.RequestContext;
import unite.login.beans.DataConnect;
import secure.unite.beans.adminDashboardBean;
import secure.unite.entities.Group;

@ManagedBean(name = "searchBean")
@ApplicationScoped

@ViewScoped
public class search {

    private String searchInput;
    private int zip;
    private String category;
    private List<Group> list = new ArrayList<Group>();
    private Group group;
    
    @PostConstruct
    public void init(){      
 
    }
    public int getZip(){
        
        return zip;
    }
    public void setZip(int zipcode){
       this.zip = zipcode;
    }
    public String getSearchInput() {
        return searchInput;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String cat){
        this.category = cat;
    }
    public void setSearchInput(String input) {
        this.searchInput = input;
    }
   

    public List<Group> searchGroups() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DataConnect.getConnection();
            // get the admin tasks for the admin to display on their dashboard
            String query = "SELECT * FROM  VolunteeringGroupInformationTable WHERE V_Group_Name LIKE ? AND V_Zip = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, searchInput);
            ps.setInt(2, zip);


            rs= ps.executeQuery();

            while (rs.next()){
                group = new Group();  
                group.setG_id(rs.getInt("V_Group_ID"));
                group.setG_name(rs.getString("V_Group_Name"));
                group.setG_active(rs.getInt("V_isActive"));
                group.setG_description(rs.getString("V_Desc"));
                group.setG_address(rs.getString("V_Address"));
                group.setG_city(rs.getString("V_City"));
                group.setG_state(rs.getString("V_State"));
                group.setG_zip(rs.getInt("V_Zip"));
                group.setG_category(rs.getString("V_Group_Category"));
                group.setCreatedDate(rs.getTimestamp("CreatedDate"));
                getList().add(group);
            } 

        } catch (SQLException ex) {
            Logger.getLogger(taskServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return getList();
    }
     public List<Group> searchCategory() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int searchCat = 0;
        if(category.equals("Animals"))
           searchCat = 1;
        if(category.equals("Medical"))
            searchCat = 2;
        if(category.equals("Construction"))
            searchCat = 3;
        if(category.equals("Education"))
            searchCat = 4;
        if(category.equals("Relief Groups")){
            searchCat = 5;
        }
        if(category.equals("Environment"))
            searchCat = 6;
        
        try {
            con = DataConnect.getConnection();
            // get the admin tasks for the admin to display on their dashboard
            String query = "SELECT * FROM  VolunteeringGroupInformationTable WHERE V_Group_Category = ? AND V_Zip = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, searchCat);
            ps.setInt(2, zip);
     
            rs= ps.executeQuery();

            while (rs.next()){
                group = new Group();  
                group.setG_id(rs.getInt("V_Group_ID"));
                group.setG_name(rs.getString("V_Group_Name"));
                group.setG_active(rs.getInt("V_isActive"));
                group.setG_description(rs.getString("V_Desc"));
                group.setG_address(rs.getString("V_Address"));
                group.setG_city(rs.getString("V_City"));
                group.setG_state(rs.getString("V_State"));
                group.setG_zip(rs.getInt("V_Zip"));
                group.setG_category(rs.getString("V_Group_Category"));
                group.setCreatedDate(rs.getTimestamp("CreatedDate"));
                getList().add(group);
            } 

        } catch (SQLException ex) {
            Logger.getLogger(taskServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return getList();
    }

    /**
     * @return the list
     */
    public List<Group> getList() {
        return list;
    }
    
}

