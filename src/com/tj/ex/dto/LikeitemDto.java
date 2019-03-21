package com.tj.ex.dto;

public class LikeitemDto {
	private int lNo;
	private String cId;
	private int iNo;
	private int lCnt;
	
	public LikeitemDto() {}
	public LikeitemDto(int lNo, String cId, int iNo, int lCnt) {
		this.lNo = lNo;
		this.cId = cId;
		this.iNo = iNo;
		this.lCnt = lCnt;
	}

	public int getlNo() {
		return lNo;
	}

	public void setlNo(int lNo) {
		this.lNo = lNo;
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

	public int getlCnt() {
		return lCnt;
	}

	public void setlCnt(int lCnt) {
		this.lCnt = lCnt;
	}
	@Override
	public String toString() {
		return "LikeitemDto [lNo=" + lNo + ", cId=" + cId + ", iNo=" + iNo + ", lCnt=" + lCnt + "]";
	}
}
