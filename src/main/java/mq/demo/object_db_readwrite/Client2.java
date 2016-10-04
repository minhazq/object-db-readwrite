package mq.demo.object_db_readwrite;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client2 {

	public static void main (String args[]) throws ClassNotFoundException, SQLException, IOException{
		Connection conn ;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://mquraishi.com:3306/pawebdb", "mquraishi", "Nopassword1");
		
		String sql = "select * from javaobject where id = 2";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();
		
		if(resultSet.next()){
			ByteArrayInputStream bis;
			ObjectInputStream ois;
			
			bis = new ByteArrayInputStream(resultSet.getBytes("javaobject"));
			ois = new ObjectInputStream(bis);
			
			MyClass  mc = (MyClass) ois.readObject();
			
			System.out.println(mc.getName());
			ois.close();
			bis.close();
		}
		
	}
}
