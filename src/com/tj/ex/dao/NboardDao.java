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

import com.tj.ex.dto.NboardDto;

public class NboardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public NboardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 공지사항 목록
	public ArrayList<NboardDto> list(int startRow, int endRow) {
		ArrayList<NboardDto> dtos = new ArrayList<NboardDto>();
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = " SELECT * FROM "
						+ " (SELECT ROWNUM RN, A.* FROM "
						+ " (SELECT * FROM NBOARD ORDER BY NNO DESC) A) "
					+ " WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int nNo 		  = rs.getInt("nNo");
				 String aId 	  = rs.getString("aId");
				 String nSubject  = rs.getString("nSubject");
				 String nContent  = rs.getString("nContent");
				 String nFilename = rs.getString("nFilename");
				 int nReadcount   = rs.getInt("nReadcount");
				 String nPw 	  = rs.getString("nPw");
				 Date nDate 	  = rs.getDate("nDate");
				 dtos.add(new NboardDto(nNo, aId, nSubject, nContent,
						 nFilename, nReadcount, nPw, nDate));
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
	// 공지사항 글갯수
	public int getNboardTotCnt() {
		int cnt = 0;
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String sql = " SELECT COUNT(*) FROM QBOARD ";
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
	// 공지사항 글쓰기(aId, nSubject, nContent, nFilename, nPw)
	public int write(String aId, String nSubject, String nContent, String nFilename, String nPw) {
		int result = FAIL;
		Connection conn 	    = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO NBOARD(NNO, AID, NSUBJECT, NCONTENT, NFILENAME, NPW) "
					+ " VALUES(NBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, nSubject);
			pstmt.setString(3, nContent);
			pstmt.setString(4, nFilename);
			pstmt.setString(5, nPw);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "공시사항 글쓰기 성공" : "공지사항 글쓰기 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// nReadcount 하나 올리기
	private void hitUp(int nNo) {
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE NBOARD SET NREADCOUNT = NREADCOUNT + 1 WHERE NNO = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
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
	// 공지글 상세보기(qNo로 글 dto보기)
	public NboardDto contentView(int nNo) {
		hitUp(nNo);
		NboardDto dto = null;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = " SELECT N.*, ANAME FROM NBOARD N, ADMIN A WHERE N.AID=A.AID AND NNO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 String aId 	  = rs.getString("aId");
				 String nSubject  = rs.getString("nSubject");
				 String nContent  = rs.getString("nContent");
				 String nFilename = rs.getString("nFilename");
				 int nReadcount   = rs.getInt("nReadcount");
				 String nPw 	  = rs.getString("nPw");
				 Date nDate 	  = rs.getDate("nDate");
				dto = new NboardDto(nNo, aId, nSubject, nContent, nFilename, nReadcount, nPw, nDate);
			}
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
		return dto;
	}
	// 공지사항 수정하기(nNo, nSubject, nContent, nFilename, nPw)
	public int modify(int nNo, String nSubject, String nContent, String nFilename, String nPw) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE NBOARD " + 
						" SET NSUBJECT = ?, " + 
						" NCONTENT = ?, " + 
						" NFILENAME = ?, " + 
						" NPW = ?, " + 
						" NDATE = SYSDATE " + 
					" WHERE NNO = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nSubject);
			pstmt.setString(2, nContent);
			pstmt.setString(3, nFilename);
			pstmt.setString(4, nPw);
			pstmt.setInt(5, nNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "공지사항 글수정 성공" : "공지사항 글수정 실패");
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
	public NboardDto modifyView(int nNo) {
		NboardDto dto = null;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 	   		 rs = null;
		String sql = "";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId 		 = rs.getString("aId");
				String nSubject  = rs.getString("nSubject");
				String nContent  = rs.getString("nContent");
				String nFilename = rs.getString("nFilename");
				int nReadcount   = rs.getInt("nReadcount");
				String nPw 	 	 = rs.getString("nPw");
				Date nDate 		 = rs.getDate("nDate");
				dto = new NboardDto(nNo, aId, nSubject, nContent, nFilename, nReadcount, nPw, nDate);
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
	// 공지사항 글삭제하기
	public int delete(int nNo) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		String sql = " DELETE FROM NBOARD WHERE NNO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "공지사항 글삭제성공" : "공지사항 글삭제실패");
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
}