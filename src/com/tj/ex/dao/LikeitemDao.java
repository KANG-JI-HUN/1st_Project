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

import com.tj.ex.dto.LikeitemDto;

public class LikeitemDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public LikeitemDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 아이디별 선택한 아이템목록
	public ArrayList<LikeitemDto> list(int startRow, int endRow) {
		ArrayList<LikeitemDto> dtos = new ArrayList<LikeitemDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = " SELECT * FROM "
						+ " (SELECT ROWNUM RN, A.* FROM "
						+ " (SELECT * FROM LIKEITEM ORDER BY LNO DESC) A) "
					+ " WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int lNo 	= rs.getInt("lNo");
				 String cId = rs.getString("cId");
				 int iNo    = rs.getInt("iNo");
				 int lCnt   = rs.getInt("lCnt");
				 dtos.add(new LikeitemDto(lNo, cId, iNo, lCnt));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	// 선택한 아이템넣기(cId, iNo, lCnt)
	public int input(String cId, int iNo, int lCnt) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "INSERT INTO LIKEITEM(LNO, CID, INO, LCNT) " + 
				"    VALUES(LIKEITEM_SEQ.NEXTVAL, ?, ?, ?) ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setInt(2, iNo);
			pstmt.setInt(3, lCnt);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "선택한상품 넣기성공":"선택한상품 넣기실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return result;
	}
	// 선택한 아이템 글갯수
	public int getLikeitemTotCnt() {
		int cnt = 0;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT COUNT(*) FROM LIKEITEM ";
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
				if (rs 	  != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return cnt;
	}
}
