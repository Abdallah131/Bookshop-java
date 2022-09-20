import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Register {

	public JFrame frame;
	private JTextField Registername;
	private JTextField Registeremail;
	private JPasswordField Registerpass;
	private JPasswordField Registerpass2;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		String str = ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		UIManager.setLookAndFeel(str);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public Register() {
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
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Hypex\\eclipse-workspace\\Bookshop\\bk.jpg"));
		lblNewLabel.setBounds(-481, 0, 933, 622);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(438, 0, 523, 622);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Username", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(84, 73, 383, 70);
		panel_1.add(panel_1_1);
		
		Registername = new JTextField();
		Registername.setColumns(10);
		Registername.setBackground(new Color(214, 217, 223));
		Registername.setBounds(6, 23, 371, 28);
		panel_1_1.add(Registername);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new TitledBorder(null, "Email", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1_1.setBounds(84, 173, 383, 70);
		panel_1.add(panel_1_1_1);
		
		Registeremail = new JTextField();
		Registeremail.setColumns(10);
		Registeremail.setBackground(new Color(214, 217, 223));
		Registeremail.setBounds(6, 23, 371, 28);
		panel_1_1_1.add(Registeremail);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new TitledBorder(null, "Password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1_2.setBounds(84, 276, 383, 70);
		panel_1.add(panel_1_1_2);
		
		Registerpass = new JPasswordField();
		Registerpass.setBackground(new Color(214, 217, 223));
		Registerpass.setBounds(6, 21, 371, 28);
		panel_1_1_2.add(Registerpass);
		
		JPanel panel_1_1_2_1 = new JPanel();
		panel_1_1_2_1.setLayout(null);
		panel_1_1_2_1.setBorder(new TitledBorder(null, "Repeat Password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1_2_1.setBounds(84, 396, 383, 70);
		panel_1.add(panel_1_1_2_1);
		
		Registerpass2 = new JPasswordField();
		Registerpass2.setBackground(new Color(214, 217, 223));
		Registerpass2.setBounds(6, 21, 371, 28);
		panel_1_1_2_1.add(Registerpass2);
		
		JButton Register = new JButton("Register");
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String namer = Registername.getText();
				String emailr = Registeremail.getText();
				String passr = Registerpass.getText();
				String pass2r= Registerpass2.getText();
				if (namer.isEmpty() || (emailr.isEmpty() || (passr.isEmpty()|| (pass2r.isEmpty())))) {
					JOptionPane.showMessageDialog(null,"All fields must be filled!");
				}else {
				
				String name = Registername.getText();
				String email = Registeremail.getText();
				String pass = String.valueOf(Registerpass.getText());
			
				
				try {
				PreparedStatement stmt ;
				stmt= con.prepareStatement("INSERT INTO login(name,email,password)VALUES(?,?,?)");
				stmt.setString(1, name);
				stmt.setString(2,email);
				stmt.setString(3, pass);
				stmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Registered");
				
				
				Registername.setText("");
				Registeremail.setText("");
				Registerpass.setText("");
				Registerpass2.setText("");
				Registername.requestFocus();
				
				
				}catch (Exception es) {
					
					System.err.println(es.getMessage());
				}
				}
				
			}
		});
		Register.setForeground(Color.WHITE);
		Register.setFont(new Font("SansSerif", Font.BOLD, 12));
		Register.setBackground(SystemColor.textHighlight);
		Register.setBounds(215, 501, 130, 63);
		panel_1.add(Register);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(243, 21, 102, 26);
		panel_1.add(lblNewLabel_1);
		
	
		frame.setBounds(100, 100, 977, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
