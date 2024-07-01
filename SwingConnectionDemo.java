
package demo;


import java.util.*;    // to take input from user
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SwingForm extends JFrame {
    
    JLabel l1, l2, l3, l4, l5, l6, lr;
    JTextField t1;
    JPasswordField p1, p2;
    JRadioButton r1, r2;
    JCheckBox c1, c2, c3;
    JComboBox<String> cb1;
    JButton b1;
    
    public void setSwingForm() {
        
        l1 = new JLabel("Username");
        l2 = new JLabel("Password");
        l3 = new JLabel("Repassword");
        l4 = new JLabel("Gender");
        l5 = new JLabel("Course");
        l6 = new JLabel("Country");
        lr = new JLabel("Result");
        t1 = new JTextField(20);
        p1 = new JPasswordField(20);
        p2 = new JPasswordField(20);
        
        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        
        c1 = new JCheckBox("Python");
        c2 = new JCheckBox("Java");
        c3 = new JCheckBox(".Net");
        
        String[] items = {"Nepal", "USA", "India", "Other"};
        cb1 = new JComboBox<>(items);
        b1 = new JButton("Submit");

        setLayout(new FlowLayout());

        add(l1);
        add(t1);
        add(l2);
        add(p1);
        add(l3);
        add(p2);
        add(l4);
        add(r1);
        add(r2);
        add(l5);
        add(c1);
        add(c2);
        add(c3);
        add(l6);
        add(cb1);
        add(lr);  // Add the result label
        add(b1);

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Extract all the data from fields
                String uname = t1.getText();
                String pass = new String(p1.getPassword());
                String repass = new String(p2.getPassword());
                String gender = "";

                if (r1.isSelected()) {
                    gender = r1.getText();
                } else {
                    gender = r2.getText();
                }

                String course = "";
                if (c1.isSelected()) {
                    course += c1.getText() + " ";
                }

                if (c2.isSelected()) {
                    course += c2.getText() + " ";
                }

                if (c3.isSelected()) {
                    course += c3.getText() + " ";
                }

                String country = (String) cb1.getSelectedItem();

                // Database connection
                try {
                    // Validation
                    if (uname.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                        lr.setText("Fill the complete form");
                        lr.setFont(new Font("Serif", Font.BOLD, 18));
                        lr.setForeground(Color.red);
                    } else {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/primedb";
                        String susername = "root";
                        String spassword = "";
                        Connection conn = DriverManager.getConnection(url, susername, spassword);

                        // Here, you can write your SQL query to insert data into the database
                        
                        //insert operation
                        String insQuery = "insert into tbl_reg(username,password,repassword,gender,course,country)value(?,?,?,?,?,?)";
                        PreparedStatement ps = conn.prepareStatement(insQuery);
                        
                        ps.setString(1,uname);
                        ps.setString(2,pass );
                        ps.setString(3, repass);
                         ps.setString(4, gender);
                          ps.setString(5, course);
                           ps.setString(6, country);
                           
                          int res = ps.executeUpdate();
                          
                          if(res>0){
                              lr.setForeground(Color.GREEN);
                              lr.setText(res + "data inserted");
                              
                          }
                        

                        conn.close();
                    }
                    

                } catch (ClassNotFoundException c) {
                    c.printStackTrace();
                } catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        });
    }
}

public class SwingConnectionDemo {
    public static void main(String[] args) {
        SwingForm s = new SwingForm();
        s.setSwingForm();
    }
}
