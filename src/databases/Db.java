package databases;

import java.sql.*;

public class Db {
   public static void main(String[] args) {
      try{  
         Class.forName("com.mysql.cj.jdbc.Driver");
          
         Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/inpt","root","idkr2003");  

         Statement stmt=con.createStatement();
          
         ResultSet rs=stmt.executeQuery("select * from auth "); 

         while(rs.next())  
            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"   "+rs.getString(3));
             
         con.close();  
         
         }catch(Exception e){
            System.out.println(e);
         }
   }
}
