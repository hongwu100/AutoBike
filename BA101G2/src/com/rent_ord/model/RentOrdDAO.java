package com.rent_ord.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RentOrdDAO implements RentOrdDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	private static final String INSERT_STMT = "INSERT INTO RENT_ORD"+
	" (rentno, memno, motno, slocno, rlocno, milstart, milend, filldate, "+
	"startdate, enddate, returndate, fine, total, rank, status, note"+ 
	") VALUES ('R'||LPAD(TO_CHAR(rentno_seq.NEXTVAL), 6,'0'), ?, ?, ?, ?,"+
	" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE = "UPDATE RENT_ORD set memno=?, motno=?,"+
	" slocno=?, rlocno=?, milstart=?, milend=?, filldate=?, startdate=?, enddate=?,"
	+ "returndate=?, fine=?, total=?, rank=?, status=?, note=? where rentno = ?";
	
	private static final String DELETE = "DELETE FROM RENT_ORD where rentno = ?";
	
	private static final String selectFactor = "SELECT rentno, memno, motno, slocno, rlocno,"+
	" milstart, milend, to_char(filldate,'yyyy-mm-dd hh:mm:ss') filldate, "+
	"to_char(startdate,'yyyy-mm-dd hh:mm:ss') startdate, "+
	"to_char(enddate,'yyyy-mm-dd hh:mm:ss') enddate, "+
	"to_char(returndate,'yyyy-mm-dd hh:mm:ss') returndate, fine, total,"+
	" rank, status, note ";
	
	private static final String GET_ALL = 
			selectFactor + " FROM RENT_ORD";
	
	private static final String GET_ONE = 
			selectFactor + " FROM RENT_ORD where rentno = ?";
	
	private static final String GET_BY_START_LOC_NO = 
			selectFactor + " FROM RENT_ORD where slocno = ?";
	
	private static final String GET_BY_RETURN_LOC_NO = 
			selectFactor + " FROM RENT_ORD where rlocno = ?";
	
	private static final String GET_BY_STATUS=  
			selectFactor + " FROM RENT_ORD where status = ?";

	private static final String GET_BY_STARTTIME_PEROID=  
			selectFactor + " FROM RENT_ORD  where startdate"+
			" between ? and ? order by startdate";
	
	private static final String GET_BY_ENDTIME_PEROID= selectFactor + " From RENT_ORD  where enddate"+
			" between ? and ? order by enddate";	

	@Override
	public void insert(RentOrdVO roVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
/*			(沒有rentno) 下面計15個attribute
  			memno, motno, slocno, rlocno, milstart, milend, filldate, "+
			"startdate, enddate, returndate, fine, total, rank, status, note"+ 
*/
			
			pstmt.setString(1, roVO.getMemno());
			pstmt.setString(2, roVO.getMotno());
			pstmt.setString(3, roVO.getSlocno());
			pstmt.setString(4, roVO.getRlocno());
			pstmt.setInt(5, roVO.getMilstart());			
			pstmt.setInt(6, roVO.getMilend());				
			pstmt.setTimestamp(7, roVO.getFilldate());				
			pstmt.setTimestamp(8, roVO.getStartdate());				
			pstmt.setTimestamp(9, roVO.getEnddate());				
			pstmt.setTimestamp(10, roVO.getReturndate());				
			pstmt.setInt(11, roVO.getFine());			
			pstmt.setInt(12, roVO.getTotal());			
			pstmt.setString(13, roVO.getRank());
			pstmt.setString(14, roVO.getStatus());			
			pstmt.setString(15, roVO.getNote());			
			
			pstmt.executeUpdate();

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

	@Override
	public void update(RentOrdVO rental_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rental_orderVO.getMemno());
			pstmt.setString(2, rental_orderVO.getMotno());
			pstmt.setString(3, rental_orderVO.getSlocno());
			pstmt.setString(4, rental_orderVO.getRlocno());
			pstmt.setInt(5, rental_orderVO.getMilstart());			
			pstmt.setInt(6, rental_orderVO.getMilend());				
			pstmt.setTimestamp(7, rental_orderVO.getFilldate());				
			pstmt.setTimestamp(8, rental_orderVO.getStartdate());				
			pstmt.setTimestamp(9, rental_orderVO.getEnddate());				
			pstmt.setTimestamp(10, rental_orderVO.getReturndate());				
			pstmt.setInt(11, rental_orderVO.getFine());			
			pstmt.setInt(12, rental_orderVO.getTotal());			
			pstmt.setString(13, rental_orderVO.getRank());
			pstmt.setString(14, rental_orderVO.getStatus());			
			pstmt.setString(15, rental_orderVO.getNote());	
			pstmt.setString(16, rental_orderVO.getRentno());
			
			

			pstmt.executeUpdate();

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

	@Override
	public void delete(String rentno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			// 1●設定於 pstmt.executeUpdate()之前
			con.setAutoCommit(false);


			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, rentno);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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



	@Override
	public List<RentOrdVO> getAll() {
		List<RentOrdVO> list = new ArrayList<RentOrdVO>();
		RentOrdVO roVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			/*計16個attribute
  			rentno,memno, motno, slocno, rlocno, milstart, milend, filldate, "+
			"startdate, enddate, returndate, fine, total, rank, status, note"+ 
			 */

			while (rs.next()) {
				roVO = new RentOrdVO();
				roVO.setRentno(rs.getString("rentno"));
				roVO.setMemno(rs.getString("memno"));
				roVO.setMotno(rs.getString("motno"));				
				roVO.setSlocno(rs.getString("slocno"));
				roVO.setRlocno(rs.getString("rlocno"));
				roVO.setMilstart(rs.getInt("milstart"));
				roVO.setMilend(rs.getInt("milend"));
				roVO.setFilldate(rs.getTimestamp("filldate"));
				roVO.setStartdate(rs.getTimestamp("startdate"));
				roVO.setEnddate(rs.getTimestamp("enddate"));
				roVO.setReturndate(rs.getTimestamp("returndate"));
				roVO.setFine(rs.getInt("fine"));
				roVO.setTotal(rs.getInt("total"));
				roVO.setRank(rs.getString("rank"));
				roVO.setStatus(rs.getString("status"));
				roVO.setNote(rs.getString("note"));						
				list.add(roVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
	
	@Override
	public RentOrdVO findByPrimaryKey(String rentno) {

		RentOrdVO roVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, rentno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				roVO = new RentOrdVO();
				roVO.setRentno(rs.getString("rentno"));
				roVO.setMemno(rs.getString("memno"));
				roVO.setMotno(rs.getString("motno"));				
				roVO.setSlocno(rs.getString("slocno"));
				roVO.setRlocno(rs.getString("rlocno"));
				roVO.setMilstart(rs.getInt("milstart"));
				roVO.setMilend(rs.getInt("milend"));
				roVO.setFilldate(rs.getTimestamp("filldate"));
				roVO.setStartdate(rs.getTimestamp("startdate"));
				roVO.setEnddate(rs.getTimestamp("enddate"));
				roVO.setReturndate(rs.getTimestamp("returndate"));
				roVO.setFine(rs.getInt("fine"));
				roVO.setTotal(rs.getInt("total"));
				roVO.setRank(rs.getString("rank"));
				roVO.setStatus(rs.getString("status"));
				roVO.setNote(rs.getString("note"));						
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return roVO;
	}

	@Override
	public Set<RentOrdVO> getRentalOrdersByStatus(String status) {
		Set<RentOrdVO> set = new LinkedHashSet<RentOrdVO>();
		RentOrdVO roVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_STATUS);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				roVO = new RentOrdVO();
				setAttirbute(roVO, rs);  //拉出來寫成一個方法
	
				set.add(roVO); // Store the row in the vector
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return set;
	}

	@Override
	public Set<RentOrdVO> getRentalOrdersByRentLoc(String slocno) {
		Set<RentOrdVO> set = new LinkedHashSet<RentOrdVO>();
		RentOrdVO roVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_START_LOC_NO);
			pstmt.setString(1, slocno);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				roVO = new RentOrdVO();
				setAttirbute(roVO, rs);  //拉出來寫成一個方法
	
				set.add(roVO); // Store the row in the vector
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return set;
	}

	@Override
	public Set<RentOrdVO> getRentalOrdersByReturnLoc(String rlocno) {
		Set<RentOrdVO> set = new LinkedHashSet<RentOrdVO>();
		RentOrdVO roVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_RETURN_LOC_NO);
			pstmt.setString(1, rlocno);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				roVO = new RentOrdVO();
				setAttirbute(roVO, rs); //拉出來寫成一個方法	
				set.add(roVO); // Store the row in the vector
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return set;
	}

	@Override
	public Set<RentOrdVO> getRentalOrdersByStartDatePrioid(Timestamp start_time, Timestamp end_time) {
		Set<RentOrdVO> set = new LinkedHashSet<RentOrdVO>();
		RentOrdVO roVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_STARTTIME_PEROID);
			pstmt.setTimestamp(1, start_time);
			pstmt.setTimestamp(2, end_time);			
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				roVO = new RentOrdVO();
				setAttirbute(roVO, rs); //拉出來寫成一個方法	
				set.add(roVO); // Store the row in the vector
			}
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return set;
	}

	@Override
	public Set<RentOrdVO> getRentalOrdersByEndDatePrioid(Timestamp start_time, Timestamp end_time) {
		Set<RentOrdVO> set = new LinkedHashSet<RentOrdVO>();
		RentOrdVO roVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ENDTIME_PEROID);
			pstmt.setTimestamp(1, start_time);
			pstmt.setTimestamp(2, end_time);			
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				roVO = new RentOrdVO();
				setAttirbute(roVO, rs); //拉出來寫成一個方法	
				set.add(roVO); // Store the row in the vector
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return set;
	}

	private void setAttirbute(RentOrdVO roVO, ResultSet rs) {
		
	
		try {
			roVO.setMotno(rs.getString("motno"));
			roVO.setRentno(rs.getString("rentno"));
			roVO.setMemno(rs.getString("memno"));
			roVO.setSlocno(rs.getString("slocno"));
			roVO.setRlocno(rs.getString("rlocno"));
			roVO.setMilstart(rs.getInt("milstart"));
			roVO.setMilend(rs.getInt("milend"));
			roVO.setFilldate(rs.getTimestamp("filldate"));
			roVO.setStartdate(rs.getTimestamp("startdate"));
			roVO.setEnddate(rs.getTimestamp("enddate"));
			roVO.setReturndate(rs.getTimestamp("returndate"));
			roVO.setFine(rs.getInt("fine"));
			roVO.setTotal(rs.getInt("total"));
			roVO.setRank(rs.getString("rank"));
			roVO.setStatus(rs.getString("status"));
			roVO.setNote(rs.getString("note"));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	
	}
}
