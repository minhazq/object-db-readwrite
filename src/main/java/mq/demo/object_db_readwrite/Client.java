package mq.demo.object_db_readwrite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client {

	public static void main (String args[]) throws ClassNotFoundException, SQLException, IOException{
		Connection conn ;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://mquraishi.com:3306/pawebdb", "mquraishi", "Nopassword1");
		
		MyClass mClass = new MyClass();
		mClass.setName("this is setting object Number 2");
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream ous = new ObjectOutputStream(bos);
		
		ous.writeObject(mClass);
		ous.flush();
		ous.close();
		bos.close();
		
		byte[]  data = bos.toByteArray();
		String sql="insert into javaobject (javaobject) values(?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, data);
		ps.execute();
		
		
	}
	
	
}
