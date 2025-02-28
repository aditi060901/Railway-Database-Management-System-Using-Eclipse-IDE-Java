package addperson;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import addtrain.Home;
import bookticket.TicketBooking;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Person {

	private JFrame frame;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtage;
	private JTextField txtgen;
	private JTextField txtenterid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Person window = new Person();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	PreparedStatement stmt;
	Connection con;
	public void connect()
	{
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","Example@2023#"); 
		 System.out.println("Connection succesfull");
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
	}
	
	
	
	
	/**
	 * Create the application.
	 */
	public Person() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 805, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 99, 323, 298);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Person", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("   PID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 38, 108, 35);
		panel.add(lblNewLabel);
		
		JLabel lblName = new JLabel("  Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(10, 103, 108, 35);
		panel.add(lblName);
		
		JLabel lblAge = new JLabel("  Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAge.setBounds(10, 160, 108, 35);
		panel.add(lblAge);
		
		JLabel lblGender = new JLabel("  Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGender.setBounds(10, 212, 108, 35);
		panel.add(lblGender);
		
		txtid = new JTextField();
		txtid.setBounds(154, 38, 139, 35);
		panel.add(txtid);
		txtid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(154, 103, 139, 35);
		panel.add(txtname);
		
		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(154, 160, 139, 35);
		panel.add(txtage);
		
		txtgen = new JTextField();
		txtgen.setColumns(10);
		txtgen.setBounds(154, 212, 139, 35);
		panel.add(txtgen);
		
		JLabel lblNewLabel_1 = new JLabel("   Railway Management System");
		lblNewLabel_1.setBounds(153, 10, 541, 67);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pid,pname,page,pgen;
				pid=txtid.getText();
				pname=txtname.getText();
				page=txtage.getText();
				pgen=txtgen.getText();
				try {
					stmt=con.prepareStatement("insert into person(PID,Name,Age,Gender)values(?,?,?,?)");
					stmt.setString(1,pid);
					stmt.setString(2,pname);
					stmt.setString(3,page);
					stmt.setString(4,pgen);
					stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Passenger Added!");
					txtid.setText("");
					txtname.setText("");
					txtage.setText("");
					txtgen.setText("");
					txtid.requestFocus();
					}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(30, 416, 113, 53);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TicketBooking info=new TicketBooking();
				TicketBooking.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(231, 416, 113, 53);
		frame.getContentPane().add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Person Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(393, 99, 370, 152);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("   Person ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 44, 107, 34);
		panel_1.add(lblNewLabel_2);
		
		txtenterid = new JTextField();
		txtenterid.addKeyListener(new KeyAdapter() {
		});
		txtenterid.setBounds(204, 44, 107, 34);
		panel_1.add(txtenterid);
		txtenterid.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pid,pname,page,pgen,penter;
				pid=txtid.getText();
				pname=txtname.getText();
				page=txtage.getText();
				pgen=txtgen.getText();
				penter=txtenterid.getText();
				try {
					stmt=con.prepareStatement("update person set PID=?,Name=?,Age=?,Gender=? where PID=?");
					stmt.setString(1,pid);
					stmt.setString(2,pname);
					stmt.setString(3,page);
					stmt.setString(4,pgen);
					stmt.setString(5,penter);
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Updated!");
					txtid.setText("");
					txtname.setText("");
					txtage.setText("");
					txtgen.setText("");
					txtid.requestFocus();
					}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(45, 100, 92, 42);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("SEARCH");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String idd=txtenterid.getText();
				try
				{
				stmt=con.prepareStatement("select PID,Name,Age,Gender from person where PID = ?");
				stmt.setString(1,idd);
				ResultSet rs=stmt.executeQuery();
				if(rs.next()==true)
				{
					String PID=rs.getString(1);
					String Name=rs.getString(2);
					String Age=rs.getString(3);
					String Gender=rs.getString(4);
					txtid.setText(PID);
					txtname.setText(Name);
					txtage.setText(Age);
					txtgen.setText(Gender);
				}
				else
				{
					txtid.setText("");
					txtname.setText("");
					txtage.setText("");
					txtgen.setText("");
					JOptionPane.showMessageDialog(null, "Entered PID Not present");
				}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(219, 100, 92, 42);
		panel_1.add(btnNewButton_1_1);
	}
}
