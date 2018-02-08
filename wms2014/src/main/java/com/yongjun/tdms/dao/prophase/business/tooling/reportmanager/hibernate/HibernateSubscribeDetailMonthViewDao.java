package com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.SubscribeDetailMonthViewDao;
import com.yongjun.tdms.model.prophase.business.report.SubscribeCountByDeptReport;

public class HibernateSubscribeDetailMonthViewDao extends BaseHibernateDao implements
		SubscribeDetailMonthViewDao {

	public List<SubscribeCountByDeptReport> loadByKey(String key, Object value)
			throws Exception {
		return super.loadByKey(SubscribeCountByDeptReport.class, key, value);
	}

	public List<SubscribeCountByDeptReport> loadByKeyArrys(String[] keyArrys,
			Object[] values) throws Exception {
		return super.loadByKeyArray(SubscribeCountByDeptReport.class, keyArrys,
				values);
	}

	public void storeMonthView(SubscribeCountByDeptReport monthView) {
		super.store(monthView);
	}

	public List<SubscribeCountByDeptReport> loadByAllMonthView() {
		return super.loadAll(SubscribeCountByDeptReport.class);
	}

	public void deleteAllMonthView(Collection<SubscribeCountByDeptReport> coll) {
		super.deleteAll(coll);
	}

	public void deleteAllMonthView() {
		String sql = "truncate table t_sub_detail_month_view";
		Session session = this.getSession();
		Connection conn = session.connection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	public void createMidView(String count, String subIds) {
		String sql = "create view midView_zzb as select * from t_device_pur_report where "
				+ count + " in(" + subIds + ")";
		Session session = this.getSession();
		Connection conn = session.connection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dropMidView() {
		String sql = "drop view midView_zzb ";
		Session session = this.getSession();
		Connection conn = session.connection();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 或得某月的统计数量
	 * 
	 * @param startDate
	 *            月初时间
	 * @param endDate
	 *            月末时间
	 * @return
	 */
	public List<SubscribeCountByDeptReport> loadDetailViewNumber(String[] array) {
		List<SubscribeCountByDeptReport> list = new ArrayList<SubscribeCountByDeptReport>();
		List<SubscribeCountByDeptReport> listt = new ArrayList<SubscribeCountByDeptReport>();
//		Session session = this.getSession();
//		Connection conn = session.connection();
//		CallableStatement call = null;
//		ResultSet rs = null;
//		try {
//			// this.dropMidView();
//			// this.createMidView("subId",subIds);
//
//			call = conn.prepareCall("{Call GET_DETAIL_NUM1(?,?,?)}");
//			call.setString(1, dept);
//			call.setDate(2, new java.sql.Date(startDate.getTime()));
//			call.setDate(3, new java.sql.Date(endDate.getTime()));
//
//			call.execute();
//			rs = (ResultSet) call.getResultSet();
//			while (rs.next()) {
//				SubscribeCountByDeptReport view = new SubscribeCountByDeptReport();
//				view.setMonth1(rs.getString("month1").substring(0, 11));
//				view.setMonth2(rs.getString("month2").substring(0, 11));
//				view.setDept(rs.getString("dept"));
//				view.setPlanNum(rs.getInt("planNum"));
//				view.setPurNum(rs.getInt("purNum"));
//				view.setCalNum(rs.getInt("calNum"));
//				view.setConNum(rs.getInt("conNum"));
//				view.setSumPrice(rs.getDouble("sumPrice"));
//				view.setBplanNum(rs.getInt("bplanNum"));
//				view.setBpurNum(rs.getInt("bpurNum"));
//				view.setBcalNum(rs.getInt("bcalNum"));
//				view.setBconNum(rs.getInt("bconNum"));
//				view.setBsumPrice(rs.getDouble("bsumPrice"));
//				list.add(view);
//			}
			
			Session session = getSession();
			Criteria cr = session.createCriteria(SubscribeCountByDeptReport.class); //生成一个Criteria对象
				if (array[1] != "" && !array[1].equals("")) {
					cr.add(Restrictions.like("deptId", array[1].trim()));
				}
				if (array[0] != "" && !array[0].equals("")) {
					cr.add(Restrictions.like("month", array[0].trim()));
				}
				 list = cr.list();
					/**
					 * 低耗计划数合计
					 */
					Integer dplanNum =0;
					/**
					 * 低耗采购数合计
					 */
					Integer dpurNum =0;
					/**
					 * 低耗取消数合计
					 */
					Integer dcalNum = 0;
					/**
					 * 低耗待确认数合计
					 */
					Integer dconNum = 0;
					/**
					 * 低耗采购费用合计
					 */
					Double dsumPrice =0.0;
					
					/**
					 * 备件计划数合计
					 */
					Integer bplanNum =0;
					/**
					 * 备件采购数合计
					 */
					Integer bpurNum =0;
					/**
					 * 备件取消数合计
					 */
					Integer bcalNum =0;
					/**
					 * 备件待确认数合计
					 */
					Integer bconNum = 0;
					/**
					 * 备件采购费用合计
					 */
					 Double bsumPrice =0.0;
				 for(SubscribeCountByDeptReport s: list){
					 if(s.getCategoryName().equals("备件")){
						 bplanNum+=s.getPlanNum();
						 bpurNum+=s.getPurNum();
						 bcalNum+=s.getCalNum();
						 bconNum+=s.getConNum();
						 bsumPrice+=s.getSumPrice();
						 
						 s.setBplanNum(bplanNum);
						 s.setBpurNum(bpurNum);
						 s.setBcalNum(bcalNum);
						 s.setBconNum(bconNum);
						 s.setBsumPrice(bsumPrice);
					 }else{
						 dplanNum+=s.getPlanNum();
						 dpurNum+=s.getPurNum();
						 dcalNum+=s.getCalNum();
						 dconNum+=s.getConNum();
						 dsumPrice+=s.getSumPrice();
						 
						 s.setDplanNum(dplanNum);
						 s.setDpurNum(dpurNum);
						 s.setDcalNum(dcalNum);
						 s.setDconNum(dconNum);
						 s.setDsumPrice(dsumPrice);
					 }
					 
					 if(dplanNum==0){
						 s.setDplanNum(0);
					 }
					 if(dpurNum==0){
						 s.setDpurNum(0);
					 }
					 if(dcalNum==0){
						 s.setDcalNum(0);
					 }
					 if(dconNum==0){
						 s.setDconNum(0);
					 }
					 if(dsumPrice==0){
						 s.setDsumPrice(0.0);
					 }
					 
					 if(bplanNum==0){
						 s.setBplanNum(0);
					 }
					 if(bpurNum==0){
						 s.setBpurNum(0);
					 }
					 if(bcalNum==0){
						 s.setBcalNum(0);
					 }
					 if(bconNum==0){
						 s.setBconNum(0);
					 }
					 if(bsumPrice==0){
						 s.setBsumPrice(0.0);
					 }
					 listt.add(s);
					 
				 }
				return listt;


	}

	/**
	 * 或得某月的统计数量 供应商
	 * 
	 * @param startDate
	 *            月初时间
	 * @param endDate
	 *            月末时间
	 * @return
	 */
//	public List<SubscribeCountByDeptReport> loadDetailViewNumberSupplier(
//			String supplier,Date year_month) {
//		List<SubscribeCountByDeptReport> list = new ArrayList<SubscribeCountByDeptReport>();
//		Session session = this.getSession();
//		Connection conn = session.connection();
//		CallableStatement call = null;
//		ResultSet rs = null;
//
//		try {
//			// this.dropMidView();
//			// this.createMidView("purId",purIds);
//			// this.deleteAllMonthView();
//			call = conn.prepareCall("{Call GET_DETAIL_NUM2(?,?,?)}");
//			call.setString(1, supplier);
//			call.setDate(2, new java.sql.Date(startDate.getTime()));
//			call.setDate(3, new java.sql.Date(endDate.getTime()));
//
//			call.execute();
//			rs = (ResultSet) call.getResultSet();
//			while (rs.next()) {
//				SubscribeCountByDeptReport view = new SubscribeCountByDeptReport();
//				view.setMonth1(rs.getString("month1").substring(0, 11));
//				view.setMonth2(rs.getString("month2").substring(0, 11));
//				view.setSupplier(rs.getString("supplier"));
//				view.setPlanNum(rs.getInt("planNum"));
//				view.setPurNum(rs.getInt("purNum"));
//				view.setCalNum(rs.getInt("calNum"));
//				view.setConNum(rs.getInt("conNum"));
//				view.setSumPrice(rs.getDouble("sumPrice"));
//				view.setBplanNum(rs.getInt("bplanNum"));
//				view.setBpurNum(rs.getInt("bpurNum"));
//				view.setBcalNum(rs.getInt("bcalNum"));
//				view.setBconNum(rs.getInt("bconNum"));
//				view.setBsumPrice(rs.getDouble("bsumPrice"));
//				list.add(view);
//				// this.storeMonthView(view);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			session.clear();
//		}
//
//		return list;
//
//	}

	public void deleteAllMonthViews(Collection<SubscribeCountByDeptReport> coll) {
		this.deleteAll(coll);
	}

	public List<SubscribeCountByDeptReport> loadDetailViewNumberSupplier(String purIds, Date year_month) {
		return null;
	}

}
