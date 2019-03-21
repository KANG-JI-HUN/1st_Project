package com.tj.ex.dto;

public class CustomerDto {
	private String cId;
	private String cPw;
	private String cName;
	private String cTel;
	private String cEmail;
	private String cGender;
	private String cAddr;
	
	public CustomerDto() {}
	public CustomerDto(String cId, String cPw, String cName, String cTel, String cEmail, String cGender, String cAddr) {
		this.cId = cId;
		this.cPw = cPw;
		this.cName = cName;
		this.cTel = cTel;
		this.cEmail = cEmail;
		this.cGender = cGender;
		this.cAddr = cAddr;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcPw() {
		return cPw;
	}
	public void setcPw(String cPw) {
		this.cPw = cPw;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcTel() {
		return cTel;
	}
	public void setcTel(String cTel) {
		this.cTel = cTel;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public String getcGender() {
		return cGender;
	}
	public void setcGender(String cGender) {
		this.cGender = cGender;
	}
	public String getcAddr() {
		return cAddr;
	}
	public void setcAddr(String cAddr) {
		this.cAddr = cAddr;
	}
	
	@Override
	public String toString() {
		return "CustomerDto [cId=" + cId + ", cPw=" + cPw + ", cName=" + cName + ", cTel=" + cTel + ", cEmail=" + cEmail
				+ ", cGender=" + cGender + ", cAddr=" + cAddr + "]";
	}
}
