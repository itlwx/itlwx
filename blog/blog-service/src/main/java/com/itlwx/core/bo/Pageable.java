package com.itlwx.core.bo;

import java.io.Serializable;

public class Pageable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 当前显示页码
	 */
	private int currentPage=1;	
	/**
	 * 每页显示条数
	 */
	private int pageOfSize=12;
	
	public Pageable(){
		
	}
	
	public Pageable(Integer currentPage, Integer pageOfSize){
		this.setCurrentPage(currentPage);
		this.setPageOfSize(pageOfSize);
	}
	
	public int getPageOfSize() {
		return pageOfSize;
	}

	public void setPageOfSize(Integer pageOfSize) {
		if(pageOfSize != null && pageOfSize > 0){
			if(pageOfSize > 5000){
				//单页最多读取5000条数据
				this.pageOfSize = 5000;
			}else{
				this.pageOfSize = pageOfSize;
			}
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		if(currentPage != null && currentPage > 0){
			this.currentPage = currentPage;
		}
	}
	
	public int getLimitStart(){
		return (getCurrentPage() - 1) * getPageOfSize();
	}
	public int getLimitEnd(){
		return getPageOfSize();
	}
	
}
