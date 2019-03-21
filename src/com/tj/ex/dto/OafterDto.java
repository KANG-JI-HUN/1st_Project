package com.tj.ex.dto;

import java.sql.Date;

public class OafterDto {
	private int oNo;
	private String cId;
	private int iNo;
	private String oSubject;
	private String oContent;
	private String oFilename;
	private int oReadcount;
	private String oPw;
	private Date oDate;
	
	public OafterDto() {}
	public OafterDto(int oNo, String cId, int iNo, String oSubject, String oContent, String oFilename, int oReadcount,
			String oPw, Date oDate) {
		super();
		this.oNo = oNo;
		this.cId = cId;
		this.iNo = iNo;
		this.oSubject = oSubject;
		this.oContent = oContent;
		this.oFilename = oFilename;
		this.oReadcount = oReadcount;
		this.oPw = oPw;
		this.oDate = oDate;
	}

	public int getoNo() {
		return oNo;
	}

	public void setoNo(int oNo) {
		this.oNo = oNo;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public int getiNo() {
		return iNo;
	}

	public void setiNo(int iNo) {
		this.iNo = iNo;
	}

	public String getoSubject() {
		return oSubject;
	}

	public void setoSubject(String oSubject) {
		this.oSubject = oSubject;
	}

	public String getoContent() {
		return oContent;
	}

	public void setoContent(String oContent) {
		this.oContent = oContent;
	}

	public String getoFilename() {
		return oFilename;
	}

	public void setoFilename(String oFilename) {
		this.oFilename = oFilename;
	}

	public int getoReadcount() {
		return oReadcount;
	}

	public void setoReadcount(int oReadcount) {
		this.oReadcount = oReadcount;
	}

	public String getoPw() {
		return oPw;
	}

	public void setoPw(String oPw) {
		this.oPw = oPw;
	}

	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	@Override
	public String toString() {
		return "OafterDto [oNo=" + oNo + ", cId=" + cId + ", iNo=" + iNo + ", oSubject=" + oSubject + ", oContent="
				+ oContent + ", oFilename=" + oFilename + ", oReadcount=" + oReadcount + ", oPw=" + oPw + ", oDate="
				+ oDate + "]";
	}
}
