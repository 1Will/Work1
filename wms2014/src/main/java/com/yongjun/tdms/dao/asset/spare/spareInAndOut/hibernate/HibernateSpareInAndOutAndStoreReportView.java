package com.yongjun.tdms.dao.asset.spare.spareInAndOut.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportViewDao;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportView;
public class HibernateSpareInAndOutAndStoreReportView extends BaseHibernateDao implements SpareInAndOutAndStoreReportViewDao{

	public List<SpareInAndOutAndStoreReportView> getSpareInAndOutAndStoreViewCollention(String[] array) throws HibernateException{
		Session session = getSession();
		Transaction tx = null; 
		List<SpareInAndOutAndStoreReportView> result=null;

        try{ 
        	String hql=" from SpareInAndOutAndStoreReportView as reportView  where 1=1";

        	if (array[0] != ""&&!array[0].equals("")) {
        		 hql = hql + " AND reportView.spareNo like :spareNo";
        	}
        	if (array[1] != ""&&!array[1].equals("")) {
        		 hql = hql + " AND reportView.spareName like :spareName";
        		 
        	}
        	if (array[2] != ""&&!array[2].equals("")) {
       		   hql = hql + " AND reportView.model like :model1";
         	}
       	   if (array[3] != ""&&!array[3].equals("")) {
       		    hql = hql + " AND reportView.locationCode like :locationCode";
       		 
       	    }
       	   if (array[4] != ""&&!array[4].equals("")) {
    		    hql = hql + " AND reportView.department= :depart_Name";
    	        }
         	if (array[5] != ""&&!array[5].equals("")) {
    		   hql = hql + " AND reportView.createTime>=:createTime_start";
        	}
    	   if (array[6] != ""&&!array[6].equals("")) {
    		    hql = hql + " AND reportView.createTime <=:createTime_end";
    	    }
    	  
    	   if (array[7] != ""&&!array[7].equals("")) {
      		    hql = hql + " AND reportView.toolingDevFlag =:toolingDevFlag";
      	    }
    	   if (array[9] != ""&&!array[9].equals("")) {
     		    hql = hql + " AND reportView.warehouse =:warehouse";
     	    }
    	   if (array[10] != ""&&!array[10].equals("")) {
    		    hql = hql + " AND reportView.regional =:regional";
    	    }
    	   if (array[8] != ""&&!array[8].equals("")&&array[8].equals("yes")) {
    		   hql = hql + " AND ( reportView.inStocks >0 or reportView.outStocks>0)";
     	    }
    	    hql=hql + " order by reportView.toolingDevFlag desc";
    		tx = session.beginTransaction();
    	    tx = session.beginTransaction();
			Query query = session.createQuery(hql);
            if (array[0] != ""){
			    query.setParameter("spareNo",'%'+ array[0].trim()+'%');
			}
			if(array[1] !=""){
				query.setParameter("spareName",'%'+ array[1].trim()+'%');
			}
			if (array[2] != "") {
				query.setParameter("model1", '%'+ array[2].trim()+'%');
			}
			if(array[3] != ""){
				query.setParameter("locationCode",'%'+ array[3].trim()+'%');
			}
			if(array[4]!=""){
				query.setParameter("depart_Name",array[4].trim());
			}
			if(array[5] != ""){
				query.setParameter("createTime_start",array[5].trim());
			}
			if(array[6] != ""){
				query.setParameter("createTime_end",array[6].trim());
			}
			if(array[7] != ""){
				query.setParameter("toolingDevFlag",array[7].trim());
			}
			if(array[9] != ""){
				query.setParameter("warehouse",Long.valueOf(array[9]));
			}
			if(array[10] != ""){
				query.setParameter("regional",Long.valueOf(array[10]));
			}
	        result=query.list();
	    	tx.commit();
        } catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}finally{
			releaseSession(session);
		}
        return result;
	}

}
