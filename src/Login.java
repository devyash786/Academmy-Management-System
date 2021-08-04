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
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame   {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
    public String EmailId;
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
	public Login() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 513, 343);
		setLocationRelativeTo(this);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g)
			{
				Image img=Toolkit.getDefaultToolkit().getImage("images.jpg");
				g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(), this);
				
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Email_Id:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					passwordField.setEchoChar((char)0);
				else
					passwordField.setEchoChar('*');
			
		}
		});
		chckbxNewCheckBox.setBackground(new Color(0, 204, 102));
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1=textField.getText();
				String s2=String.copyValueOf(passwordField.getPassword());
				Connection con=DBInfo.getConnection();
				String str="select * from registration where email=? and pass=?";
				int flag=0;
				String userype="";
				try {
					PreparedStatement ps=con.prepareStatement(str);
					ps.setString(1, s1);
					ps.setString(2, s2);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						userype=res.getString(10);
						flag=1;
						
						break;
					}
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				if(flag==1 && userype.equalsIgnoreCase("admin"))
				{    EmailId=s1;
					JOptionPane.showMessageDialog(getParent(), "Login Successfully ","Success", JOptionPane.INFORMATION_MESSAGE);
					Admin ad=new Admin(s1);
					ad.setVisible(true);
					dispose();
				}
				if(flag==1 && userype.equalsIgnoreCase("user"))
				{ JOptionPane.showMessageDialog(getParent(), "Login Successfully ","Success", JOptionPane.INFORMATION_MESSAGE);
					User u=new User(s1);
					u.setVisible(true);
					dispose();
				}
				
				
				if(flag==0)
				{
					JOptionPane.showMessageDialog(getParent(),"Username or Password incorrect!","Error",JOptionPane.ERROR_MESSAGE);
					textField.setText(null);
					passwordField.setText(null);
				}
				
			
			}
		});
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new RegistrationPage().setVisible(true);
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
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(109)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField)
								.addComponent(textField))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxNewCheckBox))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(181)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxNewCheckBox))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	
	

	
		
		
	
}
