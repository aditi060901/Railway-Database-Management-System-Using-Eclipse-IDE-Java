package signup;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import login.Login;
import user.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sign {

	private JFrame frame;
	private JTextField uid;
	private JTextField upass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sign window = new Sign();
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
	public Sign() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 804, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   Railway Management System");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(97, 10, 541, 67);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Create Username");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setBounds(93, 114, 216, 80);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Create Password");
		lblNewLabel_2_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(93, 231, 198, 80);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		uid = new JTextField();
		uid.setColumns(10);
		uid.setBounds(387, 129, 269, 63);
		frame.getContentPane().add(uid);
		
		upass = new JTextField();
		upass.setColumns(10);
		upass.setBounds(387, 231, 269, 63);
		frame.getContentPane().add(upass);
		
		JButton btnNewButton = new JButton("    CREATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname,upas;
				uname=uid.getText();
				upas=upass.getText();
				try
				{
					stmt=con.prepareStatement("insert into user(username,pass)values(?,?)");
					stmt.setString(1, uname);
					stmt.setString(2, upas);
                    stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Registerd!");
					uid.setText("");
					upass.setText("");
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(135, 370, 137, 56);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("     BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User info=new User();
				User.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(473, 370, 137, 56);
		frame.getContentPane().add(btnBack);
	}

}
