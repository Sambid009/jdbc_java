 
package demo;
 
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 

class LoginExample extends JFrame{
    JLabel l1, l2, l3;
    JTextField t1;
    JPasswordField p1;
    JButton b1;
    public void setLogin(){
        l1 = new JLabel("Username ");
        l2 = new JLabel("Password ");
        l3 = new JLabel("Result ");
        t1 = new JTextField(20);
        p1 = new JPasswordField(20);
        b1 = new JButton("Submit");
        add(l1);add(t1);
        add(l2);add(p1);
        add(b1);add(l3);
        setVisible(true);
        setLayout(new FlowLayout());
        setSize(500, 500);
        setDefaultCloseOperation(3);
        //login
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                     Connection conn = DBConnection.ConnectDb();

                    //extracting form field
                    String user = t1.getText();
                    String pass = p1.getText();
                    if(user==""&&pass==""){
                        l3.setText("provided Credential");
                    }else{
       String disQuery = "select * from tbl_reg where username=? and password=?";
                        PreparedStatement ps = conn.prepareStatement(disQuery);
                        ps.setString(1, user);
                        ps.setString(2, pass);
                        ResultSet rs = ps.executeQuery();
                        if(rs.next()){
                //       l3.setForeground(Color.GREEN);
                        //   l3.setText("login SUccessful");
                         DisplayExample sd = new  DisplayExample();
                        sd.setDisplay();
                        //close the login page after display is open 
                        LoginExample.this.dispose();
                        }else{
                            l3.setForeground(Color.red);
                            l3.setText("username or password incorrect");
                        }
                    }
               
                }catch(SQLException s){
                    System.out.println(s);
                }
            }
        });
    }
}

public class LoginData {
    public static void main(String[] args) {
        LoginExample le1 = new LoginExample();
        le1.setLogin();
    }
}