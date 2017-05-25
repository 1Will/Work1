package main.dao.impl;
import java.util.List;

import main.dao.CodeValueDao;
import main.pojo.CodeValue;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class CodeValueDaoImpl extends HibernateDaoSupport implements CodeValueDao {
	
	public Session getSuperSession() {
		
		return this.getSession(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValue> getCodeValueByCvid(Long id) {
         String hql="select temp from CodeValue temp where temp.cvId="+id;
         System.out.println("根据cvId获取CodeValue集合");
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public CodeValue getCodeValueById(Long id) {
		CodeValue codeValue = null;
		try {
			codeValue=(CodeValue) this.getSession().load(CodeValue.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return codeValue;
	}
	
	
	
}
