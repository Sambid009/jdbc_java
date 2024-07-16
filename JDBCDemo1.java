
package demo;
import java.sql.*;


class JDBCExample{
    
      public void createTable(){
          try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      String URL = "jdbc:mysql://localhost:3306/primedb";
      String username = "root";
      String password = "";
       Connection conn = DriverManager.getConnection(URL, username, password);

                          if (conn!= null) {
                            System.out.println("database connected");
                          } else {
                            System.out.println("Not connected");
                          }
                          
                          //creating table
//                          String tblQuery = "create table tbl_std("
//                                  + "id int primary key,"
//                                  + "iname varchar(50),gender varchar(50),"
//                                  + "faculty varchar(50),fee varchar(50))";
//                          //executing query: statement is used
//                          Statement st = conn.createStatement();
//                          st.execute(tblQuery);
//                          System.out.println("table created");


                        
                          
      conn.close();

    }   catch(ClassNotFoundException c){
                System.out.println(c);  
            }
          catch(SQLException c){
                System.out.println(c);  
            }
  
          
          
      }
      //creating method for inserting datas
      public void insertData(){
                  try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      String URL = "jdbc:mysql://localhost:3306/primedb";
      String username = "root";
      String password = "";
       Connection con = DriverManager.getConnection(URL, username, password);

                          if (con!= null) {
                            System.out.println("database connected");
                          } else {
                            System.out.println("Not connected");
                          }
                          


            String insQuery = "insert into tbl_std values(5,'Chris','Male','IIOT','20k')";
            //execute query
                             Statement st = con.createStatement();
                             //executeUpdate return int value 0 or 1
                             //1 refers to 1 record inserted and 0 is no record inserted
                         
                         int res = st.executeUpdate(insQuery);
                         if(res>0){
                             System.out.println(res + "row inserted");
                         }else{
                             System.out.println("Data not inserted");
                         }
                          
      con.close();

    }   catch(ClassNotFoundException c){
                System.out.println(c);  
            }
          catch(SQLException c){
                System.out.println(c);  
            }
          
      }
      //fetching data from database
      public void dispalyData(){
          try{
          Class.forName("com.mysql.cj.jdbc.Driver");

      String URL = "jdbc:mysql://localhost:3306/primedb";
      String username = "root";
      String password = "";
       Connection con = DriverManager.getConnection(URL, username, password);

                          if (con!= null) {
                            System.out.println("database connected");
                          } else {
                            System.out.println("Not connected");
                          }
                          


          String disQuery = "select* from tbl_std";
            
                             Statement st = con.createStatement();
                             //to fetch data executeQuery is used that reyurn object of Resultset
                             
                         
                ResultSet rs= st.executeQuery(disQuery);
                //in result set all the row will be extracted to show one data one data at time rs.next() is used
                while(rs.next()){
                    System.out.println("id is "+ rs.getInt("id"));
                    System.out.println("name is "+ rs.getString("iname"));
                    System.out.println("gender is "+ rs.getString("gender"));
                    System.out.println("faculty is "+ rs.getString("faculty"));
                    System.out.println("fee is "+ rs.getString("fee"));
                }
                 con.close();
      

    }   catch(ClassNotFoundException c){
                System.out.println(c);  
            }
          catch(SQLException c){
                System.out.println(c);  
            }
          
      }
      
      //updating data: update name to hari gender to Male of person whose id is 1;
      public void updateData(){
                 try{
          Class.forName("com.mysql.cj.jdbc.Driver");

      String URL = "jdbc:mysql://localhost:3306/primedb";
      String username = "root";
      String password = "";
       Connection con = DriverManager.getConnection(URL, username, password);

                          if (con!= null) {
                            System.out.println("database connected");
                          } else {
                            System.out.println("Not connected");
   
                          }
                          
                          
                          


          String upQuery = "update tbl_std  set iname='hari',gender='male' where id=1";
            
                             Statement st = con.createStatement();
                             int res1= st.executeUpdate(upQuery);
                             if(res1>0){
                                 System.out.println(res1+"data updated");
                             }
                             
                             
                         

                 con.close();
      

    }   catch(ClassNotFoundException c){
                System.out.println(c);  
            }
          catch(SQLException c){
                System.out.println(c);  
            }
 
}
      public void deleteData(){
          //delete  id of student which 
                         try{
          Class.forName("com.mysql.cj.jdbc.Driver");

      String URL = "jdbc:mysql://localhost:3306/primedb";
      String username = "root";
      String password = "";
       Connection con = DriverManager.getConnection(URL, username, password);

                          if (con!= null) {
                            System.out.println("database connected");
                          } else {
                            System.out.println("Not connected");
                          }
                          
                          
                          


          String delQuery = "delete from tbl_std  where  id=2";
            
                             Statement st = con.createStatement();
                             int res1= st.executeUpdate(delQuery);
                             if(res1>0){
                                 System.out.println(res1+"data deleted");
                             }
                             
                             
                         

                 con.close();
      

    }   catch(ClassNotFoundException c){
                System.out.println(c);  
            }
          catch(SQLException c){
                System.out.println(c);  
            }
          
      }
}



public class JDBCDemo1 {
    public static void main(String[] args) {
        JDBCExample e1 = new JDBCExample();
       // e1.createTable();
       e1.insertData();
       //e1.dispalyData();
       //e1.updateData();
     //  e1.deleteData();
    }
    
}
