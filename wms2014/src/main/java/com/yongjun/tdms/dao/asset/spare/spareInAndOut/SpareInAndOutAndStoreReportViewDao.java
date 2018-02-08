package com.yongjun.tdms.dao.asset.spare.spareInAndOut;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportView;

public interface SpareInAndOutAndStoreReportViewDao {
 List<SpareInAndOutAndStoreReportView> getSpareInAndOutAndStoreViewCollention(String []array)throws HibernateException;
}
