/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Author:      Patrick Bartholomew
 * Date:        1/29/2016
 * Description: 
 *                  
 */



package unite.login.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;  

public class DataConnect {
    
    // open db connection
    public static Connection getConnection()
    {       
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
        }
        catch(ClassNotFoundException  e){
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return null;            
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection con = null;
        
        try{
            // con = DriverManager.getConnection("jdbc:mysql://192.168.1.211:3306/UniteDB", "root", "Test1234");   
            // DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "root", "Ahzi6Gei");
            con = DriverManager.getConnection("jdbc:mysql://uaf132602.ddns.uark.edu:3306/UniteDB", "dev", "devtest1234");
            return con;
        }
        catch(Exception ex){
            System.out.println("Database:getConnection() Error -->" + ex.getMessage());
            ex.printStackTrace();
            return null;            
        }
    }
    
    // close db connection
    public static void close(Connection con)
    {
        try{
            con.close();
        }
        catch(Exception ex)
        {
            
        }
    }
    
}
