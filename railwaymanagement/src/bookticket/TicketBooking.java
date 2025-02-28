package bookticket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import addperson.Person;
import addtrain.Home;
import main.MainScreen;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 
public class TicketBooking {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketBooking window = new TicketBooking();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	PreparedStatement stmt;
	Connection con;
	private JTextField tno;
	private JTextField sno1;
	private JTextField sno2;
	private JTextField coach;
	private JTextField dat;
	private JTextField tpid;

	
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
	public TicketBooking() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 806, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   Railway Management System");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(113, 10, 541, 67);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 94, 734, 281);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblEnterTrainNo = new JLabel("   Enter Train No");
		lblEnterTrainNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnterTrainNo.setBounds(10, 10, 139, 44);
		panel.add(lblEnterTrainNo);
		
		JLabel lblEnterNo = new JLabel("   Enter Start Station");
		lblEnterNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnterNo.setBounds(10, 84, 194, 44);
		panel.add(lblEnterNo);
		
		JLabel lblEnterDestStation = new JLabel("   Enter Dest Station");
		lblEnterDestStation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnterDestStation.setBounds(10, 157, 194, 44);
		panel.add(lblEnterDestStation);
		
		tno = new JTextField();
		tno.setColumns(10);
		tno.setBounds(229, 25, 69, 35);
		panel.add(tno);
		
		sno1 = new JTextField();
		sno1.setColumns(10);
		sno1.setBounds(229, 99, 69, 35);
		panel.add(sno1);
		
		sno2 = new JTextField();
		sno2.setColumns(10);
		sno2.setBounds(229, 166, 69, 35);
		panel.add(sno2);
		
		JLabel lblCoachId = new JLabel("       Coach Type");
		lblCoachId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCoachId.setBounds(372, 19, 139, 35);
		panel.add(lblCoachId);
		
		coach = new JTextField();
		coach.setColumns(10);
		coach.setBounds(588, 19, 125, 35);
		panel.add(coach);
		
		JLabel lblDateOfJourney = new JLabel("        Date Of Journey");
		lblDateOfJourney.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDateOfJourney.setBounds(350, 159, 194, 44);
		panel.add(lblDateOfJourney);
		
		dat = new JTextField();
		dat.setColumns(10);
		dat.setBounds(588, 166, 125, 35);
		panel.add(dat);
		
		JLabel lblStatus_1_1_1 = new JLabel("       PID");
		lblStatus_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStatus_1_1_1.setBounds(401, 92, 84, 44);
		panel.add(lblStatus_1_1_1);
		
		tpid = new JTextField();
		tpid.setColumns(10);
		tpid.setBounds(588, 91, 125, 35);
		panel.add(tpid);
		
		JButton btnNewButton = new JButton("  Add Person");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person info=new Person();
				Person.main(null);
			}
		});
		btnNewButton.setBounds(10, 400, 164, 51);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnBook = new JButton("  BOOK");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tnoo,snoo1,snoo2,coachh,datte,ppid;
				tnoo=tno.getText();
				snoo1=sno1.getText();
				snoo2=sno2.getText();
				coachh=coach.getText();
			
				
				datte=dat.getText();
				ppid=tpid.getText();
				try
				{
					stmt=con.prepareStatement("insert into ticket(PID,TNO,From_Station,To_Station,Coach_Type,Date_Of_Journey)values(?,?,?,?,?,?)");
					stmt.setString(1,ppid);
					stmt.setString(2,tnoo);
					stmt.setString(3,snoo1);
					stmt.setString(4,snoo2);
					
					stmt.setString(5,coachh);
					
					stmt.setString(6,datte);
			        stmt.executeUpdate();
					JOptionPane.showMessageDialog(null,"Ticket Booked!");
					tno.setText("");
					sno1.setText("");
					sno2.setText("");
					coach.setText("");
				
					
					dat.setText("");
					tno.requestFocus();
					}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}	
				
			
				
			}
		});
		btnBook.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBook.setBounds(428, 400, 164, 51);
		frame.getContentPane().add(btnBook);
		
		JButton btnBack = new JButton("  BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainScreen info=new MainScreen();
				MainScreen.main(null);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setBounds(618, 400, 164, 51);
		frame.getContentPane().add(btnBack);
		
		JButton btnUpdatePerson = new JButton("  Update Person");
		btnUpdatePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person info=new Person();
				Person.main(null);
			}
		});
		btnUpdatePerson.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdatePerson.setBounds(208, 400, 199, 51);
		frame.getContentPane().add(btnUpdatePerson);
	}
}
