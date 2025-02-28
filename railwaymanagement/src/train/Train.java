package train;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import addtrain.Home;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Train {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Train window = new Train();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	PreparedStatement stmt;
	Connection con;
	private JTextField txttno;
	private JTextField txttname;
	private JTextField txtenterno;
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
	public Train() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 808, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   Railway Management System");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(112, 22, 541, 67);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Train", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(48, 102, 397, 255);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTid = new JLabel("   TNO");
		lblTid.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTid.setBounds(32, 67, 111, 35);
		panel.add(lblTid);
		
		JLabel lblT = new JLabel("Train Name");
		lblT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblT.setBounds(32, 146, 108, 35);
		panel.add(lblT);
		
		txttno = new JTextField();
		txttno.setColumns(10);
		txttno.setBounds(216, 67, 139, 35);
		panel.add(txttno);
		
		txttname = new JTextField();
		txttname.setColumns(10);
		txttname.setBounds(216, 146, 139, 35);
		panel.add(txttname);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tid,tname;
				tid=txttno.getText();
				tname=txttname.getText();
			
				try {
					stmt=con.prepareStatement("insert into train(TNO,TName)values(?,?)");
					stmt.setString(1,tid);
					stmt.setString(2,tname);
			
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Added!");
					txttno.setText("");
					txttname.setText("");
					txttno.requestFocus();
					}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(578, 127, 113, 53);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home info=new Home();
				Home.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(578, 247, 113, 53);
		frame.getContentPane().add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Train Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(48, 367, 401, 89);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTrainNo = new JLabel("   Train No");
		lblTrainNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTrainNo.setBounds(10, 26, 111, 35);
		panel_1.add(lblTrainNo);
		
		txtenterno = new JTextField();
		txtenterno.setColumns(10);
		txtenterno.setBounds(223, 26, 139, 35);
		panel_1.add(txtenterno);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tid=txtenterno.getText();
				try
				{
				stmt=con.prepareStatement("select TNO,TName from train where TNO = ?");
				stmt.setString(1,tid);
				ResultSet rs=stmt.executeQuery();
				if(rs.next()==true)
				{
					String TNO=rs.getString(1);
					String TName=rs.getString(2);
					txttno.setText(TNO);
					txttname.setText(TName);
				}
				else
				{
					txttno.setText("");
					txttname.setText("");
				
					JOptionPane.showMessageDialog(null, "Entered TNO Not present");
				}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.setBounds(578, 373, 113, 53);
		frame.getContentPane().add(btnSearch);
	}
}
