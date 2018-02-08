package com.yongjun.tdms.dao.asset.spare.inventoryAndCleanHouse;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.asset.spare.inventoryAndCleanHouse.SpareInventoryAndCleanHouseReportView;

/**
 * 清仓盘点报表接口
 * */
public interface SpareInventoryAndCleanHouseReportViewDao {
 List<SpareInventoryAndCleanHouseReportView> getSpareInventoryAndCleanHouseViewCollention(String []array)throws HibernateException;
}
