package com.tj.ex.dto;

import java.sql.Date;

public class NboardDto {
	private int nNo;
	private String aId;
	private String nSubject;
	private String nContent;
	private String nFilename;
	private int nReadcount;
	private String nPw;
	private Date nDate;
	
	public NboardDto() {}
	public NboardDto(int nNo, String aId, String nSubject, String nContent, String nFilename, int nReadcount,
			String nPw, Date nDate) {
		this.nNo = nNo;
		this.aId = aId;
		this.nSubject = nSubject;
		this.nContent = nContent;
		this.nFilename = nFilename;
		this.nReadcount = nReadcount;
		this.nPw = nPw;
		this.nDate = nDate;
	}
	public int getnNo() {
		return nNo;
	}
	public void setnNo(int nNo) {
		this.nNo = nNo;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getnSubject() {
		return nSubject;
	}
	public void setnSubject(String nSubject) {
		this.nSubject = nSubject;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public String getnFilename() {
		return nFilename;
	}
	public void setnFilename(String nFilename) {
		this.nFilename = nFilename;
	}
	public int getnReadcount() {
		return nReadcount;
	}
	public void setnReadcount(int nReadcount) {
		this.nReadcount = nReadcount;
	}
	public String getnPw() {
		return nPw;
	}
	public void setnPw(String nPw) {
		this.nPw = nPw;
	}
	public Date getnDate() {
		return nDate;
	}
	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}
	
	@Override
	public String toString() {
		return "NboardDto [nNo=" + nNo + ", aId=" + aId + ", nSubject=" + nSubject + ", nContent=" + nContent
				+ ", nFilename=" + nFilename + ", nReadcount=" + nReadcount + ", nPw=" + nPw + ", nDate=" + nDate + "]";
	}
}
