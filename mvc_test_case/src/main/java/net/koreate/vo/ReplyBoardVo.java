package net.koreate.vo;

import java.util.Arrays;
import java.util.Date;

public class ReplyBoardVo {
	int bno;
	String title;
	String content;
	String writer;
	int origin;
	int depth;
	int seq;
	Date regdate;
	Date updatedate;
	int viewcnt;
	
	private String[] files;
	
	private int commentCnt;
	
	private int uno;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	
	@Override
	public String toString() {
		return "ReplyBoardVo [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", origin=" + origin + ", depth=" + depth + ", seq=" + seq + ", regdate=" + regdate + ", updatedate="
				+ updatedate + ", viewcnt=" + viewcnt + ", files=" + Arrays.toString(files) + ", commentCnt="
				+ commentCnt + ", uno=" + uno + "]";
	}
	
}

