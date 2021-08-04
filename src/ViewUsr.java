import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.Vector;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewUsr extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblNewLabel_8;
	private JButton btnNewButton_2;
	JComboBox comboBox,comboBox_1,comboBox_2;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public void reset()
	{
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		comboBox.setSelectedIndex(0);;
		comboBox_1.setSelectedIndex(0);
		textField_5.setText(null);
		textField_6.setText(null);
		comboBox_2.setSelectedIndex(0);
		
		
	}
	public ViewUsr(String usermail) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 606);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Name:");
		
		JLabel lblNewLabel_1 = new JLabel("Mobile");
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		
		JLabel lblNewLabel_3 = new JLabel("Sports");
		
		JLabel lblNewLabel_4 = new JLabel("Batch");
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		
		JLabel lblNewLabel_6 = new JLabel("Age");
		
		JLabel lblNewLabel_7 = new JLabel("Gender");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
         comboBox = new JComboBox(DBInfo.getValue("sports"));
		
		 comboBox_1 = new JComboBox(DBInfo.getValue("batchtime"));
		
		 comboBox_2 = new JComboBox();
		comboBox_2.addItem("Select");
		comboBox_2.addItem("Male");
		comboBox_2.addItem("Female");
		comboBox_2.addItem("Others");
		String qry="select  name,mobile,email,sports,batch,address,age,gender,usertype from registration where email=?";
		Connection con=DBInfo.getConnection();
		
		int flag=0;
		try {
			PreparedStatement ps=con.prepareStatement(qry);
		    ps.setString(1, usermail);
		   ResultSet res=ps.executeQuery();
		   while(res.next())
		   {
			   flag=1;
		   
		   
			   textField.setText(res.getString(1));
			   textField_1.setText(res.getString(2));
			   textField_2.setText(res.getString(3));
			   comboBox.setSelectedItem(res.getString(4));
			   comboBox_1.setSelectedItem(res.getString(5));
			   textField_5.setText(res.getString(6));
			   textField_6.setText(res.getString(7));
			   comboBox_2.setSelectedItem(res.getString(8));
			   
			   
		   }
		   if(flag!=1)
		   {
			   JOptionPane.showMessageDialog(getParent(), "Record not found ","Error",JOptionPane.ERROR_MESSAGE);
		   }
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		lblNewLabel_8 = new JLabel("Your Details");
		lblNewLabel_8.setVisible(false);
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1=textField.getText();
				String s2=textField_1.getText();
				String s3=textField_2.getText();
				String s4=(String)comboBox.getSelectedItem();
				String s5=(String)comboBox_1.getSelectedItem();
				String s6=textField_5.getText();
				String s7=textField_6.getText();
				String s8=(String)comboBox_2.getSelectedItem();
			    String qry="update registration set name=?,mobile=?,email=?,sports=?,batch=?,address=?,age=?,gender=? where email=?";
			    int i=0;
			    Connection con=DBInfo.getConnection();
			    try {
					PreparedStatement ps=con.prepareStatement(qry);
					ps.setString(1, s1);
					ps.setString(2, s2);
					ps.setString(3, s3);
					ps.setString(4, s4);
					ps.setString(5, s5);
					ps.setString(6, s6);
					ps.setString(7, s7);
					ps.setString(8, s8);
					ps.setString(9, usermail);
					i=ps.executeUpdate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    if(i!=0)
				{
					JOptionPane.showMessageDialog(getParent(), "Updated Successfully ","Success", JOptionPane.INFORMATION_MESSAGE);
					new ViewUsr(usermail).setVisible(true);
					dispose();
				}
				if(i==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Updation Failed","Failed",JOptionPane.ERROR_MESSAGE); 
				}		
				
			}
		});
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new User(usermail).setVisible(true);
			dispose();
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(110)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_7))
					.addGap(179)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton)
							.addGap(52))
						.addComponent(comboBox_2, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboBox_1, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
						.addComponent(textField_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
						.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
						.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
						.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(110, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(306)
					.addComponent(lblNewLabel_8)
					.addContainerGap(308, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_8)
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(lblNewLabel_7))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
