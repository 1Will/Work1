package com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.PurchaseCountBySupplierReportDao;
import com.yongjun.tdms.model.prophase.business.report.PurchaseCountBySupplierReport;
import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;

public class HibernatePurchaseCountBySupplierReportDao extends BaseHibernateDao
		implements PurchaseCountBySupplierReportDao {

	public List<PurchaseCountBySupplierReport> loadDetailViewNumberSupplier(
			String[] array) {
				 
				 
				 List<PurchaseCountBySupplierReport> list = new ArrayList<PurchaseCountBySupplierReport>();
				 Session session = this.getSession();
				 Connection conn = session.connection();
				 PreparedStatement pst  = null;
				 ResultSet rs = null;
				 String str ="";
				 String sql ="select [month] as [month],SUPPLIER_NAME as supplierName,CATEGORY_NAME as categoryName,PURNUM as purNum,SUMPRICE as sumPrice,"+
"(select SUM(PURNUM) from t_purCount_bySupplier_report where CATEGORY_NAME ='备件' ) as bpurNum,"+
"(select SUM(SUMPRICE) from t_purCount_bySupplier_report where CATEGORY_NAME ='备件' ) as bsumPrice,"+
"(select SUM(PURNUM) from t_purCount_bySupplier_report where CATEGORY_NAME ='低耗' ) as dpurNum,"+
"(select SUM(SUMPRICE) from t_purCount_bySupplier_report where CATEGORY_NAME ='低耗' ) as dsumPrice from t_purCount_bySupplier_report"; //where [month]=? and SUPPLIER_NAME =?";
			 String groupby=" GROUP BY SUPPLIER_NAME, [month], CATEGORY_NAME, PURNUM, SUMPRICE";
				 if("" !=array[0] && "" != array[1]){
					 str =sql+" where [month]=? and SUPPLIER_NAME =? "+groupby;
				 }else if("" !=array[0]){
					 System.out.println("rrrrrrrrr"+array[0]);
					 str =sql+" where [month]=? "+groupby;
				 }else if("" != array[1]){
					 System.out.println("dfsfsfsd"+array[1]);
					 str =sql+" where SUPPLIER_NAME =? "+groupby;
				 }else{
					 str =sql +groupby;
				 }
				 try {
					 pst = conn.prepareStatement(str);
					 if("" !=array[0]){
						 pst.setString(1, array[0].trim());
					 }
					 if("" != array[1]){
						 pst.setString(2, array[1].trim()); 
					 }
					 
					rs = pst.executeQuery();
					/**
					 * 低耗采购数合计
					 */
					Integer dpurNum =0;
					/**
					 * 低耗采购费用合计
					 */
					Double dsumPrice =0.0;
					
					/**
					 * 备件采购数合计
					 */
					Integer bpurNum =0;
					/**
					 * 备件采购费用合计
					 */
					 Double bsumPrice =0.0;
					while (rs.next()) {
						PurchaseCountBySupplierReport view = new PurchaseCountBySupplierReport();
						if(null ==rs.getString("supplierName")){
							view.setSupplierName("0");
						}else{
							view.setSupplierName(rs.getString("supplierName"));
						}
						if(null == rs.getString("categoryName")){
							view.setCategoryName("0");
						}else{
							view.setCategoryName(rs.getString("categoryName"));
						}
						view.setPurNum(rs.getInt("purNum"));
						view.setSumPrice(rs.getDouble("sumPrice"));
						view.setMonth(rs.getString("month"));
						if(rs.getString("categoryName").equals("备件")){
							 bpurNum+=rs.getInt("purNum");
							 bsumPrice+=rs.getDouble("sumPrice");
							 view.setBpurNum(bpurNum);
							 view.setBsumPrice(bsumPrice);
						 }else{
							 dpurNum+=rs.getInt("purNum");
							 dsumPrice+=rs.getDouble("sumPrice");
							 view.setDpurNum(dpurNum);
							 view.setDsumPrice(dsumPrice);
						 }
						 if(0 == dpurNum){
							 view.setDpurNum(0);
						 }
						 if(0 == dsumPrice){
							 view.setDsumPrice(0.0);
						 }
						 if(0 ==bpurNum){
							 view.setBpurNum(0);
						 }
						 if(0 == bsumPrice){
								view.setBsumPrice(0.0);
							}
					    list.add(view);
						 
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;  
	}

}
