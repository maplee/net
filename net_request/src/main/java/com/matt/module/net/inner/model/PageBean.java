package com.matt.module.net.inner.model;

import java.io.Serializable;

/**
 * Author:Created by matt on 2020/3/2.
 * Email:jiagfone@163.com
 */

public class PageBean implements Serializable {

    /**
     * pageSize : 20
     * currentResult : 0
     * totalPage : 2
     * pageNo : 1
     * totalCount : 25
     * list : null
     * firstResult : 0
     * firstPage : true
     * lastPage : false
     * nextPage : 2
     * prePage : 1
     */

    private int pageSize;
    private int currentResult;
    private int totalPage;
    private int pageNo;
    private int totalCount;
    private int firstResult;
    private boolean firstPage;
    private boolean lastPage;
    private int nextPage;
    private int prePage;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentResult() {
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public boolean isLoadMore(int page){
        return page < totalPage;
    }
}
