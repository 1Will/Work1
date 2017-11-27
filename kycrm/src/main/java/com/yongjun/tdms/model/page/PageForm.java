/*    */ package com.yongjun.tdms.model.page;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class PageForm
/*    */ {
/*  6 */   private int CurrentPage = 1;
/*  7 */   private int PrePage = 0;
/*  8 */   private int NextPage = 0;
/*    */   private List list;
/* 11 */   private int PageSize = 0;
/* 12 */   private int TotalPage = 0;
/* 13 */   private int TotalSize = 0;
/*    */   private String sql;
/*    */ 
/*    */   public int getCurrentPage()
/*    */   {
/* 16 */     return this.CurrentPage;
/*    */   }
/*    */ 
/*    */   public void setCurrentPage(int currentPage)
/*    */   {
/* 22 */     if (currentPage < 1) {
/* 23 */       currentPage = 1;
/*    */     }
/* 25 */     if (currentPage > this.TotalPage) {
/* 26 */       currentPage = this.TotalPage;
/*    */     }
/* 28 */     this.CurrentPage = currentPage;
/*    */   }
/*    */   public List getList() {
/* 31 */     return this.list;
/*    */   }
/*    */   public void setList(List list) {
/* 34 */     this.list = list;
/*    */   }
/*    */   public int getNextPage() {
/* 37 */     return this.NextPage;
/*    */   }
/*    */   public void setNextPage(int nextPage) {
/* 40 */     this.NextPage = nextPage;
/*    */   }
/*    */   public int getPrePage() {
/* 43 */     return this.PrePage;
/*    */   }
/*    */   public void setPrePage(int prePage) {
/* 46 */     this.PrePage = prePage;
/*    */   }
/*    */   public int getPageSize() {
/* 49 */     return this.PageSize;
/*    */   }
/*    */   public void setPageSize(int pageSize) {
/* 52 */     this.PageSize = pageSize;
/*    */   }
/*    */   public String getSql() {
/* 55 */     return this.sql;
/*    */   }
/*    */   public void setSql(String sql) {
/* 58 */     this.sql = sql;
/*    */   }
/*    */   public int getTotalPage() {
/* 61 */     return this.TotalPage;
/*    */   }
/*    */   public void setTotalPage(int totalPage) {
/* 64 */     this.TotalPage = totalPage;
/*    */   }
/*    */   public int getTotalSize() {
/* 67 */     return this.TotalSize;
/*    */   }
/*    */   public void setTotalSize(int totalSize) {
/* 70 */     this.TotalSize = totalSize;
/*    */   }
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.page.PageForm
 * JD-Core Version:    0.6.2
 */