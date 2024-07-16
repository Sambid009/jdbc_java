 
package demo;


   
import java.sql.*;
import javax.sql.rowset.*;

//row set is more flexible than result set
//it is default scrollable and updatable.
//it means we can point to any row and also update data directly

class RowSetExample{
    public void setRow(){
        try{
            
                    Class.forName("com.mysql.cj.jdbc.Driver");
        //initializing rowSet
        JdbcRowSet rs =RowSetProvider.newFactory().createJdbcRowSet();
        //creating connection
                                String url = "jdbc:mysql://localhost:3306/primedb";
                        String susername = "root";
                        String spassword = "";
        
        rs.setUrl(url);
        rs.setUsername(susername);
        rs.setPassword(spassword);
        
        //writing display query
        rs.setCommand("select* from tbl_std");
        rs.execute();
        
        //fetching third row data        
        rs.absolute(3);
            System.out.println("id is " + rs.getInt("id"));
                System.out.println("name is " + rs.getString("iname"));
                System.out.println("gender is " + rs.getString("gender"));
                System.out.println("faculty is " + rs.getString("faculty"));
                System.out.println("fee is " + rs.getString("fee"));
                
             // updating 1st row
            rs.first();
            rs.updateString("gender", "female");
            rs.updateString("iname", "Gita");
            rs.updateRow();
            
            // fetching updated details
            rs.first();
            System.out.println("------Updated row--------");
            System.out.println("name is " + rs.getString("iname"));
            System.out.println("gender is " + rs.getString("gender"));
            System.out.println("faculty is " + rs.getString("faculty"));
            System.out.println("fee is " + rs.getString("fee"));
            
            rs.last();
            System.out.println("-----Last row-----");
            System.out.println("name is " + rs.getString("iname"));
            System.out.println("gender is " + rs.getString("gender"));
            System.out.println("faculty is " + rs.getString("faculty"));
            System.out.println("fee is " + rs.getString("fee"));
            
            // deleting data of 2nd row
            System.out.println("--Deleting data of 2nd row--");
            rs.absolute(2);  // Move cursor to the second row
            rs.deleteRow();  // Delete the second row
            
        }catch(ClassNotFoundException e){
            System.out.println(e); 
            
        }catch(SQLException c){
            System.out.println(c);
            
        }

    }
}
public class RowSetDemo {
    
    public static void main(String[] args) {
        RowSetExample rr = new RowSetExample();
        rr.setRow();
    }
    
}
