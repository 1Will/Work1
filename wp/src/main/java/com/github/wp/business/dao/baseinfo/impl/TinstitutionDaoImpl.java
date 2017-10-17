package com.github.wp.business.dao.baseinfo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.baseinfo.TinstitutionDao;
import com.github.wp.business.pojo.base.Tinstitution;


/**
 * <p>
 * 单位管理中dao层实现: 杜鹏
 * <p>
 * Date: 07-20-16
 * <p>
 * Version: 1.0
 */
@Repository
public class TinstitutionDaoImpl implements TinstitutionDao{

	@Resource
	private SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 根据id得到此单位的所有子单位的集合
	 * @param id
	 * @return List<?>
	 * @author 杜鹏
	 * @since 07-20-16
	 */
	@Override
	public List<?> findChildById(Long id) {
		String hql = "from Tinstitution tempA";
		if (id == null)
			hql += " where tempA.sysTinstitution.id is null";
		else
			hql += " where tempA.sysTinstitution.id = " + id;
		return this.getSuperSession().createQuery(hql).list();
	}

	
	/**
	 * @param id
	 * @return Tinstitution 单位
	 * @author dupeng
	 * @since 07-20-2016
	 */
	public Tinstitution findOne(Long id) {
		return (Tinstitution) this.getSuperSession().get(
				Tinstitution.class, id);
	}
	
	/**
	 * @param Tinstitution
	 * @return Tinstitution 保存或新增单位信息
	 * @author dupeng
	 * @since 07-21-2016
	 */
	
	public void saveOrUpdate(Tinstitution tinstitution){
		this.getSuperSession().saveOrUpdate(tinstitution);
	}
	
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 07-21-16
	 * 根据id得到tinstitution单位对象，改变单位的表示状态为D，保存，假删除
	 */
	public void deleteOne(Long id){
		Tinstitution tinstitution = findOne(id);
		tinstitution.setEffectflag('D');
		this.getSuperSession().saveOrUpdate(tinstitution);
	}
	
}
