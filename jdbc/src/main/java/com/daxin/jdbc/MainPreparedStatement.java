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
public class MainPreparedStatement {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {

		DBConnection utils = new DBConnection();
		String sql = "insert into user (name,age) values(?,?)";
		

		Connection conn = utils.getConnection();
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		long start=System.nanoTime();
		for (int i = 1; i <= 2000; i++) {
			 
			pStmt.setString(1, String.valueOf(i));
			pStmt.setInt(2, i);
			pStmt.addBatch();
			
		
		}
		
		
		
		int[] re = pStmt.executeBatch();
//		pStmt.execute();//如果使用批处理的话，执行pStmt.execute()只会插进去一条数据
		long end=System.nanoTime();
		
		pStmt.close();
		conn.close();
		//MainPreparedStatement = 3027毫秒
		System.out.println("MainPreparedStatement = "+TimeUnit.NANOSECONDS.toMillis(end-start)+"毫秒");
		
		
		
		
		
		
		
		
		
		
	}
}
