package com.motor_model.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class pictureInsert {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ba10102";
	String passwd = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO MOTOR_MODEL"+
	" (modtype, brand, displacement, name, renprice, saleprice, motpic"+ 
	") VALUES ('MMH'||LPAD(TO_CHAR(modtype_seq.NEXTVAL), 6,'0'), ?, ?, ?, ?, ?,?)";
	
	private static final String UPDATE = "UPDATE MOTOR_MODEL set brand=?,"+
	" displacement=?, name=?, renprice=?, saleprice=?, motpic=? where modtype = ?";
	
	private static final String DELETE = "DELETE FROM MOTOR_MODEL where modtype = ?";
	
	private static final String GET_ONE = "SELECT modtype, brand, displacement,"
			+ "  name, renprice, saleprice, motpic FROM MOTOR_MODEL where modtype = ?";
	
	private static final String GET_ALL = "SELECT modtype, brand, displacement,"
			+ "  name, renprice, saleprice, motpic FROM MOTOR_MODEL";
	
	public void insert(MotorModelVO mmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, mmVO.getBrand());
			pstmt.setInt(2, mmVO.getDisplacement());	
			pstmt.setString(3, mmVO.getName());
			pstmt.setInt(4, mmVO.getRenprice());			
			pstmt.setInt(5, mmVO.getSaleprice());			
			pstmt.setBytes(6, mmVO.getMotpic());			
					
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
