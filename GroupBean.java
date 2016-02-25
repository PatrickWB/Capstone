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
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import unite.beans.DataConnect;
import unite.beans.Group;


@ManagedBean(name = "groupbean")

@ViewScoped
public class GroupBean implements Serializable{
    private List<Group> list = new ArrayList<Group>();
    private Group group;
    //@ManagedProperty("#{Group}")
    @PostConstruct
    public void init(){      
      
    }
   /* public int getUserLevel(){
        //int id = 2;
        String email = "hreneeg@gmail.com";
        PreparedStatement ps = null;
        Connection con = null;
        int level = -1;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
            ps = con.prepareStatement("select * from UserInformationTable Where 'U_Email' = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
             while (rs.next())
            {
                level = rs.getInt("U_Reward_Level");
            }
        }catch(Exception e){
            System.out.println("Get list error--> " + e.getMessage());
        }
        finally{
            try{
                DataConnect.close(con);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return level;
    }
    */
    public List<Group> getGroupList(){
 
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/record", "root", "root");
            con =  DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
            String sql = "select * from VolunteeringGroupInformationTable where V_isActive != 0";
            ps= con.prepareStatement(sql); 
            rs= ps.executeQuery(); 
            System.out.println("Made it before while rs.next()!");
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
                list.add(group);
            } 
        }
        catch(Exception e){
             //e.printStackTrace();
             System.out.println("Get list error--> " + e.getMessage());
        }
        finally{
            try{
                DataConnect.close(con);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    }
    
