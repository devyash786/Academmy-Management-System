import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tbl extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Tbl(String tablename,String cngmail) {
		Vector<String> cols=new Vector<>();
		Vector<Vector<String>> rows=new Vector<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Back");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new User(cngmail).setVisible(true);
				dispose();
			}
		});
		
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Connection con=DBInfo.getConnection();
		String s1="select * from "+tablename;
		try {
			PreparedStatement ps=con.prepareStatement(s1);
			ResultSet res=ps.executeQuery();
			
			
			ResultSetMetaData rsmd=res.getMetaData();
			int colCount=rsmd.getColumnCount();
			System.out.println(colCount);
			for(int i=2;i<=colCount;i++)
			{
				String s11=rsmd.getColumnName(i);
				cols.add(s11);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
