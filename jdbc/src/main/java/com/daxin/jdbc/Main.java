package com.daxin.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.StatementImpl;

/**
 * Hello world!
 *
 */
public class Main {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {

		DBConnection utils = new DBConnection();
		String sql = "insert into user (name,age) values(?,?)";
		

		Connection conn = utils.getConnection();
		Statement stmt = conn.createStatement();
		//预编译，不要在for循环中每一次都编译，这样反而性能更差
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		
//		conn.setReadOnly(true);//设置conn只读
		
		long start=System.nanoTime();
		for (int i = 1; i <= 2000; i++) {
			 
			pStmt.setString(1, String.valueOf(i));
			pStmt.setInt(2, i);
			pStmt.addBatch();
		
			
			//stmt.executeUpdate("insert into user (name,age) values("+i+","+i+")");
			
		}
		
		StatementImpl tmp = ((StatementImpl)pStmt);
		
		
		int[] re = pStmt.executeBatch();
//		pStmt.execute();//如果使用批处理的话，执行pStmt.execute()只会插进去一条数据
		long end=System.nanoTime();
		
		
		System.out.println(end-start);//2925231784
		conn.close();

		
		long l1=2706252823l;
		long l2=2763415405l;
		//纳秒转秒
		System.out.println(TimeUnit.NANOSECONDS.toMillis(l2-l1));//57
//		//秒转毫秒
//		System.out.println(TimeUnit.SECONDS.toMillis(1));
//		//纳秒转毫秒为10的6次幂
//		System.out.println((l2-l1)/1000000);
		
		
		
		
		
		
		
		
		
		
	}
}
