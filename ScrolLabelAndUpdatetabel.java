
package demo;



import java.sql.*;

//using only result set it will point to first row
//then second row and so on
//using scrollable and updatable resultset we can move pointer to any row etc
//it can also be used to updated data of particular row


class ScrolUpdateEg{
    
   public void setScroll(){
       
       
       
       try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/primedb";
                        String susername = "root";
                        String spassword = "";
                        Connection conn = DriverManager.getConnection(url, susername, spassword);
                        
//                        return conn;

                        String  disquery = " select * from tbl_std";
                          
                        //creating scrollable and updatable result set
                        
                        int scroll = ResultSet.TYPE_SCROLL_SENSITIVE;
                        int updatetable = ResultSet.CONCUR_UPDATABLE;
                        int readonly = ResultSet.CONCUR_READ_ONLY;
                        
                        
                        Statement st = conn.createStatement(scroll,updatetable);
                        
                        //--------------------**OR**-----------------------
                        //Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); //maile use garni scrollable huna paryo bhanera halni
            
            
                        //executing query
                       ResultSet rs = st.executeQuery(disquery);
                      //fetching data of third row
                      rs.absolute(3);  //to fetch row of own choice 
                      System.out.println("Data of third row");
                System.out.println("id is " + rs.getInt("id"));
                System.out.println("name is " + rs.getString("iname"));
                System.out.println("gender is " + rs.getString("gender"));
                System.out.println("faculty is " + rs.getString("faculty"));
                System.out.println("fee is " + rs.getString("fee"));
            
                
                System.out.println("Data of last row");
                rs.last();
                 System.out.println("id is " + rs.getInt("id"));
                System.out.println("name is " + rs.getString("iname"));
                System.out.println("gender is " + rs.getString("gender"));
                System.out.println("faculty is " + rs.getString("faculty"));
                System.out.println("fee is " + rs.getString("fee"));
                
                
                //----updating first row data using scrollable
                System.out.println("Updating data of first row");
                rs.first();
                rs.updateString("fee", "100K");
                rs.updateString("faculty", "CSIT");
                rs.updateRow();  //execute
                
                      rs.first();  //to fetch row of first
                      System.out.println("Data of third row");
                System.out.println("id is " + rs.getInt("id"));
                System.out.println("name is " + rs.getString("iname"));
                System.out.println("gender is " + rs.getString("gender"));
                System.out.println("faculty is " + rs.getString("faculty"));
                System.out.println("fee is " + rs.getString("fee"));

          
                
        }catch(ClassNotFoundException c){
            System.out.println(c);
        }catch(SQLException s){
            System.out.println(s);
        }
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
   }
    
    
    
    
    
    
   
        
        
}























public class ScrolLabelAndUpdatetabel {
    public static void main(String[] args) {
        
        ScrolUpdateEg s = new ScrolUpdateEg();
        s.setScroll();
    }

}