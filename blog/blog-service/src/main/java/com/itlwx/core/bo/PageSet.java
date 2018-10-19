package com.itlwx.core.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 数据分页处理类
 * @author dawn
 *
 * @param <T> 数据项
 */
public class PageSet<T> implements Serializable {
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
	private int pageOfSize=10;	
	/**
	 * 记录总数
	 */
	private int recordTotal = 0;
	/**
	 * 数据项
	 */
	private List<T> items;

	/**
	 * 总页数
	 */
	private int countPage;
	
	/**
	 * 分页构造方法 currentPage和pageOfSize必需不为NULL才会计算字段值
	 * @param currentPage 当前页码
	 * @param pageOfSize 每页记录数
	 * @param recordTotal 总记录数
	 */
	public PageSet(Integer currentPage, Integer pageOfSize, Integer recordTotal){
		if(recordTotal != null){
			this.recordTotal=recordTotal;
		}
		if(currentPage != null){
			this.currentPage=currentPage;
		}
		if(pageOfSize != null){
			this.pageOfSize=pageOfSize;
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageOfSize() {
		return pageOfSize;
	}
	public void setPageOfSize(int pageOfSize) {
		this.pageOfSize = pageOfSize;
	}
	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}
	public int getRecordTotal() {
		return recordTotal;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public List<T> getItems() {
		return items;
	}

	public int getCountPage() {
		return (this.recordTotal-1)/pageOfSize+1;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}
}
