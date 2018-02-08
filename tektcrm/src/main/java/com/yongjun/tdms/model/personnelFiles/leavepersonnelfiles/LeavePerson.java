/*     */ package com.yongjun.tdms.model.personnelFiles.leavepersonnelfiles;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class LeavePerson extends BaseInfoEntity
/*     */ {
/*     */   private static final long serialVersionUID = -7292422905845042981L;
/*     */   private String code;
/*     */   private String name;
/*     */   private String fileNo;
/*     */   private Institution inst;
/*     */   private Department dept;
/*     */   private Duty duty;
/*     */   private String mobile;
/*     */   private String telphone;
/*     */   private CodeValue status;
/*     */   private Date leaveDate;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  79 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  87 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/*  96 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Department getDept()
/*     */   {
/* 104 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(Department dept)
/*     */   {
/* 113 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public Duty getDuty()
/*     */   {
/* 121 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(Duty duty)
/*     */   {
/* 130 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public String getFileNo()
/*     */   {
/* 138 */     return this.fileNo;
/*     */   }
/*     */ 
/*     */   public void setFileNo(String fileNo)
/*     */   {
/* 147 */     this.fileNo = fileNo;
/*     */   }
/*     */ 
/*     */   public Institution getInst()
/*     */   {
/* 155 */     return this.inst;
/*     */   }
/*     */ 
/*     */   public void setInst(Institution inst)
/*     */   {
/* 164 */     this.inst = inst;
/*     */   }
/*     */ 
/*     */   public String getMobile()
/*     */   {
/* 172 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile)
/*     */   {
/* 181 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 189 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 198 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public CodeValue getStatus()
/*     */   {
/* 206 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(CodeValue status)
/*     */   {
/* 215 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getTelphone()
/*     */   {
/* 223 */     return this.telphone;
/*     */   }
/*     */ 
/*     */   public void setTelphone(String telphone)
/*     */   {
/* 232 */     this.telphone = telphone;
/*     */   }
/*     */ 
/*     */   public Date getLeaveDate()
/*     */   {
/* 240 */     return this.leaveDate;
/*     */   }
/*     */ 
/*     */   public void setLeaveDate(Date leaveDate)
/*     */   {
/* 249 */     this.leaveDate = leaveDate;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.leavepersonnelfiles.LeavePerson
 * JD-Core Version:    0.6.2
 */