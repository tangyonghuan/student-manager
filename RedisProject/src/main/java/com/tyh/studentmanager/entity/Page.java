package com.tyh.studentmanager.entity;

import com.tyh.studentmanager.redis.RedisOpreate;

import java.util.List;

/**
 * Created by DWade on 2017/8/7.
 */
public class Page {
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private Long totalRecord;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 开始索引
     */
    private int start;
    /**
     * 结束索引
     */
    private int end;


    public Page(int pageNum, int pageSize,int minScore,int maxScore){

        this.pageSize = pageSize;
        totalRecord = RedisOpreate.getStudentCount(minScore,maxScore);
        if(totalRecord%pageSize == 0){
            totalPage = (int)(totalRecord/pageSize);
        }else{
            totalPage = (int)(totalRecord/pageSize)+1;
        }
        /**
         * 将无效页码转为有效页码
         */
        if(pageNum<=0) pageNum = 1;
        if(pageNum>totalPage)
            pageNum = totalPage;
        start = (pageNum-1)*pageSize;

        if(pageNum<totalPage){
            end = pageNum*pageSize-1;
        }else{
            end = (int)(totalRecord -1);
        }
        this.pageNum = pageNum;
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

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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
