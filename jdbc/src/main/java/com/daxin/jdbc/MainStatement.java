package com.daxin.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.StatementImpl;

/**
 * 
 * 使用Statement进行数据存储
 *
 */
public class MainStatement {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {

		DBConnection utils = new DBConnection();
		String sql = "insert into user (name,age) values(?,?)";

		Connection conn = utils.getConnection();
		// Statement每次执行sql语句，数据库都要执行sql语句的编译，最好用于仅执行一次查询并返回结果的情形，效率高于PreparedStatement.但存在sql注入风险。
		Statement stmt = conn.createStatement();

		long start = System.nanoTime();
		for (int i = 1; i <= 20000; i++) {
			// Statement一次执行一编译，对于单次执行的话性能优于PrepareStatement。但是批量的话性能不好
			stmt.executeUpdate("insert into user (name,age) values(" + i + "," + i + ")");

		}

		long end = System.nanoTime();

		stmt.close();
		conn.close();
		System.out.println("Statement = " + TimeUnit.NANOSECONDS.toMillis(end - start) + "毫秒");// Statement
																								// =
																								// 28034毫秒

	}
}
