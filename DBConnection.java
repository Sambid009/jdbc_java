
package demo;
import java.sql.*;

public class DBConnection {
    
    public static Connection ConnectDb(){
        try{
            
                           Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/primedb";
                        String susername = "root";
                        String spassword = "";
                        Connection conn = DriverManager.getConnection(url, susername, spassword);
                        return conn;
            
        }
            catch(ClassNotFoundException c){
                System.out.println(c);  
            }
          catch(SQLException c){
                System.out.println(c);  
            }
        return null;
    }
    public static void main(String[] args) {
        
    }
    
}
