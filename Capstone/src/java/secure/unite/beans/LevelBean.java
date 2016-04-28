/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Heather
 */

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
import unite.login.beans.DataConnect;
import secure.unite.entities.Group;

@ManagedBean(name = "levelbean")
@ViewScoped
public class LevelBean implements Serializable{
    private int level = -1;
    @PostConstruct
    public void init(){      
       
    }
    public int getUserLevel(){
        //int id = 2;
        String email = SessionState.getUserName();
        PreparedStatement ps = null;
        Connection con = null;
        try{
            System.out.println("Made it to user level query block");
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
            ps = con.prepareStatement("select U_Reward_Level from UserInformationTable Where U_Email = ?");
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
    
    
    
}
