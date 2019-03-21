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

import com.tj.ex.dto.QboardDto;

public class QboardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public QboardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 질문글 목록
	public ArrayList<QboardDto> list(int startRow, int endRow) {
		ArrayList<QboardDto> dtos = new ArrayList<QboardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = " SELECT * FROM "
						+ " (SELECT ROWNUM RN, A.* FROM "
						+ " (SELECT * FROM QBOARD ORDER BY QGROUP DESC, QSTEP) A) "
					+ " WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int qNo 		 = rs.getInt("qNo");
				 String cId 	 = rs.getString("cId");
				 String aId 	 = rs.getString("aId");
				 String qSubject = rs.getString("qSubject");
				 String qContent = rs.getString("qContent");
				 int qReadcount  = rs.getInt("qReadcount");
				 String qPw 	 = rs.getString("qPw");
				 int qGroup 	 = rs.getInt("qGroup");
				 int qStep 		 = rs.getInt("qStep");
				 int qIndent	 = rs.getInt("qIndent");
				 Date qDate		 = rs.getDate("qDate");
				 dtos.add(new QboardDto(qNo, cId, aId, qSubject, qContent, qReadcount,
						 qPw, qGroup, qStep, qIndent, qDate));
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
	// 질문글 갯수
	public int getQboardTotCnt() {
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
	// 질문글 쓰기(cId, qSubject, qContent, qPw)
	public int write(String cId, String qSubject, String qContent, String qPw,
						int qGroup, int qStep, int qIndent) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO QBOARD(QNO, CID, QSUBJECT, QCONTENT, QPW, "
										+ " QGROUP, QSTEP, QINDENT) "
						+ " VALUES(QBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, "
								+ " QBOARD_SEQ.CURRVAL, 0, 0)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setString(2, qSubject);
			pstmt.setString(3, qContent);
			pstmt.setString(4, qPw);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "질문 글쓰기 성공":"질문 글쓰기 실패");
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
	// qReadcount 하나 올리기
	private void hitUp(int qNo) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE QBOARD SET QREADCOUNT = QREADCOUNT + 1 WHERE QNO = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	// 글 상세보기(qNo로 글 dto보기)
	public QboardDto contentView(int qNo) {
		hitUp(qNo);
		QboardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = " SELECT Q.*, CNAME FROM QBOARD Q, CUSTOMER C"
					+ " WHERE Q.CID=C.CID AND QNO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String cId 	 = rs.getString("cId");
				 String aId 	 = rs.getString("aId");
				 String qSubject = rs.getString("qSubject");
				 String qContent = rs.getString("qContent");
				 int qReadcount  = rs.getInt("qReadcount");
				 String qPw 	 = rs.getString("qPw");
				 int qGroup 	 = rs.getInt("qGroup");
				 int qStep 		 = rs.getInt("qStep");
				 int qIndent	 = rs.getInt("qIndent");
				 Date qDate		 = rs.getDate("qDate");
				 dto = new QboardDto(qNo, cId, aId, qSubject, qContent, qReadcount, qPw,
						 qGroup, qStep, qIndent, qDate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	// 답변글 view + 수정 view(qNo로 dto리턴)
	public QboardDto modifyView_replyView(int qNo) {
		QboardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = " SELECT Q.*, CNAME FROM QBOARD Q, CUSTOMER C"
					+ " WHERE Q.CID=C.CID AND QNO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String cId 	 = rs.getString("cId");
				 String aId 	 = rs.getString("aId");
				 String qSubject = rs.getString("qSubject");
				 String qContent = rs.getString("qContent");
				 int qReadcount  = rs.getInt("qReadcount");
				 String qPw 	 = rs.getString("qPw");
				 int qGroup 	 = rs.getInt("qGroup");
				 int qStep 		 = rs.getInt("qStep");
				 int qIndent	 = rs.getInt("qIndent");
				 Date qDate		 = rs.getDate("qDate");
				 dto = new QboardDto(qNo, cId, aId, qSubject, qContent, qReadcount, qPw,
						 qGroup, qStep, qIndent, qDate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	// 질문글 수정하기(QNO, QSUBJECT, QCONTENT, QPW)
	public int modify(int qNo, String qSubject, String qContent, String qPw) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE QBOARD "
						+ " SET QSUBJECT = ?, "
						+ " QCONTENT = ?, "
						+ " QPW = ?, "
						+ " QDATE = SYSDATE "
					+ " WHERE QNO=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qSubject);
			pstmt.setString(2, qContent);
			pstmt.setString(3, qPw);
			pstmt.setInt(4, qNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "질문 글수정 성공":"질문 글수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 질문글 삭제하기
	public int delete(int qNo) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QBOARD WHERE QNO=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNo);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "질문 글삭제 성공":"질문 글삭제 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// step a
		private void preReplyStepA(int qGroup, int qStep) {
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = " UPDATE QBOARD SET QSTEP = QSTEP + 1 " + 
					"    WHERE QGROUP = ? AND QGROUP > ? ";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, qGroup);
				pstmt.setInt(2, qStep);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
			}
		}
	}
	// 답변글 쓰기(aId, qSubject, qContent, qPw)
	public int reply(String aId, String qSubject, String qContent, String qPw,
			int qGroup, int qStep, int qIndent) {
		preReplyStepA(qGroup, qStep); // 답변글 저장전 step A 먼저 실행
		int result = FAIL;
		Connection conn 	 	= null;
		PreparedStatement pstmt = null;
			String sql = " INSERT INTO QBOARD(QNO, AID, QSUBJECT, QCONTENT, QPW, "
											+ " QGROUP, QSTEP, QINDENT) "
							+ " VALUES(QBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, "
										+ " ?, ?, ?) ";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, aId);
				pstmt.setString(2, qSubject);
				pstmt.setString(3, qContent);
				pstmt.setString(4, qPw);
				pstmt.setInt(5, qGroup);
				pstmt.setInt(6, qStep+1);
				pstmt.setInt(7, qIndent+1);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS? "질문답변 글쓰기 성공":"질문답변 글쓰기 실패");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
			}
		}
		return result;
	}
}