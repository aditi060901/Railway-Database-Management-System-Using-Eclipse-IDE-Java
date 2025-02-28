package user;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import main.MainScreen;
import signup.Sign;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class User {

	private JFrame frame;
	private JTextField uid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					User window = new User();
					window.frame.setVisible(true);
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	PreparedStatement stmt;
	Connection con;
	private JPasswordField pss;
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
	public User() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 802, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   Railway Management System");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(110, 10, 541, 67);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("     Username");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setBounds(86, 135, 198, 80);
		frame.getContentPane().add(lblNewLabel_2);
		
		uid = new JTextField();
		uid.setColumns(10);
		uid.setBounds(403, 149, 269, 63);
		frame.getContentPane().add(uid);
		
		JButton btnNewButton = new JButton("  LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userid,userpass;
				userid=uid.getText();
				userpass=pss.getText();
				try
				{
					stmt=con.prepareStatement("select username,pass from user where username =?");
					stmt.setString(1,userid);
					ResultSet rs=stmt.executeQuery();
					if(rs.next()==true)
					{
						MainScreen info=new MainScreen();
						MainScreen.main(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid details","Login error",JOptionPane.ERROR_MESSAGE);
						uid.setText(null);
						pss.setText(null);
					}
				}
				catch(Exception e1)
				 {
					 System.out.println(e1);
				 }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(134, 355, 181, 55);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSignup = new JButton(" SIGNUP");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sign info=new Sign();
				Sign.main(null);
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignup.setBounds(491, 355, 181, 55);
		frame.getContentPane().add(btnSignup);
		
		JLabel lblNewLabel_2_1 = new JLabel("       Password");
		lblNewLabel_2_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(86, 247, 198, 80);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		pss = new JPasswordField();
		pss.setBounds(403, 247, 269, 63);
		frame.getContentPane().add(pss);
	}
}
