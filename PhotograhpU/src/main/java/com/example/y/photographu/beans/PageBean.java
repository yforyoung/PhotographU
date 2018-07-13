package com.example.y.photographu.beans;

import java.util.List;

public class PageBean<T> {
    private int pageSize;
    private int currentPage;
    private int totalRows;
    private int totalPages;
    private int startIndex;
    private int start;
    private int end;
    private List<T> data;

    public PageBean() {
    }

    public PageBean(int pageSize, int currentPage, int totalRows, int totalPages, int startIndex, int start, int end, List<T> data) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRows = totalRows;
        this.totalPages = totalPages;
        this.startIndex = startIndex;
        this.start = start;
        this.end = end;
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
