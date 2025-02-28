package station;

import java.awt.EventQueue;
import java.sql.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import addtrain.Home;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Station {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Station window = new Station();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	PreparedStatement stmt;
	Connection con;
	private JTextField txtname;
	private JTextField txtenter;
	
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
	public Station() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 798, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   Railway Management System");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(126, 24, 541, 67);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Train", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(34, 129, 398, 233);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStationName = new JLabel("Station Name");
		lblStationName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStationName.setBounds(27, 90, 108, 35);
		panel.add(lblStationName);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(209, 92, 139, 35);
		panel.add(txtname);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Train Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(34, 385, 398, 67);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStationNo = new JLabel("   Station Name");
		lblStationNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStationNo.setBounds(10, 22, 118, 35);
		panel_1.add(lblStationNo);
		
		txtenter = new JTextField();
		txtenter.setColumns(10);
		txtenter.setBounds(212, 22, 139, 35);
		panel_1.add(txtenter);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sname;
			
				sname=txtname.getText();
			
				try {
					stmt=con.prepareStatement("insert into station(SName)values(?)");
					
					stmt.setString(1,sname);
			
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Station Added!");
					
					txtname.setText("");
					txtname.requestFocus();
					}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(581, 141, 113, 53);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home info=new Home();
				Home.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(581, 265, 113, 53);
		frame.getContentPane().add(btnBack);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sid=txtenter.getText();
				try
				{
				stmt=con.prepareStatement("select SName from station where SName ='?'");
				stmt.setString(1,sid);
				ResultSet rs=stmt.executeQuery();
				if(rs.next()==true)
				{
					String SName=rs.getString(1);
					
					txtname.setText(SName);
				}
				else
				{
					
					txtname.setText("");
				
					JOptionPane.showMessageDialog(null, "Entered Station Not present");
				}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}

			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.setBounds(581, 381, 113, 53);
		frame.getContentPane().add(btnSearch);
	}

}
