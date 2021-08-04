import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Tour extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Tour(String link) {
		Vector<String> cols=new Vector<>();
		Vector<Vector<String>> rows=new Vector<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Add");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String s1= (String)JOptionPane.showInputDialog(getParent(), "Select Sports","Choose",JOptionPane.PLAIN_MESSAGE,null,DBInfo.getValue1("sports"),(Object)"Select");
				String s2=JOptionPane.showInputDialog("Enter date");
				String s3=JOptionPane.showInputDialog("Enter venue");
				String qry="insert into tounaments values(?,?,?,?)";
				Connection con=DBInfo.getConnection();
				int i=0;
				try {
					PreparedStatement ps=con.prepareStatement(qry);
					ps.setInt(1, 0);
					ps.setString(2, s1);
					ps.setString(3, s2);
					ps.setString(4, s3);
					i=ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(i!=0)
				{
					JOptionPane.showMessageDialog(getParent(), "Tournament Scheduled successfully","Success",JOptionPane.INFORMATION_MESSAGE);
					new Tour(link).setVisible(true);
					dispose();
				}
				if(i==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Tournament not Added!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Delete");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String s11=JOptionPane.showInputDialog("Enter sno");
				int s1=Integer.parseInt(s11);
				if(s11.length()==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Enter a valid input","Error",JOptionPane.ERROR_MESSAGE);
				}
				String q1="select * from tounaments where sno=?";
				Connection con=DBInfo.getConnection();
				int flag=0;
				try {
					PreparedStatement ps=con.prepareStatement(q1);
					ps.setInt(1, s1);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						flag=1;
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				if(flag==1)
				{
					int k=JOptionPane.showConfirmDialog(getParent(),"Do you want to delete!","Confirm",JOptionPane.YES_NO_OPTION);
					if(k==0)
					{
						String q="delete from tounaments where sno=?";
						int i=0;
						try{
							PreparedStatement ps1=con.prepareStatement(q);
							ps1.setInt(1, s1);
							i=ps1.executeUpdate();
						}
						catch(Exception e1) {
							e1.printStackTrace();
						}
						if(i!=0)
						{
							JOptionPane.showMessageDialog(getParent(), "record deleted successfully","Success",JOptionPane.INFORMATION_MESSAGE);
							new Tour(link).setVisible(true);
							dispose();
						}
						if(i==0)
						{
							JOptionPane.showMessageDialog(getParent(), "record deletion failed","Error",JOptionPane.ERROR_MESSAGE);
							
						}
					
					}}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "record not found","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
					
				
			
		});
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Back");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Admin(link).setVisible(true);
				dispose();
			}
		});
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		String query="select * from tounaments";
		Connection con=DBInfo.getConnection();
		   try {
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet res=ps.executeQuery();
			ResultSetMetaData rsmd=res.getMetaData();
			int colCount=rsmd.getColumnCount();
			
			for(int i=1;i<=colCount;i++)
			{
				String s1=rsmd.getColumnName(i);
				cols.add(s1);
			}
			
			
			
			
			while(res.next())
			{
				Vector<String> v=new Vector<>();
				for(int i=1;i<=colCount;i++)
				{
					
					v.add(res.getString(i));
				}
				rows.add(v);
			}
			
			JTable table=new JTable(rows,cols);
			JScrollPane pane=new JScrollPane(table);
			getContentPane().add(pane);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
