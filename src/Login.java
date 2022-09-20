import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	private JFrame frame;
	private JTextField Lusername;
	private JTextField Lemail;
	private JPasswordField Lpass;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		String str = ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		UIManager.setLookAndFeel(str);
		
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

	public Login() {
		initialize();
		Connect();
	}
	
	Connection con;
	
	public void Connect() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop", "root", "");
		} catch (Exception e) {
			System.out.print("Error : " + e.getMessage());
		}
	}

	private void initialize() {
		ImageIcon logo = new ImageIcon("books.png");
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setIconImage(logo.getImage());
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(150, 150, 150));
		panel.setBounds(0, 0, 440, 622);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("sign.jpg"));
		lblNewLabel.setBounds(-293, 0, 733, 634);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(438, 0, 523, 622);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Username", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(74, 98, 383, 70);
		panel_1.add(panel_1_1);
		
		Lusername = new JTextField();
		Lusername.setBackground(new Color(214, 217, 223));
		Lusername.setBounds(6, 23, 371, 28);
		panel_1_1.add(Lusername);
		Lusername.setColumns(10);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new TitledBorder(null, "Email", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1_1.setBounds(74, 207, 383, 70);
		panel_1.add(panel_1_1_1);
		
		Lemail = new JTextField();
		Lemail.setBackground(new Color(214, 217, 223));
		Lemail.setBounds(6, 24, 371, 28);
		panel_1_1_1.add(Lemail);
		Lemail.setColumns(10);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new TitledBorder(null, "Password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1_2.setBounds(74, 305, 383, 70);
		panel_1.add(panel_1_1_2);
		
		Lpass = new JPasswordField();
		Lpass.setBackground(new Color(214, 217, 223));
		Lpass.setBounds(6, 21, 371, 28);
		panel_1_1_2.add(Lpass);
		
		JButton Register = new JButton("Register");
		Register.setBounds(101, 430, 130, 63);
		panel_1.add(Register);
		Register.setForeground(SystemColor.text);
		Register.setBackground(SystemColor.textHighlight);
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register rg = new Register();
				frame.setVisible(false);
				rg.frame.setVisible(true);	
				rg.frame.setLocationRelativeTo(null);
				
			}
		});
		Register.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton Signin = new JButton("Sign-In");
		Signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String name = Lusername.getText();
				String email = Lemail.getText();
				String pass = Lpass.getText();
				
				if (name.isEmpty() || (email.isEmpty() || (pass.isEmpty()))) {
					JOptionPane.showMessageDialog(null,"All fields must be filled!");
				}
				
				
				PreparedStatement stmt;
				ResultSet rs;
				try {
					stmt = con.prepareStatement("SELECT name,email,password FROM login WHERE name= ? AND email = ? AND password = ?");
					stmt.setString(1, name);
					stmt.setString(2, email);
					stmt.setString(3, pass);			
					rs = stmt.executeQuery();
				if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Login Successful");
                    Main sh = new Main();
                    frame.setVisible(false);
    				sh.frame.setVisible(true);
    				sh.frame.setLocationRelativeTo(null);
				}else {
					JOptionPane.showMessageDialog(null,"Login Failed Check your information");
					Lusername.setText("");
					Lemail.setText("");
					Lpass.setText("");
					
				}
				
				} catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
				
			}
		});
		Signin.setForeground(Color.WHITE);
		Signin.setFont(new Font("SansSerif", Font.BOLD, 12));
		Signin.setBackground(SystemColor.textHighlight);
		Signin.setBounds(304, 430, 130, 63);
		panel_1.add(Signin);
		
		JLabel lblNewLabel_1 = new JLabel("Sign In");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(239, 35, 102, 26);
		panel_1.add(lblNewLabel_1);
		frame.setBounds(100, 100, 977, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
