 
package demo;

 import java.util.*;
import java.sql.*;
//prepaid statement is used to manupulate dynamic data

class PrepaidExample{
    
    public void preparedInsert(){
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             
                    String URL = "jdbc:mysql://localhost:3306/primedb";
                    String susername = "root";
                    String spassword = "";
                    Connection conn = DriverManager.getConnection(URL, susername, spassword);
                    
                    //inserting data using prepaid statement//
                    //get input from user
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter your id");
                    int id =sc.nextInt();
                    System.out.println("Enter your name");
                    String name=sc.next();
                    System.out.println("Enter your gender");
                    String gender =sc.next();
                      System.out.println("Enter your Faculty");
                    String faculty =sc.next();
                     System.out.println("Enter your fee");
                    String fee =sc.next();
                    
                    //use prepaid statement to insert data
                    String insQuery = "insert into tbl_std values(?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(insQuery);
                    ps.setInt(1,id);
                    ps.setString(2,name);
                    ps.setString(3,gender);
                    ps.setString(4,faculty);
                    ps.setString(5,fee);
                    
                    //execute
                   int res =  ps.executeUpdate();
                   if (res>0){
                       System.out.println(res+ "Data inserted");
                   }
                    
            
        }
        catch(ClassNotFoundException c){
            System.out.println("c");
        }catch(SQLException c){
                System.out.println(c);  
            }
       
        
    }
    //displaying dataof database using prepared stmt
public void displayPrepared(){
    //display the info of student whose id is given by user
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/primedb";
        String susername ="root";
        String spassword = "";
         Connection conn=DriverManager.getConnection(url,susername,spassword);
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a id to dispaly data");
        int did = sc.nextInt();
        
        
        //writing query
        String dquery="SELECT*from tbl_std where id=?";
        PreparedStatement ps1=conn.prepareStatement(dquery);
        //executing query
        ps1.setInt(1, did);
        
        
        //data display garna result set chainxa
        ResultSet rs=ps1.executeQuery();
        while(rs.next()){
            System.out.println("name is "+rs.getString("iname"));
                System.out.println("gender is "+rs.getString("gender"));
                System.out.println("faculty is "+rs.getString("faculty"));
                System.out.println("fee is "+rs.getString("fee"));
                System.out.println("---------------------");
        }
        
    }catch(ClassNotFoundException c){
        System.out.println(c);
    }catch(SQLException  s){
        System.out.println(s);
    } 
}
//-------------------------------------------------------------------------

//updating datausing prepared statement 
public void updatePrepared(){
    try{
        //update name, gender and course of student to user choice whose id is also given by user
       Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/primedb";
        String susername ="root";
        String spassword = "";
        Connection conn=DriverManager.getConnection(url,susername,spassword);
        Scanner sc = new Scanner(System.in);
        System.out.println("enter id of a person"+"of which you want to update record" );
        int uid=sc.nextInt();
        System.out.println("enter updated name");
        String uname=sc.next();
        System.out.println("enter updated gender");
        String ugender=sc.next();
        System.out.println("enter updated facultty");
        String ufaculty=sc.next();
        
        
        
        //writing upadte query
          String upQuery="UPDATE tbl_std SET iname = ?, gender = ?, faculty = ? WHERE id = ?";
          
        PreparedStatement ps2= conn.prepareStatement(upQuery);
        ps2.setString(1, uname);
        ps2.setString(2, ugender);
        ps2.setString(3, ufaculty);
        ps2.setInt(4, uid);
        
        
        int res1=ps2.executeUpdate();
        if(res1>0){
          System.out.println(res1+"data updated" );  
        }
        else{
            System.out.println(res1+"not  updated" );  
        }
        
        
    }catch(ClassNotFoundException c){
        System.out.println(c);
    }catch(SQLException  s){
        System.out.println(s);
    } 
 
}

//--------------------------------------------------------------------------
//deleting using prepaid statement


public void delPrepared(){
    System.out.println("hi hhello");
    
    
}

    
}//close of class PrepaidExample

public class PrepaidStatementDemo {
    public static void main(String[] args) {
        PrepaidExample pe = new PrepaidExample();
        pe. updatePrepared();
        
        
    }
    
}
