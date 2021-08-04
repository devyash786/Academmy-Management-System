import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UnsupportedLookAndFeelException;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
    public void reset()
    {
    	passwordField.setText(null);
    	passwordField_1.setText(null);
    	passwordField_2.setText(null);
    	
    }
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ChangePassword(String Emailid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		setTitle("Change Passowrd");
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=DBInfo.getConnection();
				String str="select * from registration where email=? ";
				int flag=0;
				String userype="";
				try {
					PreparedStatement ps=con.prepareStatement(str);
					ps.setString(1, Emailid);
					
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
				{    
					
					Admin ad=new Admin(Emailid);
					ad.setVisible(true);
					dispose();
				}
				if(flag==1 && userype.equalsIgnoreCase("user"))
				{ 
					User u=new User(Emailid);
					u.setVisible(true);
					dispose();
				}
				
				
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Previous Password");
		
		passwordField = new JPasswordField();
		
		JLabel lblNewLabel_1 = new JLabel("New Password");
		
		passwordField_1 = new JPasswordField();
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		
		passwordField_2 = new JPasswordField();
		
		JButton btnNewButton_1 = new JButton("Change Password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1=String.valueOf(passwordField.getPassword());
				String s2=String.valueOf(passwordField_1.getPassword());
				String s3=String.valueOf(passwordField_2.getPassword());
				String s4=Emailid;
				
			if(s2.length()==0 || s3.length()==0)
			{
				JOptionPane.showMessageDialog(getParent(), "Enter all fields","Error",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			
			if(s2.equals(s3))	
				{
				Connection con=DBInfo.getConnection();
				
				String str="select * from registration where email=? and pass=?";
				int flag=0;
				String userype="";
				try {
					PreparedStatement ps=con.prepareStatement(str);
					ps.setString(1, s4);
					ps.setString(2, s1);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						
						flag=1;
						break;
					}
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				if(flag==1)
				{ 
					String q="update registration set pass=? where email=?";
					int i=0;
					try {
						PreparedStatement ps1=con.prepareStatement(q);
					    ps1.setString(1, s2);
					    ps1.setString(2, s4);
					    i=ps1.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  if(i!=0)
				  {
					  JOptionPane.showMessageDialog(getParent(), "Password Changed Successfully","Succes",JOptionPane.INFORMATION_MESSAGE);
					  try {
						new Login().setVisible(true);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						dispose();
				  }
				  else
				  {
					  JOptionPane.showMessageDialog(getParent(), "Password not Changed ","Error",JOptionPane.ERROR_MESSAGE);  
					  reset();
				  }
					
					
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "Inorrect Previous Password ","Error",JOptionPane.ERROR_MESSAGE);  
					reset();
				}
			
				
				}
			else{
				JOptionPane.showMessageDialog(getParent(), "Passwords doesnot match  ","Error",JOptionPane.ERROR_MESSAGE);
				    reset();
				}
			}}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField_2)
								.addComponent(passwordField_1)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addComponent(btnNewButton_1))))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
