import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class DBInfo {
	   
	   
      static {
    	  
    	  try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
						e.printStackTrace();
		}
      }
      

  public static Connection getConnection()
    {
    	Connection con=null;
    	try {
    		con =DriverManager.getConnection("jdbc:mysql://localhost:3306/stadium","root","rat");
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return con;
    }
    	  
     public static Vector<String> getValue(String TableName)
     {
    	 Vector<String> v= new Vector<>();
    	 v.add("Select");
    	 Connection con=getConnection();
    	 String query="select name from " + TableName+" order by name";
    	 try {
    		 PreparedStatement ps=con.prepareStatement(query);
    		 ResultSet  res=ps.executeQuery();
    		 while(res.next())
    		 { 
    			 String s=res.getString(1);
    			 v.add(s.toUpperCase());
    			 
    			 
    		 }
    		 
    	 }catch(Exception e1)
    	 {
    		 e1.printStackTrace();
    	 }
    	return v;	 
    	 
     }
     static Vector<String> v1=new Vector<>();
     public static Object[] getValue1(String TableName)
     {
    	 v1.clear();
    	 
    	 v1.add("Select");
    	 Connection con=getConnection();
    	 String query="select name from " + TableName;
    	 try {
    		 PreparedStatement ps=con.prepareStatement(query);
    		 ResultSet  res=ps.executeQuery();
    		 while(res.next())
    		 {  
    			 String s=res.getString(1);
    			 v1.add(s.toUpperCase());
    			 
    		 }
    		 
    		 
    	 }catch(Exception e1)
    	 {
    		 e1.printStackTrace();
    	 }
    	 
    	return v1.toArray();	 
    	 
      }
     
      
    
      
}
