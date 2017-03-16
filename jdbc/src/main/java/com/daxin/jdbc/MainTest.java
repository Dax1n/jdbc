package com.daxin.jdbc;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
/**
 * 
 * 通过获取元数据列的count，然后根据count获取数据
 * 
 * @author Daxin
 *
 */
public class MainTest {
	public static void main(String[] args) throws Exception {

		DBConnection utils = new DBConnection();
		String sql = "select * from user where name =?";

		Connection conn = utils.getConnection();
		PreparedStatement pStmt = conn.prepareStatement(sql);

		pStmt.setString(1, "666");

		ResultSet rs = pStmt.executeQuery();

		ResultSetMetaData metaData = pStmt.getMetaData();

		ParameterMetaData parameterMetaData = pStmt.getParameterMetaData();

		System.out.println(metaData);
		System.out.println(parameterMetaData.getParameterCount());// 一个参数
		int columns = metaData.getColumnCount();
		System.out.println("-----------------------------------------------------");
		System.out.println("-----------------------------------------------------");
		while (rs.next()) {

			for (int i = 1; i <= columns; i++) {
				String columnName = metaData.getColumnName(i);

				//System.out.println("columnName = " + columnName);

			}
			
			/**
			 * 根据列获取数据
			 */
			for (int i = 1; i <= columns; i++) {
				System.out.println(rs.getString(i));

			}

		}

	}

}
