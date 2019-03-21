package com.tj.ex.dto;

import java.sql.Date;

public class FreeboardDto {
	private int fNo;
	private String cId;
	private String fSubject;
	private String fContent;
	private String fFilename;
	private int fReadcount;
	private String fPw;
	private int fGroup;
	private int fStep;
	private int fIndent;
	private String fIp;
	private Date fDate;
	
	public FreeboardDto() {}
	public FreeboardDto(int fNo, String cId, String fSubject, String fContent, String fFilename, int fReadcount,
			String fPw, int fGroup, int fStep, int fIndent, String fIp, Date fDate) {
		this.fNo = fNo;
		this.cId = cId;
		this.fSubject = fSubject;
		this.fContent = fContent;
		this.fFilename = fFilename;
		this.fReadcount = fReadcount;
		this.fPw = fPw;
		this.fGroup = fGroup;
		this.fStep = fStep;
		this.fIndent = fIndent;
		this.fIp = fIp;
		this.fDate = fDate;
	}
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getfSubject() {
		return fSubject;
	}
	public void setfSubject(String fSubject) {
		this.fSubject = fSubject;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}
	public String getfFilename() {
		return fFilename;
	}
	public void setfFilename(String fFilename) {
		this.fFilename = fFilename;
	}
	public int getfReadcount() {
		return fReadcount;
	}
	public void setfReadcount(int fReadcount) {
		this.fReadcount = fReadcount;
	}
	public String getfPw() {
		return fPw;
	}
	public void setfPw(String fPw) {
		this.fPw = fPw;
	}
	public int getfGroup() {
		return fGroup;
	}
	public void setfGroup(int fGroup) {
		this.fGroup = fGroup;
	}
	public int getfStep() {
		return fStep;
	}
	public void setfStep(int fStep) {
		this.fStep = fStep;
	}
	public int getfIndent() {
		return fIndent;
	}
	public void setfIndent(int fIndent) {
		this.fIndent = fIndent;
	}
	public String getfIp() {
		return fIp;
	}
	public void setfIp(String fIp) {
		this.fIp = fIp;
	}
	public Date getfDate() {
		return fDate;
	}
	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}
	
	@Override
	public String toString() {
		return "FreeboardDto [fNo=" + fNo + ", cId=" + cId + ", fSubject=" + fSubject + ", fContent=" + fContent
				+ ", fFilename=" + fFilename + ", fReadcount=" + fReadcount + ", fPw=" + fPw + ", fGroup=" + fGroup
				+ ", fStep=" + fStep + ", fIndent=" + fIndent + ", fIp=" + fIp + ", fDate=" + fDate + "]";
	}
}
