/**
 * 
 */
package com.yongjun.tdms.dao.codevalue.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.codevalue.CodeValueDao;
import com.yongjun.tdms.model.codevalue.CodeValue;

/**
 * @author Administrator
 * @param <T>
 *
 */
public class HibernateCodeValue extends BaseHibernateDao implements
		CodeValueDao {

	public void deleteAllCodeValue(Collection<CodeValue> codeValues) {
		System.out.println("========="+codeValues.size());
		
		this.deleteAll(codeValues);
	}

	public void deleteCodeValue(CodeValue codeValue) {
		this.delete(codeValue);
	}

	public List<CodeValue> loadAllCodeValue(Long[] codeValueIds) {
		return this.loadAll(CodeValue.class, codeValueIds);
	}

	public List<CodeValue> loadAllCodeValue() {
		return this.loadAll(CodeValue.class);
	}

	public CodeValue loadCodeValue(Long codeValueId) {
		return this.load(CodeValue.class, codeValueId);
	}

	public void storeCodeValue(CodeValue codeValue) {
		this.store(codeValue);
	}	
	/**
	 * 加载一个实体，根据code来家在
	 */
	public List<CodeValue> loadByKey(String keyName,Object codeValue) throws DaoException {
		return this.loadByKey(CodeValue.class, keyName, codeValue);
	}
}
