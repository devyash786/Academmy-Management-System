import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class EquipmentReq extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public EquipmentReq(String link) {
		Vector<String> cols=new Vector<>();
		Vector<Vector<String>> rows=new Vector<>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(this);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Back");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			new Admin(link).setVisible(true);
			dispose();
			}
		});
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
	String query="Select * from equipment";
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
}}
