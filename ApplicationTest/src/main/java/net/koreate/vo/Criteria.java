package net.koreate.vo;

public class Criteria {
	int page;
	
	int perPageNum;

	public Criteria() {
		this.page=1;
		this.perPageNum=5;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <=0) {
			this.page=1;
			return;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum <=0 || perPageNum > 20) {
			this.perPageNum = 5;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getCurrentPage() {
		int cnt = (this.page -1)*perPageNum;
		return cnt;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}

}
