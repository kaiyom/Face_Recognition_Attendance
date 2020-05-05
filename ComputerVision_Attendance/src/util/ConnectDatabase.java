
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
   public Statement stm; 
   public ResultSet rs;
   
    Connection con;
   private final String driver="org.mysql.Driver";
   private final String root="jdbc:mysql://127.0.0.1/computer_vision";
   private final String user="root";
   private final String pass="";
   
   public void connect(){
       try{
           System.setProperty("jdbc.Driver", driver);
           con=DriverManager.getConnection(root,user,pass);
           System.out.println("Successful");
       }catch(SQLException e){
           System.out.println("Error...."+e);
       }
   }
   public void disconnect(){
       try{
           con.close();
       }catch(SQLException e){
           System.out.println("Error ...."+e);
       }
   }
   public void executesql(String sql){
       try{
           stm=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
           rs=stm.executeQuery(sql);
       }catch(Exception e){
           System.out.println("Error ...."+e);
       }
   }

}
