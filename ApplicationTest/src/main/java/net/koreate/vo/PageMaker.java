package net.koreate.vo;

public class PageMaker {
	int totalCount;
	int startPage;
	int endPage;
	boolean prev;
	boolean next;
	
	int displayPageNum = 5;
	
	Criteria cri;

	public void calData() {
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage = (endPage - displayPageNum)+1;

		int temp = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		if(endPage > temp) {
			endPage = temp;
		}
		
		prev = startPage == 1 ?false:true;
		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		
	}
	
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calData();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	
}
