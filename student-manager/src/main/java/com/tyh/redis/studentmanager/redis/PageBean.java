package com.tyh.redis.studentmanager.redis;

import java.util.List;

public class PageBean<T> {
	private int pageNum;  //当前页
	private int pageSize; //每页记录数量
	private int totalRecord; //总记录数量
	private int totalPage;   //总页数
	
	private int startIndex;  
	private List<T> list;
	private int start; //在页面上显示的第一页
	private int end;  //在页面上显示的第二页
	
	public PageBean(int pageNum,int pageSize,int totalRecord){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		
		//计算总页数
		if(totalRecord%pageSize==0){
			this.totalPage = totalRecord/pageSize;
		}else{
			this.totalPage = totalRecord/pageSize+1;
		}
		
		this.startIndex = (pageNum-1)*pageSize;
		this.start = 1;
		this.end = 5;
		if(totalPage<=5){
			this.end = this.totalPage;
		}else{
			this.start = pageNum-1;
			this.end = pageNum+1;
			if(start<=0){
				this.start = 1;
				this.end = 3;
			}
			if(end>this.totalPage){
				this.end = this.totalPage;
				this.start = end-3;
			}
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
}
