package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.ItemlistDto;

public class ItemlistDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public ItemlistDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// 상품목록
	public ArrayList<ItemlistDto> list(int startRow, int endRow) {
		ArrayList<ItemlistDto> dtos = new ArrayList<ItemlistDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM "
				+ " (SELECT * FROM ITEMLIST ORDER BY INO DESC) A) " + " WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int iNo 		 = rs.getInt("iNo");
				String aId 		 = rs.getString("aId");
				String iName 	 = rs.getString("iName");
				String iSubject  = rs.getString("iSubject");
				String iInfo	 = rs.getString("iInfo");
				int iAmount 	 = rs.getInt("iAmount");
				String iFilename = rs.getString("iFilname");
				int iReadcount   = rs.getInt("iReadcount");
				dtos.add(new ItemlistDto(iNo, aId, iName, iSubject, iInfo, iAmount, iFilename, iReadcount));
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
		return dtos;
	}

	// 상품갯수
	public int getItemlistTotCnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT COUNT(*) FROM ITEMLIST ";
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

	// 상품목록 글쓰기(aId, iName, iSubject, iInfo, iAmount, iFilename)
	public int write(String aId, String iName, String iSubject, String iInfo, int iAmount, String iFilename) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO ITEMLIST(INO, AID, INAME, ISUBJECT, IINFO, IAMOUNT, IFILENAME) "
				+ " VALUES(ITEMLIST_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, iName);
			pstmt.setString(3, iSubject);
			pstmt.setString(4, iInfo);
			pstmt.setInt(5, iAmount);
			pstmt.setString(6, iFilename);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "상품 글쓰기 성공" : "상품 글쓰기 실패");
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

	// 조회수 올리기
	private void hitUp(int iNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE ITEMLIST SET IREADCOUNT = IREADCOUNT + 1 WHERE INO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNo);
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

	// 상품글 상세보기(조회수 UP + iNo로 dto리턴)
	public ItemlistDto contentView(int iNo) {
		hitUp(iNo);
		ItemlistDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT I.*, INAME FROM ITEMLIST I, ADMIN A " + " WHERE I.AID=A.AID AND INO =? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId 		 = rs.getString("aId");
				String iName 	 = rs.getString("iName");
				String iSubject  = rs.getString("iSubject");
				String iInfo 	 = rs.getString("iInfo");
				int iAmount 	 = rs.getInt("iAmount");
				String iFilename = rs.getString("iFilname");
				int iReadcount   = rs.getInt("iReadcount");
				dto = new ItemlistDto(iNo, aId, iName, iSubject, iInfo, iAmount, iFilename, iReadcount);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}

	// 상품글 수정하기(aId, iName, iSubject, iInfo, iAmount, iFilename)
	public int modify(int iNo, String iName, String iSubject, String iInfo, int iAmount, String iFilename) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE ITEMLIST " + " SET INAME = ?, " + " ISUBJECT = ?, " + " IINFO = ?, " + " IAMOUNT = ? "
				+ " IFILENAME = ? " + " WHERE INO= ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iName);
			pstmt.setString(2, iSubject);
			pstmt.setString(3, iInfo);
			pstmt.setInt(4, iAmount);
			pstmt.setString(5, iFilename);
			pstmt.setInt(6, iNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "상품 글수정 성공" : "상품 글수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 수정 view(fNo로 iNo리턴)
	public ItemlistDto modifyView(int iNo) {
		ItemlistDto dto = null;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = "";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
					String aId 		 = rs.getString("aId");
					String iName 	 = rs.getString("iName");
					String iSubject  = rs.getString("iSubject");
					String iInfo	 = rs.getString("iInfo");
					int iAmount 	 = rs.getInt("iAmount");
					String iFilename = rs.getString("iFilname");
					int iReadcount   = rs.getInt("iReadcount");
					dto = new ItemlistDto(iNo, aId, iName, iSubject, iInfo, iAmount, iFilename, iReadcount);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs 	  != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	// 상품글 삭제하기(iNo로 글 삭제하기)
	public int delete(int iNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " DELETE FROM ITEMLIST WHERE INO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "상품 글삭제 성공" : "상품 글삭제 실패");
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