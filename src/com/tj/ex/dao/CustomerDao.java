package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.CustomerDto;

public class CustomerDao {
	public static final int MEMBER_EXISTENT = 0; // 존재하는 ID
	public static final int MEMBER_NONEXISTENT = 1; // 사용가능 ID
	public static final int FAIL = 0; // 실패(회원가입, 정보수정)
	public static final int SUCCESS = 1; // 성공(회원가입, 정보수정)
	public static final int LOGIN_FAIL_ID = -1; // 로그인오류(id)
	public static final int LOGIN_FAIL_PW = 0; // 로그인오류(pw)
	public static final int LOGIN_SUCCESS = 1; // 로그인성공
	private DataSource ds;
	public CustomerDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// id confirm
	public int cIdConfirm(String cId) {
		int result = MEMBER_EXISTENT;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = " SELECT * FROM CUSTOMER WHERE CID=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			rs = pstmt.executeQuery();
			if(rs.next()) {				// 한 줄이라도 있을 경우
				result = MEMBER_EXISTENT;
			} else {					// 한 줄이라도 없을 경우
				result = MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn	!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// join
	public int joinCustomer(CustomerDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO CUSTOMER(CID, CPW, CNAME, CTEL, CEMAIL, CGENDER, CADDR) "
						+ " VALUES(?, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getcId());
			pstmt.setString(2, dto.getcPw());
			pstmt.setString(3, dto.getcName());
			pstmt.setString(4, dto.getcTel());
			pstmt.setString(5, dto.getcEmail());
			pstmt.setString(6, dto.getcGender());
			pstmt.setString(7, dto.getcAddr());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "회원가입성공" : "회원가입실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// login
	public int loginCheck(String cId, String cPw) {
		int result = LOGIN_FAIL_ID;
		Connection 		   	conn = null;
		PreparedStatement pstmt = null;
		ResultSet			 rs = null;
		String sql = " SELECT * FROM CUSTOMER WHERE CID=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbcPw = rs.getString("cPw");
				if(dbcPw.equals(cPw)) {
					result = LOGIN_SUCCESS; // 로그인 성공
				} else {
					result = LOGIN_FAIL_PW; // pw 오류
				}
			} else {
				result = LOGIN_FAIL_ID; // id 오류
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	
		return result;
	}
	// cID로 dto 가져오기
	public CustomerDto getCustomer(String cId) {
		CustomerDto customer = null;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = " SELECT * FROM CUSTOMER WHERE CID=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			rs = pstmt.executeQuery();
			if(rs.next()) {				// 한 줄이라도 있을 경우
				customer = new CustomerDto();
				customer.setcId(rs.getString("cId"));
				customer.setcPw(rs.getString("cPw"));
				customer.setcName(rs.getString("cName"));
				customer.setcTel(rs.getString("cTel"));
				customer.setcEmail(rs.getString("cEmail"));
				customer.setcGender(rs.getString("cGender"));
				customer.setcAddr(rs.getString("cAddr"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn	!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return customer;
	}
	// modify
	public int ModifyCustomer(CustomerDto dto) {
		int result = FAIL;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		String sql = " UPDATE CUSTOMER "
						+ " SET CPW = ?, "
						+ " CNAME = ?, "
						+ " CTEL = ?, "
						+ " CEMAIL = ?, "
						+ " CGENDER = ?, "
						+ " CADDR = ? "
					+ " WHERE CID = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getcPw());
			pstmt.setString(2, dto.getcName());
			pstmt.setString(3, dto.getcTel());
			pstmt.setString(4, dto.getcEmail());
			pstmt.setString(5, dto.getcGender());
			pstmt.setString(6, dto.getcAddr());
			pstmt.setString(7, dto.getcId());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "회원수정성공" : "회원수정실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 가입한 회원수
	public int getCustomerTotCnt() {
		int result = 0;
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = " SELECT COUNT(*) FROM CUSTOMER ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {				// 한 줄이라도 있을 경우
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn	!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// startRow~endRow까지 list
	public ArrayList<CustomerDto> list(int startRow, int endRow) {
		ArrayList<CustomerDto> dtos = new ArrayList<CustomerDto>();
		Connection 		   conn = null;
		PreparedStatement pstmt = null;
		ResultSet 			 rs = null;
		String sql = "SELECT * FROM "
						+ " (SELECT ROWNUM RN, A.* FROM(SELECT * FROM CUSTOMER ORDER BY CID) A) "
					+ " WHERE RN BETWEEN ? AND ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {				// 몇 줄까지 있는지 알지 못하므로
				 String cId = rs.getString("cId");
				 String cPw = rs.getString("cPw");
				 String cName = rs.getString("cName");
				 String cTel = rs.getString("cTEl");
				 String cEmail = rs.getString("cEmail");
				 String cGender = rs.getString("cGender");
				 String cAddr = rs.getString("cAddr");
				 dtos.add(new CustomerDto(cId, cPw, cName, cTel, cEmail, cGender, cAddr));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn	!=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
}