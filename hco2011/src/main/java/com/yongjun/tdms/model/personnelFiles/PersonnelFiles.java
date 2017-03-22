/*     */ package com.yongjun.tdms.model.personnelFiles;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.base.institution.Institution;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.security.Department;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.base.area.Area;
/*     */ import com.yongjun.tdms.model.base.duty.Duty;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class PersonnelFiles extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String code;
/*     */   private String name;
/*     */   private String fileNo;
/*     */   private Institution inst;
/*     */   private Department dept;
/*     */   private Duty duty;
/*  50 */   private boolean sex = false;
/*     */   private String idNumber;
/*     */   private Date birthday;
/*     */   private CodeValue marriage;
/*     */   private Area birthplace;
/*     */   private CodeValue national;
/*     */   private CodeValue politice;
/*     */   private CodeValue education;
/*     */   private String homeTel;
/*     */   private String mobile;
/*     */   private String telphone;
/*     */   private String email;
/*     */   private Date entryDate;
/*     */   private Date regularizedDate;
/*     */   private Date separationDate;
/*     */   private String address;
/*     */   private CodeValue workway;
/*     */   private CodeValue status;
/*     */ 
/*     */   public boolean equals(Object arg0)
/*     */   {
/*  76 */     if (arg0 == this) {
/*  77 */       return true;
/*     */     }
/*  79 */     if (!(arg0 instanceof PersonnelFiles)) {
/*  80 */       return false;
/*     */     }
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  87 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getAddress()
/*     */   {
/*  94 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address)
/*     */   {
/* 101 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public Date getBirthday()
/*     */   {
/* 108 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday)
/*     */   {
/* 115 */     this.birthday = birthday;
/*     */   }
/*     */ 
/*     */   public Area getBirthplace()
/*     */   {
/* 122 */     return this.birthplace;
/*     */   }
/*     */ 
/*     */   public void setBirthplace(Area birthplace)
/*     */   {
/* 129 */     this.birthplace = birthplace;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 136 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 143 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Duty getDuty()
/*     */   {
/* 164 */     return this.duty;
/*     */   }
/*     */ 
/*     */   public void setDuty(Duty duty)
/*     */   {
/* 171 */     this.duty = duty;
/*     */   }
/*     */ 
/*     */   public CodeValue getEducation()
/*     */   {
/* 178 */     return this.education;
/*     */   }
/*     */ 
/*     */   public void setEducation(CodeValue education)
/*     */   {
/* 185 */     this.education = education;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 192 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 199 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public Date getEntryDate()
/*     */   {
/* 206 */     return this.entryDate;
/*     */   }
/*     */ 
/*     */   public void setEntryDate(Date entryDate)
/*     */   {
/* 213 */     this.entryDate = entryDate;
/*     */   }
/*     */ 
/*     */   public String getFileNo()
/*     */   {
/* 220 */     return this.fileNo;
/*     */   }
/*     */ 
/*     */   public void setFileNo(String fileNo)
/*     */   {
/* 227 */     this.fileNo = fileNo;
/*     */   }
/*     */ 
/*     */   public String getHomeTel()
/*     */   {
/* 234 */     return this.homeTel;
/*     */   }
/*     */ 
/*     */   public void setHomeTel(String homeTel)
/*     */   {
/* 241 */     this.homeTel = homeTel;
/*     */   }
/*     */ 
/*     */   public String getIdNumber()
/*     */   {
/* 248 */     return this.idNumber;
/*     */   }
/*     */ 
/*     */   public void setIdNumber(String idNumber)
/*     */   {
/* 255 */     this.idNumber = idNumber;
/*     */   }
/*     */ 
/*     */   public Department getDept() {
/* 259 */     return this.dept;
/*     */   }
/*     */ 
/*     */   public void setDept(Department dept) {
/* 263 */     this.dept = dept;
/*     */   }
/*     */ 
/*     */   public Institution getInst() {
/* 267 */     return this.inst;
/*     */   }
/*     */ 
/*     */   public void setInst(Institution inst) {
/* 271 */     this.inst = inst;
/*     */   }
/*     */ 
/*     */   public CodeValue getMarriage()
/*     */   {
/* 278 */     return this.marriage;
/*     */   }
/*     */ 
/*     */   public void setMarriage(CodeValue marriage)
/*     */   {
/* 285 */     this.marriage = marriage;
/*     */   }
/*     */ 
/*     */   public String getMobile()
/*     */   {
/* 306 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile)
/*     */   {
/* 313 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 320 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 327 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public CodeValue getNational()
/*     */   {
/* 334 */     return this.national;
/*     */   }
/*     */ 
/*     */   public void setNational(CodeValue national)
/*     */   {
/* 341 */     this.national = national;
/*     */   }
/*     */ 
/*     */   public CodeValue getPolitice()
/*     */   {
/* 348 */     return this.politice;
/*     */   }
/*     */ 
/*     */   public void setPolitice(CodeValue politice)
/*     */   {
/* 355 */     this.politice = politice;
/*     */   }
/*     */ 
/*     */   public Date getRegularizedDate()
/*     */   {
/* 362 */     return this.regularizedDate;
/*     */   }
/*     */ 
/*     */   public void setRegularizedDate(Date regularizedDate)
/*     */   {
/* 369 */     this.regularizedDate = regularizedDate;
/*     */   }
/*     */ 
/*     */   public Date getSeparationDate()
/*     */   {
/* 390 */     return this.separationDate;
/*     */   }
/*     */ 
/*     */   public void setSeparationDate(Date separationDate)
/*     */   {
/* 397 */     this.separationDate = separationDate;
/*     */   }
/*     */ 
/*     */   public boolean isSex()
/*     */   {
/* 404 */     return this.sex;
/*     */   }
/*     */ 
/*     */   public void setSex(boolean sex)
/*     */   {
/* 411 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */   public CodeValue getStatus()
/*     */   {
/* 418 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(CodeValue status)
/*     */   {
/* 425 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getTelphone()
/*     */   {
/* 432 */     return this.telphone;
/*     */   }
/*     */ 
/*     */   public void setTelphone(String telphone)
/*     */   {
/* 439 */     this.telphone = telphone;
/*     */   }
/*     */ 
/*     */   public CodeValue getWorkway()
/*     */   {
/* 446 */     return this.workway;
/*     */   }
/*     */ 
/*     */   public void setWorkway(CodeValue workway)
/*     */   {
/* 453 */     this.workway = workway;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.personnelFiles.PersonnelFiles
 * JD-Core Version:    0.6.2
 */