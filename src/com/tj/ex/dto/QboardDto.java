package com.tj.ex.dto;

import java.sql.Date;

public class QboardDto {
	private int qNo;
	private String cId;
	private String aId;
	private String qSubject;
	private String qContent;
	private int qReadcount;
	private String qPw;
	private int qGroup;
	private int qStep;
	private int qIndent;
	private Date qDate;
	
	public QboardDto() {}
	public QboardDto(int qNo, String cId, String aId, String qSubject, String qContent, int qReadcount, String qPw,
			int qGroup, int qStep, int qIndent, Date qDate) {
		this.qNo = qNo;
		this.cId = cId;
		this.aId = aId;
		this.qSubject = qSubject;
		this.qContent = qContent;
		this.qReadcount = qReadcount;
		this.qPw = qPw;
		this.qGroup = qGroup;
		this.qStep = qStep;
		this.qIndent = qIndent;
		this.qDate = qDate;
	}
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getqSubject() {
		return qSubject;
	}
	public void setqSubject(String qSubject) {
		this.qSubject = qSubject;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public int getqReadcount() {
		return qReadcount;
	}
	public void setqReadcount(int qReadcount) {
		this.qReadcount = qReadcount;
	}
	public String getqPw() {
		return qPw;
	}
	public void setqPw(String qPw) {
		this.qPw = qPw;
	}
	public int getqGroup() {
		return qGroup;
	}
	public void setqGroup(int qGroup) {
		this.qGroup = qGroup;
	}
	public int getqStep() {
		return qStep;
	}
	public void setqStep(int qStep) {
		this.qStep = qStep;
	}
	public int getqIndent() {
		return qIndent;
	}
	public void setqIndent(int qIndent) {
		this.qIndent = qIndent;
	}
	public Date getqDate() {
		return qDate;
	}
	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}
	
	@Override
	public String toString() {
		return "QboardDto [qNo=" + qNo + ", cId=" + cId + ", aId=" + aId + ", qSubject=" + qSubject + ", qContent="
				+ qContent + ", qReadcount=" + qReadcount + ", qPw=" + qPw + ", qGroup=" + qGroup + ", qStep=" + qStep
				+ ", qIndent=" + qIndent + ", qDate=" + qDate + "]";
	}
}
