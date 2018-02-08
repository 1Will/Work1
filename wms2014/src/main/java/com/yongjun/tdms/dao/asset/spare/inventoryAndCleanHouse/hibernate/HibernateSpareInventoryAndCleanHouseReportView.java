package com.yongjun.tdms.dao.asset.spare.inventoryAndCleanHouse.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.inventoryAndCleanHouse.SpareInventoryAndCleanHouseReportViewDao;
import com.yongjun.tdms.model.asset.spare.inventoryAndCleanHouse.SpareInventoryAndCleanHouseReportView;

public class HibernateSpareInventoryAndCleanHouseReportView extends
		BaseHibernateDao implements SpareInventoryAndCleanHouseReportViewDao {

	@SuppressWarnings("unchecked")
	public List<SpareInventoryAndCleanHouseReportView> getSpareInventoryAndCleanHouseViewCollention(
			String[] array) throws HibernateException {
		Session session = getSession();
		List<SpareInventoryAndCleanHouseReportView> result = null;

		try {
			String hql = " from SpareInventoryAndCleanHouseReportView as reportView  where 1=1";

			if (array[0] != "" && !array[0].equals("")) {
				hql = hql + " AND reportView.spareNo like :spareNo";
			}
			if (array[1] != "" && !array[1].equals("")) {
				hql = hql + " AND reportView.spareName like :spareName";
			}
			if (array[2] != "" && !array[2].equals("")) {
				hql = hql + " AND reportView.locationCode like :locationCode";
			}
			if (array[3] != "" && !array[3].equals("")) {
				hql = hql + " AND reportView.month like :month";
			}
			if (array[4] != "" && !array[4].equals("")) {
				hql = hql + " AND reportView.deptId= :departId";
			}
			if (array[5] != "" && !array[5].equals("")) {
				hql = hql + " AND reportView.toolingDevFlag =:toolingDevFlag";
			}
			if (array[6] != "" && !array[6].equals("")) {
				hql = hql + " AND reportView.modelSpecs like :modelSpecs";
			} 
			if(array[7] != "" && !array[7].equals("")&&array[7].equals("true")){
				hql = hql + " AND reportView.inStocks>0 or reportView.outStocks>0";
			}
			hql = hql + " order by reportView.toolingDevFlag desc";
			Query query = session.createQuery(hql);
			if (array[0] != "") {
				query.setParameter("spareNo", '%' + array[0].trim() + '%');
			}
			if (array[1] != "") {
				query.setParameter("spareName", '%' + array[1].trim() + '%');
			}
			if (array[2] != "") {
				query.setParameter("locationCode", '%' + array[2].trim() + '%');
			}
			if (array[3] != "") {
				query.setParameter("month", array[3].trim());
			}
			if (array[4] != "") {
				query.setParameter("departId", array[4].trim());
			}
			if (array[5] != "") {
				query.setParameter("toolingDevFlag", array[5].trim());
			}
			if (array[6] != "") {
				query.setParameter("modelSpecs", '%' + array[0].trim() + '%');
			}
			result = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}

}
