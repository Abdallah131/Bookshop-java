import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import net.proteanit.sql.DbUtils;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

public class Main {

	public JFrame frame;
	private JTextField bookID;
	private JTextField BookName;
	private JTextField BookPrice;
	private JTable tablers;
	private JTextField SearchFD;

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		String str = ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		UIManager.setLookAndFeel(str);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	ResultSet rs;
	private JTextField t;
	private JTextField t2;

	public Main() {
		initialize();
		Connect();
		load();

	}

	public void Connect() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop", "root", "");
		} catch (Exception e) {
			System.out.print("Error : " + e.getMessage());
		}
	}

	public void load() {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT * FROM books");
			rs = stmt.executeQuery();
			tablers.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private void initialize() {

		ImageIcon logo = new ImageIcon("books.png");
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(220, 220, 220));
		frame.setBounds(100, 100, 1138, 778);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(logo.getImage());
		frame.setLocationRelativeTo(null);

		JLabel Title_1 = new JLabel("Book Shop");
		Title_1.setFont(new Font("Script MT Bold", Font.PLAIN, 37));
		Title_1.setBounds(498, 34, 163, 80);
		frame.getContentPane().add(Title_1);
	
		JPanel RegistrationP = new JPanel();
		RegistrationP.setBorder(new LineBorder(new Color(0, 128, 192), 3, true));
		RegistrationP.setBounds(109, 162, 428, 378);
		frame.getContentPane().add(RegistrationP);
		RegistrationP.setLayout(null);

		JLabel BID = new JLabel("Book ID");
		BID.setFont(new Font("SansSerif", Font.PLAIN, 20));
		BID.setBounds(44, 58, 82, 39);
		RegistrationP.add(BID);

		JLabel BN = new JLabel("Book Name");
		BN.setFont(new Font("SansSerif", Font.PLAIN, 20));
		BN.setBounds(44, 109, 102, 39);
		RegistrationP.add(BN);

		JLabel P = new JLabel("Price");
		P.setFont(new Font("SansSerif", Font.PLAIN, 20));
		P.setBounds(44, 159, 82, 39);
		RegistrationP.add(P);

		bookID = new JTextField();
		bookID.setBounds(206, 68, 193, 26);
		RegistrationP.add(bookID);
		bookID.setColumns(10);

		BookName = new JTextField();
		BookName.setColumns(10);
		BookName.setBounds(206, 119, 193, 26);
		RegistrationP.add(BookName);

		BookPrice = new JTextField();
		BookPrice.setColumns(10);
		BookPrice.setBounds(206, 169, 193, 26);
		RegistrationP.add(BookPrice);

		JButton Save = new JButton("Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(bookID.getText());
				String name = BookName.getText();
				int price = Integer.parseInt(BookPrice.getText());

				try {
					Statement stmt = con.createStatement();
					String sql = ("INSERT INTO books(ID,Name,price)VALUES(" + id + ",'" + name + "'," + price + ")");
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Book Added");
					load();
					bookID.setText("");
					BookName.setText("");
					BookPrice.setText("");
					BookName.requestFocus();

				} catch (SQLException e1) {
					System.err.println(e1.getMessage());
				}

			}
		});
		Save.setBounds(30, 238, 109, 55);
		RegistrationP.add(Save);

		JButton Clear = new JButton("Clear");
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bookID.setText("");
				BookName.setText("");
				BookPrice.setText("");
				bookID.requestFocus();
			}
		});
		Clear.setBounds(163, 238, 109, 55);
		RegistrationP.add(Clear);

		JButton Exit = new JButton("Exit");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Exit.setBounds(296, 238, 109, 55);
		RegistrationP.add(Exit);

		JLabel Regtitle = new JLabel("Registration");
		Regtitle.setForeground(new Color(0, 0, 0));
		Regtitle.setFont(new Font("Script MT Bold", Font.PLAIN, 25));
		Regtitle.setBounds(258, 127, 128, 31);
		frame.getContentPane().add(Regtitle);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 128, 192), 3, true));
		panel.setBounds(638, 160, 428, 378);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 408, 361);
		panel.add(scrollPane);

		tablers = new JTable();
		scrollPane.setViewportView(tablers);

		JPanel Search = new JPanel();
		Search.setBounds(49, 614, 582, 94);
		frame.getContentPane().add(Search);
		Search.setBorder(new LineBorder(new Color(0, 128, 192), 3, true));
		Search.setLayout(null);

		JLabel BID_1 = new JLabel("Book ID");
		BID_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		BID_1.setBounds(25, 29, 82, 39);
		Search.add(BID_1);

		SearchFD = new JTextField();
		SearchFD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String srch = SearchFD.getText();

				PreparedStatement stmt;

				try {
					stmt = con.prepareStatement("SELECT ID,Name,price FROM books WHERE ID= ?");
					stmt.setString(1, srch);
					ResultSet rs = stmt.executeQuery();

					if (rs.next() == true) {
						String Name = rs.getString(2);
						String price = rs.getString(3);

						t.setText(Name);
						t2.setText(price);

					} else {
						bookID.setText("");
						BookName.setText("");
						BookPrice.setText("");
					}

				} catch (SQLException e1) {

				}

			}
		});
		SearchFD.setColumns(10);
		SearchFD.setBounds(132, 39, 161, 26);
		Search.add(SearchFD);

		JButton Update = new JButton("Update");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PreparedStatement stmt;

				try {
					stmt = con.prepareStatement("UPDATE BOOKS SET Name= ?, price = ? WHERE ID= ?");
					stmt.setString(1, t.getText());
					stmt.setString(2, t2.getText());
					stmt.setString(3, SearchFD.getText());
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book Updated");
					load();

				} catch (Exception er) {
					System.err.println(er.getMessage());
				}
			}
		});
		Update.setBounds(313, 27, 109, 50);
		Search.add(Update);

		JButton Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PreparedStatement stmt;

				try {
					stmt = con.prepareStatement("DELETE FROM books WHERE ID = ?");
					stmt.setString(1, SearchFD.getText());
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book Deleted");
					load();
					bookID.setText("");
					BookName.setText("");
					BookPrice.setText("");
					BookName.requestFocus();

				} catch (Exception err) {
					System.err.println(err.getMessage());
				}

			}
		});
		Delete.setBounds(450, 27, 109, 50);
		Search.add(Delete);

		JLabel Title = new JLabel("Search");
		Title.setForeground(new Color(0, 0, 0));
		Title.setFont(new Font("Script MT Bold", Font.PLAIN, 25));
		Title.setBounds(89, 574, 128, 31);
		frame.getContentPane().add(Title);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel_1.setBounds(466, 92, 222, 4);
		frame.getContentPane().add(panel_1);
		
		JLabel Log = new JLabel("");
		Log.setIcon(new ImageIcon("bookslog.png"));
		Log.setBounds(49, 16, 111, 62);
		frame.getContentPane().add(Log);
		
		JLabel Log1 = new JLabel("");
		Log1.setIcon(new ImageIcon("bookslog.png"));
		Log1.setBounds(996, 16, 90, 62);
		frame.getContentPane().add(Log1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 128, 192), 3));
		panel_2.setBounds(687, 614, 399, 94);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel sdsdt2 = new JLabel("Book Name");
		sdsdt2.setFont(new Font("SansSerif", Font.BOLD, 12));
		sdsdt2.setBounds(24, 19, 76, 16);
		panel_2.add(sdsdt2);
		
		JLabel t3 = new JLabel("Book Price");
		t3.setFont(new Font("SansSerif", Font.BOLD, 12));
		t3.setBounds(23, 53, 66, 16);
		panel_2.add(t3);
		
		t = new JTextField();
		t.setColumns(10);
		t.setBounds(124, 13, 122, 28);
		panel_2.add(t);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(124, 47, 122, 28);
		panel_2.add(t2);
		
		JButton Clear_1 = new JButton("Clear");
		Clear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setText("");
				t2.setText("");
				BookName.requestFocus();
			}
		});
		Clear_1.setBounds(276, 19, 102, 55);
		panel_2.add(Clear_1);
	}
}