package cancelticket;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import addtrain.Home;
import main.MainScreen;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicketCancelling {

	private JFrame frame;
	private JTextField tidenter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketCancelling window = new TicketCancelling();
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
	public TicketCancelling() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 804, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   Railway Management System");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(113, 10, 541, 67);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cancel Ticket", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(27, 167, 498, 144);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("   Person ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(25, 49, 142, 34);
		panel.add(lblNewLabel_2);
		
		tidenter = new JTextField();
		tidenter.setColumns(10);
		tidenter.setBounds(266, 49, 139, 35);
		panel.add(tidenter);
		
		JButton btnNewButton_1 = new JButton("  CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id;
				id=tidenter.getText();
				try
				{
					stmt=con.prepareStatement("delete from ticket where PID=?");
				    stmt.setString(1,id);
				    stmt.executeUpdate();
				    JOptionPane.showMessageDialog(null,"Ticket Cancelled!");
					tidenter.setText("");
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(609, 167, 114, 53);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScreen info=new MainScreen();
				MainScreen.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(610, 286, 113, 60);
		frame.getContentPane().add(btnBack);
	}
}
