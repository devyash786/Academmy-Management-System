import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Admin(String cngemail) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 409);
		setTitle("ADMIN");
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem menuItem = new JMenuItem("");
		menuBar.add(menuItem);
		
		JMenu mnNewMenu = new JMenu(cngemail);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("View Profile");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q="select name,mobile,email,address,age,gender from registration where email=?";
				Connection con=DBInfo.getConnection();
				String s1="",s2="",s3="",s4="",s5="",s6="";
				int i=0;
				try {
					PreparedStatement ps=con.prepareStatement(q);
					ps.setString(1, cngemail);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{ 
						s1=res.getString(1);
						s2=res.getString(2);
						s3=res.getString(3);
						s4=res.getString(4);
						s5=res.getString(5);
						s6=res.getString(6);
						i=1;
						break;
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(i!=0)
				{
					new AdminProfile(s1,s2,s3,s4,s5,s6).setVisible(true);
					dispose();	
				}
				if(i==0)
				{
					JOptionPane.showMessageDialog(getParent(),"Record not found","Error",JOptionPane.ERROR_MESSAGE);
				}
				
						
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Change your Password");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1=JOptionPane.showInputDialog("Confirm your email id");
				if(s1.length()==0)
					JOptionPane.showMessageDialog(getParent(), "Please enter a valid email address","Error",JOptionPane.ERROR_MESSAGE);
				
				else
					{
					if(s1.equals(cngemail))
					
				{		
				new ChangePassword(s1).setVisible(true);
				dispose();
				}
				else
					 JOptionPane.showMessageDialog(getParent(), "Your email id does not Match","Error",JOptionPane.ERROR_MESSAGE);
			}}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Log Out");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int s=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to logout","Confirmation",JOptionPane.YES_NO_OPTION);
				if(s==0)
				{try {
					new HomePage().setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}}
			
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 3, 0, 0));
		
		JButton btnNewButton = new JButton("Add Player/Admin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1=JOptionPane.showInputDialog("Enter user type");
				if(s1.equalsIgnoreCase("player"))
				{
					 try {
						new RegistrationPage().setVisible(true);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 dispose();
				}
				
				else if(s1.equalsIgnoreCase("admin"))
				{
					try {
						new RegistrationPageAdmin(cngemail).setVisible(true);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
					else 
					{
						JOptionPane.showMessageDialog(getParent(),"Enter valid usertype","Error",JOptionPane.ERROR_MESSAGE);
					}
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("Add Sports/Batch");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1=JOptionPane.showInputDialog("Enter what you want to add Sports/Batch");
				
				String s2="",query="";
				if(s1.length()==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Enter valid input","Error",JOptionPane.ERROR_MESSAGE); 
				
				}
				else {
				Connection con=DBInfo.getConnection();
				if(s1.equalsIgnoreCase("Sports"))
				{
					 s2=JOptionPane.showInputDialog("Enter Sports").toLowerCase();
					 query="insert into sports values(?,?)";
					 int i=0;
					 try {
						PreparedStatement ps1=con.prepareStatement(query);
						ps1.setInt(1, 0);
						ps1.setString(2, s2);
						i=ps1.executeUpdate();
						
					}
					 catch(Exception e1)
					{
						 e1.printStackTrace();
					}
					   if(i!=0)
						{
							JOptionPane.showMessageDialog(getParent(), " Sports Added Successfully ","Success", JOptionPane.INFORMATION_MESSAGE);
							
						}
						if(i==0)
						{
							JOptionPane.showMessageDialog(getParent(), "Sporta addition failed","Failed",JOptionPane.ERROR_MESSAGE); 
						}	
					
					
					 
					
				}
				else if(s1.equalsIgnoreCase("Batch"))
				{
					 s2=JOptionPane.showInputDialog("Enter Batch");
					 query="insert into batchtime values(?,?)";
					 int i=0;
					 try {
						PreparedStatement ps1=con.prepareStatement(query);
						ps1.setInt(1, 0);
						ps1.setString(2, s2);
						i=ps1.executeUpdate();
						
					}
					 catch(Exception e1)
					{
						 e1.printStackTrace();
					}
					   if(i!=0)
						{
							JOptionPane.showMessageDialog(getParent(), "Batch Added Successfully ","Success", JOptionPane.INFORMATION_MESSAGE);
							
						}
						if(i==0)
						{
							JOptionPane.showMessageDialog(getParent(), "Batch addition failed","Failed",JOptionPane.ERROR_MESSAGE); 
						}	
					
					
					 
					
				}
				else {
					JOptionPane.showMessageDialog(getParent(), "Enter valid input","ERROR",JOptionPane.ERROR_MESSAGE); 
				}
				
			}}
		});
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_1 = new JButton("Search/Update/Delete User");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1=JOptionPane.showInputDialog("Enter the desired email");
				String usertype="";
				if(s1.length()==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Enter a valid input","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
				Connection con=DBInfo.getConnection();
				String str="select * from registration where email=?";
				int flag=0;
				
				try {
					PreparedStatement ps=con.prepareStatement(str);
					ps.setString(1, s1);
					
					ResultSet res=ps.executeQuery();
					while(res.next())
					{   flag=1;
						usertype=res.getString(10);
					   break;
					}
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				if(usertype.equalsIgnoreCase("user"))
				{ if(flag==1)
				{    
					
					ViewUserProfile ad=new ViewUserProfile(cngemail,s1);
					ad.setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "Email id does not exist ","Error",JOptionPane.ERROR_MESSAGE);
				}
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "You dont have acces to this ","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View All");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] ob= {"Select","user","sports","batchtime"};
				String s1=(String)JOptionPane.showInputDialog(getParent(),"Enter item","Search",JOptionPane.PLAIN_MESSAGE,null,ob,ob[0]);
				if(s1.equalsIgnoreCase("Select"))
				{
					JOptionPane.showMessageDialog(getParent(),"Enter a valid item!","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					new View_Sp_Batch(cngemail,s1).setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("See Equipment Request");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EquipmentReq(cngemail).setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_6 = new JButton("Delete Sports/Batch");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object c[]= {"Select","Sports","BatchTime"};
				String s1=((String)JOptionPane.showInputDialog(getParent(),"Enter what you want to add","Add",JOptionPane.PLAIN_MESSAGE,null,c,c[0])).toLowerCase();
				String s2="";
				if(s1.equalsIgnoreCase("Select"))
				{
					JOptionPane.showMessageDialog(getParent(), "Enter valid input","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
				if(s1.equalsIgnoreCase("sports"))
				{    
					 s2=((String)JOptionPane.showInputDialog(getParent(), "Select ","Choose",JOptionPane.PLAIN_MESSAGE,null,DBInfo.getValue1("sports"),(Object)"Select")).toLowerCase();
				}
				else if(s1.equalsIgnoreCase("batchtime"))
				{
					 s2=(String)JOptionPane.showInputDialog(getParent(), "Select ","Choose",JOptionPane.PLAIN_MESSAGE,null,DBInfo.getValue1("batchtime"),(Object)"Select");
				}
				
				
					
				
				String qry="delete from "+s1+" where name=?";
				if(s1.equalsIgnoreCase("Select")||s2.equalsIgnoreCase("Select"))
				{
					JOptionPane.showMessageDialog(getParent(), "Enter valid Inputs","Error",JOptionPane.ERROR_MESSAGE);
				}
				Connection con=DBInfo.getConnection();
				int i=0;
				try {
					PreparedStatement ps=con.prepareStatement(qry);
					ps.setString(1, s2);
					i=ps.executeUpdate();
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				if(i!=0)
				{
					JOptionPane.showMessageDialog(getParent(), "Deleted","Sucess",JOptionPane.INFORMATION_MESSAGE);
					new Admin(cngemail).setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "Not Deleted","Error",JOptionPane.ERROR_MESSAGE);
				}
			}}
		});
		contentPane.add(btnNewButton_6);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Tournaments");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Tour(cngemail).setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton_4);
	}

}
