package com.daxin.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


/**
 *
 * 正确使用PreparedStatement+批处理提升性能
 *
 */
public class MainPreparedStatementError {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {

		DBConnection utils = new DBConnection();
		String sql = "insert into user (name,age) values(?,?)";
		

		Connection conn = utils.getConnection();
		PreparedStatement pStmt = null;
		
//		PreparedStatement: 数据库会对sql语句进行预编译，下次执行相同的sql语句时，数据库端不会再进行预编译了，而直接用数据库的缓冲区，提高数据访问的效率（但尽量采用使用？号的方式传递参数），如果sql语句只执行一次，以后不再复用。
//		 从安全性上来看，PreparedStatement是通过?来传递参数的，避免了拼sql而出现sql注入的问题，所以安全性较好。
		long start=System.nanoTime();
		for (int i = 1; i <= 2000; i++) {
			pStmt = conn.prepareStatement(sql);//放在此处也是只要相同sql只编译一次，但是放在此处不规范
			pStmt.setString(1, String.valueOf(i));
			pStmt.setInt(2, i);
			pStmt.executeUpdate();//最好还是使用批处理，在2000条记录时候，比批处理差200ms左右
		}
		
		
		
//		pStmt.execute();//如果使用批处理的话，执行pStmt.execute()只会插进去一条数据
		long end=System.nanoTime();
		
		pStmt.close();
		conn.close();
		//MainPreparedStatementError = 3132毫秒
		System.out.println("MainPreparedStatementError = "+TimeUnit.NANOSECONDS.toMillis(end-start)+"毫秒"); 
		
		
		
		
		
		
		
		
		
		
	}
}
