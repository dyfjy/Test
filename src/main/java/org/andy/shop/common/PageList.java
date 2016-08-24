package org.andy.shop.common;

import java.util.List;
/**
 * 分页
 *@Title:  
 *@Description:  
 *@Author:cdi  
 *@Since:2016年8月24日  
 *@Version:1.1.0
 */
public class PageList<T> {
 
    private int page;//当前页
    private int rows;//每行显示数
    private int total;	//总结果数
    private List<T> list;	//当前页内容
 
    public PageList() {
        super();
    }
 
    public PageList(int page, int rows, int total, List<T> list) {
        super();
        this.page = page;
        this.rows = rows;
        this.total = total;
        this.list = list;
    }
 
    public int getPage() {
        return page;
    }
 
    public void setPage(int page) {
        this.page = page;
    }
 
    public int getRows() {
        return rows;
    }
 
    public void setRows(int rows) {
        this.rows = rows;
    }
 
    public List<T> getList() {
        return list;
    }
 
    public void setList(List<T> list) {
        this.list = list;
    }
 
    public int size(){
        return null==list?0:list.size();
    }
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }
 
}