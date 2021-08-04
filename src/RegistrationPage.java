import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegistrationPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	JComboBox comboBox ,comboBox_1,comboBox_1_1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public void reset()
	{
		textField.setText(null);
		
		textField_1.setText(null);
		textField_2.setText(null);
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		textField_3.setText(null);
		textField_4.setText(null);
		comboBox_1_1.setSelectedIndex(0);
		passwordField.setText(null);
		passwordField_1.setText(null);
	}
	public RegistrationPage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 988, 604);
		setLocationRelativeTo(this);
		//setResizable(false);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g)
			{
				Image img=Toolkit.getDefaultToolkit().getImage("Untitled1.jpg");
				g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(), this);
				
			}
		};
		contentPane.setForeground(Color.WHITE);
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(Color.YELLOW);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mobile no:");
		lblNewLabel_1.setBackground(SystemColor.textHighlight);
		lblNewLabel_1.setForeground(Color.YELLOW);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Email id:");
		lblNewLabel_2.setForeground(Color.YELLOW);
		
		lblNewLabel_3 = new JLabel("Sports:");
		lblNewLabel_3.setForeground(Color.YELLOW);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		 comboBox = new JComboBox(DBInfo.getValue("sports"));
		
		JLabel lblNewLabel_4 = new JLabel("Bactch Time:");
		lblNewLabel_4.setForeground(Color.YELLOW);
		
		 comboBox_1 = new JComboBox(DBInfo.getValue("batchTime"));
		
		JLabel lblNewLabel_5 = new JLabel("Address:");
		lblNewLabel_5.setForeground(Color.YELLOW);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Age:");
		lblNewLabel_6.setForeground(Color.YELLOW);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Gender");
		lblNewLabel_7.setForeground(Color.YELLOW);
		
		JLabel lblNewLabel_8 = new JLabel("Password");
		lblNewLabel_8.setForeground(Color.YELLOW);
		
		passwordField = new JPasswordField();
		
		JLabel lblNewLabel_9 = new JLabel("Register Yourself Now!!");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_9.setForeground(Color.YELLOW);
		
		JLabel lblNewLabel_10 = new JLabel("Confirm Pass:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10.setForeground(Color.YELLOW);
		
		passwordField_1 = new JPasswordField();
		
	 comboBox_1_1 = new JComboBox();
		comboBox_1_1.addItem("Select");
		comboBox_1_1.addItem("Male");
		comboBox_1_1.addItem("Female");
		comboBox_1_1.addItem("Other");
		
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1=textField.getText();
				String s2=textField_1.getText();
				String s3=textField_2.getText();
				String s4=(String)comboBox.getSelectedItem();
				String s5=(String)comboBox_1.getSelectedItem();
				String s6=textField_3.getText();
				String s7=textField_4.getText();
				String s8=(String)comboBox_1_1.getSelectedItem();
				String s9=String.copyValueOf(passwordField.getPassword());
				String s10=String.copyValueOf(passwordField_1.getPassword());
				String s11="user";
				int i=0;
				if(s1.length()==0||s2.length()==0||s3.length()==0||s4.equalsIgnoreCase("Select")||s5.equalsIgnoreCase("Select")||s5.equalsIgnoreCase("Select")||s8.equalsIgnoreCase("Select")||s6.length()==0||s7.length()==0||s9.length()==0||s10.length()==0)
				{
					JOptionPane.showMessageDialog(getParent(), " Please Enter all fields ","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(!s9.equals(s10))
				{
					JOptionPane.showMessageDialog(getParent(), " Passwords do not match ","Error",JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
					passwordField.setText(null);
				}
				else
				{
					Connection con=DBInfo.getConnection();
					String query="insert into registration values(?,?,?,?,?,?,?,?,?,?)";
					try {
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, s1);
						ps.setString(2, s2);
						ps.setString(3, s3);
						ps.setString(4, s4);
						ps.setString(5, s5);
						ps.setString(6, s6);
						ps.setString(7, s7);
						ps.setString(8, s8);
						ps.setString(9, s9);
						ps.setString(10, s11);
						i=ps.executeUpdate();
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(i!=0)
					{
						JOptionPane.showMessageDialog(getParent(), "Registered Successfully ","Success", JOptionPane.INFORMATION_MESSAGE);
						reset();
					}
					if(i==0)
					{
						JOptionPane.showMessageDialog(getParent(), "Registration Failed","Failed",JOptionPane.ERROR_MESSAGE); 
					}		
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("SIGN IN");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Login().setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(669, Short.MAX_VALUE)
					.addComponent(lblNewLabel_9)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(621)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_7)
								.addComponent(lblNewLabel_10)
								.addComponent(lblNewLabel_8)
								.addComponent(lblNewLabel_6))
							.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField_1, Alignment.TRAILING)
								.addComponent(passwordField, Alignment.TRAILING)
								.addComponent(comboBox_1_1, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_4, Alignment.TRAILING)
								.addComponent(textField_3, Alignment.TRAILING)
								.addComponent(comboBox_1, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_2, Alignment.TRAILING)
								.addComponent(textField_1, Alignment.TRAILING)
								.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
							.addGap(11))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_9)
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_10))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2))
					.addGap(62))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
