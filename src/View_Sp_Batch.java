import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View_Sp_Batch extends JFrame {

	private JPanel contentPane;
	String query;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public View_Sp_Batch(String emailadmin,String mng) {
		Vector<String> cols=new Vector<>();
		Vector<Vector<String>> rows=new Vector<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 474);
		setLocationRelativeTo(this);
		setTitle("Raw Data");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Back");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Admin(emailadmin).setVisible(true);
				dispose();
			}
		});
		
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		if(mng.equalsIgnoreCase("sports")||mng.equalsIgnoreCase("batchtime"))
			query="Select * from "+mng;
		else if(mng.equalsIgnoreCase("user"))
		{
			 query="select name,mobile,email,sports,batch,address,age,gender from registration where usertype='user'" ;
			
		}
		else if(mng.equalsIgnoreCase("coach"))
		{
			 query="select name,mobile,email,sports,address,age,gender from registration where usertype='coach'" ;
		}
		else
		{
			query="select name,mobile,email,address,age,gender from registration usertype='admin'" ;
		}
		Connection con=DBInfo.getConnection();
		   try {
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet res=ps.executeQuery();
			ResultSetMetaData rsmd=res.getMetaData();
			int colCount=rsmd.getColumnCount();
			
			for(int i=2;i<=colCount;i++)
			{
				String s1=rsmd.getColumnName(i);
				cols.add(s1);
			}
			
			
			
			
			while(res.next())
			{
				Vector<String> v=new Vector<>();
				for(int i=2;i<=colCount;i++)
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
