package net.koreate.vo;

import java.util.Date;

public class CommentVo {
  private int rno;
  private int cno;
  private int bno;
  private String commentText;
  private String commentAuth;

  private Date regdate;
  private Date updatedate;

  private int uno;
  public Integer getCno() {
    return cno;
  }

  public void setCno(int cno) {
    this.cno = cno;
  }

  public int getBno() {
    return bno;
  }

  public void setBno(int bno) {
    this.bno = bno;
  }

  public String getCommentText() {
	return commentText;
  }

  public void setCommentText(String commentText) {
	this.commentText = commentText;
  }

  public String getCommentAuth() {
	return commentAuth;
  }

	public void setCommentAuth(String commentAuth) {
		this.commentAuth = commentAuth;
	}
	
	public Date getRegdate() {
    return regdate;
  }

  public void setRegdate(Date regdate) {
    this.regdate = regdate;
  }

  public Date getUpdatedate() {
    return updatedate;
  }

  public void setUpdatedate(Date updatedate) {
    this.updatedate = updatedate;
  }
  
  

  public int getRno() {
	return rno;
  }
	
  public void setRno(int rno) {
	this.rno = rno;
  }


	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	@Override
	public String toString() {
		return "CommentVo [rno=" + rno + ", cno=" + cno + ", bno=" + bno + ", commentText=" + commentText
				+ ", commentAuth=" + commentAuth + ", regdate=" + regdate + ", updatedate=" + updatedate + ", uno="
				+ uno + "]";
	}
	
	

}