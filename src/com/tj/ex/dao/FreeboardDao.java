package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.FreeboardDto;

public class FreeboardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public DataSource ds;
	public FreeboardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 자유게시판 글목록(startRow부터 endRow까지)
	public ArrayList<FreeboardDto> list(int startRow, int endRow) {
		ArrayList<FreeboardDto> dtos = new ArrayList<FreeboardDto>();
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = " SELECT * FROM "
						+ " (SELECT ROWNUM RN, A.* FROM "
						+ " (SELECT * FROM FREEBOARD ORDER BY FGROUP DESC, FSTEP) A) "
					+ " WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			 int fNo 		  = rs.getInt("fNo");
			 String cId 	  = rs.getString("cId");
			 String fSubject  = rs.getString("fSubject");
			 String fContent  = rs.getString("fContent");
			 String fFilename = rs.getString("fFilename");
			 int fReadcount   = rs.getInt("fReadcount");
			 String fPw  	  = rs.getString("fPw");
			 int fGroup 	  = rs.getInt("fGroup");
			 int fStep 		  = rs.getInt("fStep");
			 int fIndent 	  = rs.getInt("fIndent");
			 String fIp 	  = rs.getString("fIp");
			 Date fDate 	  = rs.getDate("fDate");
			 dtos.add(new FreeboardDto(fNo, cId, fSubject, fContent, fFilename, fReadcount, fPw, fGroup, fStep, fIndent, fIp, fDate));
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
	// 자유게시판 글갯수
	public int getFreeboardTotCnt() {
		int cnt = 0;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = " SELECT COUNT(*) FROM FREEBOARD ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs 	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return cnt;
	}
	// 자유게시판 글쓰기(cId, fSubject, fContent, fFilename, fPw, fIp)
	public int write(String cId, String fSubject, String fContent, String fFilename, String fPw, String fIp) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO FREEBOARD"
						+ "(FNO, CID, FSUBJECT, FCONTENT, FFILENAME, FPW, FGROUP, FSTEP, FINDENT, FIP) "
						+ " VALUES(FREEBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, "
						+ " FREEBOARD_SEQ.CURRVAL, 0, 0, ?) ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setString(2, fSubject);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fFilename);
			pstmt.setString(5, fPw);
			pstmt.setString(6, fIp);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "자유게시판 글쓰기 성공":"자유게시판 글쓰기 실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// fReadcount 하나 올리기(조회수)
	private void hitup(int fNo) {
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE FREEBOARD SET FREADCOUNT = FREADCOUNT + 1 WHERE FNO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			pstmt.executeUpdate();
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
	}
	// 글 상세보기(조회수 up + fNo로 dto리턴)
	public FreeboardDto contentView(int fNo) {
		hitup(fNo);
		FreeboardDto dto = null;
		Connection  	   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = " SELECT F.*, CNAME FROM FREEBOARD F, CUSTOMER C "
						+ " WHERE F.CID = C.CID AND FNO = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String cId 	  = rs.getString("cId");
				 String fSubject  = rs.getString("fSubject");
				 String fContent  = rs.getString("fContent");
				 String fFilename = rs.getString("fFilename");
				 int fReadcount	  = rs.getInt("fReadcount");
				 String fPw		  = rs.getString("fPw");
				 int fGroup 	  = rs.getInt("fGroup");
				 int fStep 		  = rs.getInt("fStep");
				 int fIndent 	  = rs.getInt("fIndent");
				 String fIp 	  = rs.getString("fIp");
				 Date fDate 	  = rs.getDate("fDate");
				 dto = new FreeboardDto(fNo, cId, fSubject, fContent, fFilename, fReadcount, fPw, fGroup, fStep, fIndent, fIp, fDate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;	
	}
	// 답변글 view + 수정 view(fNo로 dto리턴)
	public FreeboardDto modifyView_replyView(int fNo) {
		FreeboardDto dto = null;
		Connection		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = "";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String cId 	  = rs.getString("cId");
				 String fSubject  = rs.getString("fSubject");
				 String fContent  = rs.getString("fContent");
				 String fFilename = rs.getString("fFilename");
				 int fReadcount	  = rs.getInt("fReadcount");
				 String fPw		  = rs.getString("fPw");
				 int fGroup 	  = rs.getInt("fGroup");
				 int fStep 		  = rs.getInt("fStep");
				 int fIndent 	  = rs.getInt("fIndent");
				 String fIp 	  = rs.getString("fIp");
				 Date fDate 	  = rs.getDate("fDate");
				 dto = new FreeboardDto(fNo, cId, fSubject, fContent, fFilename, fReadcount, fPw, fGroup, fStep, fIndent, fIp, fDate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs  	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn	!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	// 글 수정하기(FNO, FSUBJECT, FCONTENT, FFILENAME, FPW, FIP, FDATE)
		public int modify(int fNo, String fSubject, String fContent, String fFilename, String fPw, String fIp) {
			int result = FAIL;
			Connection 		   conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE FREEBOARD "
							+ " SET FSUBJECT = ?,"
							+ " FCONTENT = ?,"
							+ " FFILENAME = ?,"
							+ " FPW = ?,"
							+ " FIP = ? "
							+ " FDATE = SYSDATE "
						+ " WHERE FNO= ? ";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fSubject);
				pstmt.setString(2, fContent);
				pstmt.setString(3, fFilename);
				pstmt.setString(4, fPw);
				pstmt.setString(5, fIp);
				pstmt.setInt(6, fNo);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS? "자유게시판 글수정 성공":"자유게시판 글수정 실패");
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
		// 글 삭제하기(FNO로 삭제하기)
		public int delete(int fNo) {
			int result = FAIL;
			Connection 		   conn = null;
			PreparedStatement pstmt = null;
			String sql = " DELETE FROM FREEBOARD WHERE FNO=?" ;
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, fNo);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS? "자유게시판 글삭제 성공":"자유게시판 글삭제 실패");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
		}
		// step a
		private void preReplyStepA(int fGroup, int fStep) {
			Connection 		   conn = null;
			PreparedStatement pstmt = null;
			String sql = " UPDATE FREEBOARD SET FSTEP = FSTEP + 1 "
							+ " WHERE FGROUP = ? AND FGROUP > ? ";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, fGroup);
				pstmt.setInt(2, fStep);
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(pstmt !=null) pstmt.close();
					if(conn  != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		// 답변글 쓰기(cId, fSubject, fContent, fFilename, fPw, fIp)
		public int reply(String cId, String fSubject, String fContent, String fFilename,
							String fPw, int fGroup, int fStep, int fIndent, String fIp) {
			preReplyStepA(fGroup, fStep); // 답변글 저장 전 step a 먼저 실행
			//
			int result = FAIL;
			Connection 		   conn = null;
			PreparedStatement pstmt = null;
			String sql = " INSERT INTO FREEBOARD(FNO, CID, FSUBJECT, FCONTENT, FFILENAME, FPW, FGROUP, FSTEP, FINDENT, FIP) "
							+ " VALUES(FREEBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			try {
				conn = ds.getConnection();
				pstmt =conn.prepareStatement(sql);
				pstmt.setString(1, cId);
				pstmt.setString(2, fSubject);
				pstmt.setString(3, fContent);
				pstmt.setString(4, fFilename);
				pstmt.setString(5, fPw);
				pstmt.setInt(6, fGroup);
				pstmt.setInt(7, fStep+1);
				pstmt.setInt(8, fIndent+1);
				pstmt.setString(9, fIp);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(pstmt !=null) pstmt.close();
					if(conn  !=null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
	}
}