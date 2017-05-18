package main.dao.impl;

import java.util.List;

import main.dao.ContactArchivesDao;
import main.pojo.ContactArchives;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ContactArchivesDaoImpl extends HibernateDaoSupport implements ContactArchivesDao {

	@Override
	public void saveContactArchives(ContactArchives contactArchives) {
           try {
			   this.getHibernateTemplate().save(contactArchives);
		} catch (Exception e) {
		       e.printStackTrace();
		}		
	}
    //根据id获取单个实体
	@SuppressWarnings("unchecked")
	@Override
	public ContactArchives getContactArchivesById(Long id) {
       ContactArchives contactArchives=null;
       try {
		   contactArchives=(ContactArchives)getSession().load(ContactArchives.class, id);
	} catch (Exception e) {
	       e.printStackTrace();
	}
		
		return contactArchives;
	}
	//初始化时获取所有实体
	@SuppressWarnings("unchecked")
	public List<ContactArchives> getAllContactArchives() {
		String hql = "from ContactArchives";
		System.out.println("====sql===="+hql);
		return this.getHibernateTemplate().find(hql);
	}
	/*@SuppressWarnings("unchecked")
	@Override
	public List<ContactArchives> getAllContactArchivesById(Long[] ids) {
       String hql="from ContactArchies cont where cont.id in'" + ids + "' ";
		return this.getHibernateTemplate().find(hql);
	}
*/
	@Override
	public Session getSuperSession() {

		return this.getSession(true);
	}
	@SuppressWarnings("unchecked")
	public  List<ContactArchives> getAllContactArchives(Long id) {
		String hql="from ContactArchives temp where temp.customerId="+id;
		
		return this.getHibernateTemplate().find(hql);
	}
	
}
