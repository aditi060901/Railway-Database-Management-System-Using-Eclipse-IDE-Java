package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButtonMenuItem;

import railway.RMS;
import user.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   Railway Management System");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setBounds(125, 22, 541, 67);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("  ADMIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RMS info=new RMS();
				RMS.main(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setBounds(102, 229, 206, 76);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUser = new JButton("   USER");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User info=new User();
				User.main(null);
			}
		});
		btnUser.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnUser.setBounds(459, 229, 207, 76);
		frame.getContentPane().add(btnUser);
	}
}
