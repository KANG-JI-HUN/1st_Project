package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.OafterDto;

public class OafterDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public OafterDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 후기목록
	public ArrayList<OafterDto> list(int startRow, int endRow) {
		ArrayList<OafterDto> dtos = new ArrayList<OafterDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = " SELECT * FROM "
						+ "    (SELECT ROWNUM RN, A.* FROM "
						+ "        (SELECT * FROM OAFTER ORDER BY ONO DESC) A) "
						+ "    WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int oNo 		  = rs.getInt("oNo");
				 String cId 	  = rs.getString("cId");
				 int iNo 		  = rs.getInt("iNo");
				 String oSubject  = rs.getString("oSubject");
				 String oContent  = rs.getString("oContent");
				 String oFilename = rs.getString("oFilename");
				 int oReadcount   = rs.getInt("oReadcount");
				 String oPw 	  = rs.getString("oPw");
				 Date oDate		  = rs.getDate("oDate");
				 dtos.add(new OafterDto(oNo, cId, iNo, oSubject, oContent,
						 oFilename, oReadcount, oPw, oDate));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());}
		}
		return dtos;
	}
	// 후기 글갯수
	public int getOafterTotCnt() {
		int cnt = 0;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 		  	= null;
		String sql = " SELECT COUNT(*) FROM OAFTER ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return cnt;
	}
	// 후기쓰기(cId, iNo, oSubject, oContent, oFilename, oPw)
	public int write(String cId, int iNo, String oSubject, String oContent,
						String oFilename, String oPw) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO OAFTER "
						+ "(ONO, CID, INO, OSUBJECT, OCONTENT, OFILENAME, OPW) "
						+ " VALUES(OAFTER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setInt(2, iNo);
			pstmt.setString(3, oSubject);
			pstmt.setString(4, oContent);
			pstmt.setString(5, oFilename);
			pstmt.setString(6, oPw);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "후기 글쓰기 성공":"후기 글쓰기 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
					System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// oReadcount 하나 올리기
	private void hitUp(int oNo) {
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE OAFTER SET OREADCOUNT = OREADCOUNT + 1 WHERE ONO = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	// 후기 상세보기(oNo로 글 dto보기)
	public OafterDto contentView(int oNo) {
		hitUp(oNo);
		OafterDto dto = null;
		Connection conn 	 	= null;
		PreparedStatement pstmt = null;
		ResultSet rs 	  		= null;
		String sql = " SELECT O.*, CNAME FROM OAFTER O, CUSTOMER C WHERE O.CID=C.CID AND ONO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 String cId 	  = rs.getString("cId");
				 int iNo 		  = rs.getInt("iNo");
				 String oSubject  = rs.getString("oSubject");
				 String oContent  = rs.getString("oContent");
				 String oFilename = rs.getString("oFilename");
				 int oReadcount   = rs.getInt("oReadcount");
				 String oPw 	  = rs.getString("oPw");
				 Date oDate		  = rs.getDate("oDate");
				 dto = new OafterDto(oNo, cId, iNo, oSubject, oContent,
						 oFilename, oReadcount, oPw, oDate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs 	  != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	// 후기 수정하기(oNo, iNo, oSubject, oContent, oFilename, oPw, oDate)
	public int modify(int oNo, int iNo, String oSubject, String oContent, String oFilename, String oPw) {
		int result = FAIL;
		Connection conn		    = null;
		PreparedStatement pstmt = null;
			String sql = " UPDATE OAFTER "
							+ " SET OSUBJECT = ?, "
							+ " OCONTENT = ?, "
							+ " OFILENAME = ?, "
							+ " OPW = ?, "
							+ " ODATE = SYSDATE "
						+ " WHERE ONO = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oSubject);
			pstmt.setString(2, oContent);
			pstmt.setString(3, oFilename);
			pstmt.setString(4, oPw);
			pstmt.setInt(5, oNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "후기 글수정 성공" : "후기 글수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 수정 modifyView
	public OafterDto modifyView(int oNo) {
		OafterDto dto = null;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = "";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cId 		 = rs.getString("cId");
				int iNo  		 = rs.getInt("iNo");
				String oSubject  = rs.getString("oSubject");
				String oContent  = rs.getString("oContent");
				String oFilename = rs.getString("oFilename");
				int oReadcount   = rs.getInt("oReadcount");
				String oPw 		 = rs.getString("oPw");
				Date oDate 		 = rs.getDate("oDate");
				dto = new OafterDto(oNo, cId, iNo, oSubject, oContent, oFilename, oReadcount, oPw, oDate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs    != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	// 후기 삭제하기
	public int delete(int oNo) {
		int result = FAIL;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		String sql = " DELETE FROM OAFTER WHERE ONO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "후기 글삭제 성공" : "후기 글삭제 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}	
}