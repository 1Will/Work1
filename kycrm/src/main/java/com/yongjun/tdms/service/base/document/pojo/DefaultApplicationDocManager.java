 package com.yongjun.tdms.service.base.document.pojo;
 
 import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
 import com.yongjun.pluto.service.impl.BaseManager;
 import com.yongjun.pluto.service.security.UserManager;
 import com.yongjun.tdms.dao.base.document.ApplicationDocDao;
 import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
 import com.yongjun.tdms.service.base.document.ApplicationDocManager;

 import java.util.Collection;
 import java.util.List;
 import java.util.Properties;
 
 public class DefaultApplicationDocManager extends BaseManager
   implements ApplicationDocManager
 {
   private final ApplicationDocDao applicationDocDao;
   private final UserManager userManager;
   protected Properties systemParameterConfiguration;
 
   public DefaultApplicationDocManager(ApplicationDocDao applicationDocDao, UserManager userManager)
   {
     this.applicationDocDao = applicationDocDao;
     this.userManager = userManager;
   }
 
   public ApplicationDoc loadApplicationDoc(Long applicationDocId) {
     return this.applicationDocDao.loadApplicationDoc(applicationDocId);
   }
 
   public List<ApplicationDoc> loadAllApplicationDocs(Long[] applicationDocIds) {
     return this.applicationDocDao.loadAllApplicationDocs(applicationDocIds);
   }
 
   public List<ApplicationDoc> loadAllApplicationDocs() {
     return this.applicationDocDao.loadAllApplicationDocs();
   }
 
   public void storeApplicationDoc(ApplicationDoc applicationDoc) {
     applicationDoc.setCreator(this.userManager.getUser().getName());
     this.applicationDocDao.storeApplicationDoc(applicationDoc);
   }
 
   public void deleteApplicationDoc(ApplicationDoc applicationDoc) {
     this.applicationDocDao.deleteApplicationDoc(applicationDoc);
   }
 
   public void deleteAllApplicationDocs(Collection<ApplicationDoc> applicationDocs) {
     this.applicationDocDao.deleteAllApplicationDocs(applicationDocs);
   }
 
   public Integer getNumberOfManualDoc()
   {
     return this.applicationDocDao.getNumberOfManualDoc();
   }
 
   public Properties getSystemParameterConfiguration() {
     return this.systemParameterConfiguration;
   }
 
   public void setSystemParameterConfiguration(Properties systemParameterConfiguration)
   {
     this.systemParameterConfiguration = systemParameterConfiguration;
   }
 
   public boolean isMostNumberForTheManualDoc() {
     String manualDocNumber = this.systemParameterConfiguration.getProperty("help_manual_number");
     if (manualDocNumber != null)
     {
       Integer number = getNumberOfManualDoc();
 
       if (number.compareTo(Integer.valueOf(manualDocNumber)) >= 0) {
         return true;
       }
     }
     return false;
   }
 
   public Integer getMostUploadNumberForManualDoc() {
     Integer number = Integer.valueOf(0);
     String manualDocNumber = this.systemParameterConfiguration.getProperty("help_manual_number");
     if (null != manualDocNumber) {
       number = Integer.valueOf(manualDocNumber);
     }
     return number;
   }
 
   public List<ApplicationDoc> getAllManualDoc() {
     return this.applicationDocDao.getAllManualDoc();
   }
   public List<ApplicationDoc> loadByKey(String keyName, Object keyValue) throws DaoException {
     return this.applicationDocDao.loadByKey(keyName, keyValue);
   }
 
   public List<ApplicationDoc> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
     return this.applicationDocDao.loadByKeyArray(keyNames, keyValues);
   }

 }

