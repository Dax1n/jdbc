package com.daxin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * JDBC封装
 * 
 * 
 * @author Daxin
 *
 */
public class DBConnection {
	
	// 三属性、四方法
	// 三大核心接口
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * 
	 * 创建数据库的连接
	 * @return
	 */
	public Connection getConnection() {
		try {
			// 1: 加载连接驱动，Java反射原理
			Class.forName(Config.CLASS_NAME);
			// 2:创建Connection接口对象，用于获取MySQL数据库的连接对象。三个参数：url连接字符串 账号 密码
			String url = Config.DATABASE_URL + "://" + Config.SERVER_IP + ":" + Config.SERVER_PORT + "/"
					+ Config.DATABASE_SID;
			conn = DriverManager.getConnection(url, Config.USERNAME, Config.PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库的方法
	 */
	public void closeConn() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 增删改语句的方法
	 * 
	 * @param pstmt
	 * @return
	 */
	public int execUpdate(PreparedStatement pstmt) {
		try {
			// 1、使用Statement对象发送SQL语句
			int affectedRows = pstmt.executeUpdate();
			// 2、返回结果
			return affectedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 执行查询语句
	 * 
	 * @param pstmt
	 * @return
	 */
	public ResultSet execQuery(PreparedStatement pstmt) {
		try {
			// 1、使用Statement对象发送SQL语句
			rs = pstmt.executeQuery();
			// 2、返回结果
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}












