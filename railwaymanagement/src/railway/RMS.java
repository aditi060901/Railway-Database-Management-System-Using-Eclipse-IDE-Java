package railway;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import addtrain.Home;

import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RMS {

	private JFrame frame;
	private JTextField txtadmin;
	private JPasswordField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RMS window = new RMS();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RMS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 812, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Railway Reservation System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(208, 26, 435, 73);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("       Password");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_1.setBounds(79, 249, 189, 73);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("         Admin");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setBounds(70, 160, 198, 80);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtadmin = new JTextField();
		txtadmin.setBounds(374, 177, 269, 63);
		frame.getContentPane().add(txtadmin);
		txtadmin.setColumns(10);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(373, 266, 270, 56);
		frame.getContentPane().add(txtpass);
		
		JButton btnNewButton = new JButton("  Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password=txtpass.getText();
				String username=txtadmin.getText();
				if( password.contains("root9451") && username.contains("Admin"))
				{
					txtpass.setText(null);
					txtadmin.setText(null);
					Home info=new Home();
					Home.main(null);
					
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid details","Login error",JOptionPane.ERROR_MESSAGE);
					txtpass.setText(null);
					txtadmin.setText(null);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(338, 371, 136, 47);
		frame.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 344, 778, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 107, 778, 2);
		frame.getContentPane().add(separator_1);
	}
}
