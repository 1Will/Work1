package com.github.wp.business.dao.contract.impl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.contract.RecordDao;
import com.github.wp.business.pojo.qiandan.HBillingrecord;
import com.github.wp.business.vo.RecordCustom;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 开票记录
 * @author dupeng
 * @version 1.0
 * @since 2016年8月17日
 */
@Repository
public class RecordDaoImpl implements RecordDao{

	@Resource
	public SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Pager<RecordCustom> findPage1(String code,String contractname,String customCode,String name, Pagination pagination){
		StringBuffer hql = new StringBuffer("select c.code as contractCode,c.contractname,b.code as customCode,b.name,r.code as recordCode,r.billingTime,r.currency,r.invoiceTitle");
		hql.append(" from HBillingrecord r , Hcontractmanagement c ,Bcustomerinfo b where r.contractManagementId = c.id");
		hql.append(" and c.customerInfoId = b.id");
		if(name != null && !"".equals(name)){
		hql.append(" and b.name like '%"+name+"%'");
		}
		Query query = this.getSuperSession().createQuery(hql.toString());
		query.setFirstResult(pagination.getFirstSize());//初始行数   
		query.setMaxResults(pagination.getMaxSize());
		Pager<RecordCustom> pager = new Pager<RecordCustom>();
		List list = query.list();
		//int count = list.size();
		pager.setTotal(list.size());
		List<RecordCustom> recordList = new ArrayList<RecordCustom>();
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size();i++){
				RecordCustom record = new RecordCustom();
				Object[] object = (Object[])list.get(i);
				record.setCode((String)object[0]);
				record.setContractname((String)object[1]);
				record.setCustomCode((String)object[2]);
				record.setCustoName((String)object[3]);
				record.setRecordCode((String)object[4]);
				record.setBillingTime((Timestamp)object[5]);
				record.setCurrency((String)object[6]);
				record.setInvoiceTitle((String)object[7]);
				
				recordList.add(record);
				pager.setRows(recordList);
			}
		}else{
			pager.setRows(null);
		}
		return pager;
		
	}
	
	@Override
	public void saveOrUpdate(HBillingrecord record){
		this.getSuperSession().saveOrUpdate(record);
	}
	
	@Override
	public void deleteOne(Long[] ids){
		for (Long id : ids) {
			HBillingrecord record = findOne(id);
			record.setEffectflag('D');
			this.getSuperSession().saveOrUpdate(record);
		}
	}
	
	@Override
	public HBillingrecord findOne(Long id) {
		return (HBillingrecord) this.getSuperSession().get(
				HBillingrecord.class, id);
	}
	
}
