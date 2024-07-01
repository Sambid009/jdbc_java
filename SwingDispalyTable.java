
package demo;

import java.util.*;    // to take input from user
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

class DisplayExample extends JFrame{
    JTable jt;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton b1,b2;
    
    int selectedId = -1; // To store selected row ID
    
    public void setDispaly(){
        setLayout(new FlowLayout());
        jt = new JTable();
        l1 = new JLabel("Username");
        l2 = new JLabel("Password");
        l3 = new JLabel("Repassword");
        l4 = new JLabel("Gender");
        l5 = new JLabel("Course");
        l6 = new JLabel("Country");
         l7 = new JLabel(); // To show update status
        
        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);
        t4 = new JTextField(20);
        t5 = new JTextField(20);
        t6 = new JTextField(20);
        b1 = new JButton("Update");
        b2 = new JButton ("Delete");
       
        //set the size of JTable
        jt.setPreferredScrollableViewportSize(new Dimension(600,50));
        
        //creating scroll bar for table
        JScrollPane js = new JScrollPane(jt);
        
        //creating row and column of table: Default TableModel
        DefaultTableModel df = (DefaultTableModel)jt.getModel();
        
        //creating table column heading
        
        df.addColumn("id");
        df.addColumn("username");
        df.addColumn("password");
        df.addColumn("repassword");
        df.addColumn("gender");
        df.addColumn("course");
        df.addColumn("country");
        
        //fetching
        
        try{
            Connection conn = DBConnection.ConnectDb();
            String disQuery = "select * from tbl_reg";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(disQuery);
            
            while(rs.next()){
                //fetch data and show in table
                int id = rs.getInt("id");
                String un = rs.getString("username");
                String ps = rs.getString("password");
                String rp = rs.getString("repassword");
                String gn = rs.getString("gender");
                String cr = rs.getString("course");
                String cn = rs.getString("country");
                
                
                
                df.addRow(new Object[ ]{id,un,ps,rp,gn,cr,cn});
            }
            add(js);//adding scroll pane
            add(l1);add(t1);
            add(l2);add(t2);
            add(l3);add(t3);
            add(l4);add(t4);
            add(l5);add(t5);
            add(l6);add(t6);
            add(b1);add(b2);
            add(l7);
            
            setVisible(true);
            setSize(800,400);
            //setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            //when any row of table is selected then fetching such data of row into text field and ipdating data.
            //for this :when row is  clicked mouse event is generated and handled by muse listener
            //we required only on method evet so mouseadapter
            
            jt.addMouseListener(new MouseAdapter(){
            
                @Override
                public void mouseClicked(MouseEvent e){
                    //fetching selected row
                    int row = jt.getSelectedRow();
                    //fetching data of particular row and assigning in variable 
                    selectedId = (int) df.getValueAt(row, 0); // Fetching ID of selected row
                    String un = df.getValueAt(row,1 ).toString();
                    String ps = df.getValueAt(row,2 ).toString();
                    String rs = df.getValueAt(row,3 ).toString();
                    String gn =df.getValueAt(row,4 ).toString();
                    String cr = df.getValueAt(row,5 ).toString();
                    String cn = df.getValueAt(row,6 ).toString();
                    //assigning data into text field
                    t1.setText(un);
                    t2.setText(ps);
                    t3.setText(rs);
                    t4.setText(gn);
                    t5.setText(cr);
                    t6.setText(cn);
                }
            });
                    //-----------------------------------------------------------------------------------------------------
                               b1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                              if (selectedId == -1) {
                        l7.setForeground(Color.RED);
                        l7.setText("No row selected");
                        return;
                    }
 
                            String upun = t1.getText();
                            String upps = t2.getText();
                            String uprps = t3.getText();
                            String upgd = t4.getText();
                            String upco = t5.getText();
                            String upcu = t6.getText();

                            String upQuery="update tbl_reg set username=?,password=?,repassword=?,gender=?,course=?,country=? where id = ?";

                            try {
                                PreparedStatement ps2 = conn.prepareStatement(upQuery);

                                ps2.setString(1,upun);
                                ps2.setString(2,upps);
                                ps2.setString(3,uprps);
                                ps2.setString(4,upgd);
                                ps2.setString(5,upco);
                                ps2.setString(6,upcu);
                               ps2.setInt(7,selectedId);

                                int res1 = ps2.executeUpdate();

       if (res1 > 0) {
                            l7.setForeground(Color.GREEN);
                            l7.setText(res1 + " row updated");
                            df.setValueAt(upun, jt.getSelectedRow(), 1);
                            df.setValueAt(upps, jt.getSelectedRow(), 2);
                            df.setValueAt(uprps, jt.getSelectedRow(), 3);
                            df.setValueAt(upgd, jt.getSelectedRow(), 4);
                            df.setValueAt(upco, jt.getSelectedRow(), 5);
                            df.setValueAt(upcu, jt.getSelectedRow(), 6);
                        }

                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    });


            // Action listener for delete button
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedId == -1) {
                        l7.setForeground(Color.RED);
                        l7.setText("No row selected");
                        return;
                    }

                    String delQuery = "delete from tbl_reg where id=?";

                    try {
                        Connection conn = DBConnection.ConnectDb();
                        PreparedStatement ps3 = conn.prepareStatement(delQuery);
                        ps3.setInt(1, selectedId);

                        int res1 = ps3.executeUpdate();

                        if (res1 > 0) {
                            l7.setForeground(Color.GREEN);
                            l7.setText(res1 + " row deleted");
                            df.removeRow(jt.getSelectedRow());
                            // Clear text fields
                            t1.setText("");
                            t2.setText("");
                            t3.setText("");
                            t4.setText("");
                            t5.setText("");
                            t6.setText("");
                            selectedId = -1; // Reset selectedId
                        }

                    } catch (SQLException ex) {
                        l7.setForeground(Color.RED);
                        l7.setText("Delete failed: " + ex.getMessage());
                    }
                }
            });

        } catch (SQLException s) {
            System.out.println(s);
        }
    }
}

public class SwingDispalyTable {
    public static void main(String[] args) {
        DisplayExample de = new DisplayExample();
        de.setDispaly();
    }
}
