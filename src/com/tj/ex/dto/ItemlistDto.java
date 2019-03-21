package com.tj.ex.dto;

public class ItemlistDto {
	private int iNo;
	private String aId;
	private String iName;
	private String iSubject;
	private String iInfo;
	private int iAmount;
	private String iFilename;
	private int iReadcount;
	
	public ItemlistDto() {}
	public ItemlistDto(int iNo, String aId, String iName, String iSubject, String iInfo, int iAmount, String iFilename,
			int iReadcount) {
		this.iNo = iNo;
		this.aId = aId;
		this.iName = iName;
		this.iSubject = iSubject;
		this.iInfo = iInfo;
		this.iAmount = iAmount;
		this.iFilename = iFilename;
		this.iReadcount = iReadcount;
	}

	public int getiNo() {
		return iNo;
	}

	public void setiNo(int iNo) {
		this.iNo = iNo;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getiSubject() {
		return iSubject;
	}

	public void setiSubject(String iSubject) {
		this.iSubject = iSubject;
	}

	public String getiInfo() {
		return iInfo;
	}

	public void setiInfo(String iInfo) {
		this.iInfo = iInfo;
	}

	public int getiAmount() {
		return iAmount;
	}

	public void setiAmount(int iAmount) {
		this.iAmount = iAmount;
	}

	public String getiFilename() {
		return iFilename;
	}

	public void setiFilename(String iFilename) {
		this.iFilename = iFilename;
	}

	public int getiReadcount() {
		return iReadcount;
	}

	public void setiReadcount(int iReadcount) {
		this.iReadcount = iReadcount;
	}
	@Override
	public String toString() {
		return "ItemlistDto [iNo=" + iNo + ", aId=" + aId + ", iName=" + iName + ", iSubject=" + iSubject + ", iInfo="
				+ iInfo + ", iAmount=" + iAmount + ", iFilename=" + iFilename + ", iReadcount=" + iReadcount + "]";
	}
}

