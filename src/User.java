import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JButton;

public class User extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public User(String mail) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 396);
		setTitle("User");
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("");
		menuBar.add(mntmNewMenuItem);
		
		JMenu mnNewMenu = new JMenu(mail);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Change Password");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(mail).setVisible(true);
				dispose();
			}
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
			}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 3, 0, 0));
		
		JButton btnNewButton = new JButton("My Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewUsr(mail).setVisible(true);
				
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("See All Sports");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Tbl("sports",mail).setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("See All Batch");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Tbl("batchtime",mail).setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("See Tournaments");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Tbl("tounaments",mail).setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("Request Equipments");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String g=((String)JOptionPane.showInputDialog(getParent(),"Enter Sports:","Enter",JOptionPane.PLAIN_MESSAGE,null,DBInfo.getValue1("sports"),(Object)"Select")).toLowerCase();
			   String g1=JOptionPane.showInputDialog("Enter requirments");
			   String qry="insert into equipment  values(?,?,?)";
			   Connection con=DBInfo.getConnection();
			  int i=0;
			   try {
				PreparedStatement ps=con.prepareStatement(qry);
				ps.setInt(1, 0);
				ps.setString(2, g);
				ps.setString(3, g1);
				i=ps.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   if(i!=0)
			   {
				   JOptionPane.showMessageDialog(getParent(), "Requested!","Done",JOptionPane.PLAIN_MESSAGE);
			   }
			   else
			   {
				   JOptionPane.showMessageDialog(getParent(), "Request Failed!","Error",JOptionPane.ERROR_MESSAGE);
				   
			   }
			}
		});
		contentPane.add(btnNewButton_4);
	}

}
