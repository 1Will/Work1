package com.yongjun.tdms.dao.base.document;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.document.ApplicationDoc;


public interface ApplicationDocDao {

	public ApplicationDoc loadApplicationDoc(Long applicationDocId);

	public List<ApplicationDoc> loadAllApplicationDocs(Long [] applicationDocIds);

	public List<ApplicationDoc> loadAllApplicationDocs();			//工装变更使用到！

	public void storeApplicationDoc(ApplicationDoc applicationDoc);

	public void deleteApplicationDoc(ApplicationDoc applicationDoc);
	
	public void deleteAllApplicationDocs(Collection<ApplicationDoc> applicationDocs);
	
	/**
	 * 获取手册类型文件的个数
	 * @return
	 */
	public Integer getNumberOfManualDoc();
	
	/**
	 * 获取所有的手册对象
	 * @return List
	 */
	public List<ApplicationDoc> getAllManualDoc();

}
