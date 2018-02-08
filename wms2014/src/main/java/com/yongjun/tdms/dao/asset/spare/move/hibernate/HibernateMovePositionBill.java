package com.yongjun.tdms.dao.asset.spare.move.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.move.MovePositionBillDao;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBill;

public class HibernateMovePositionBill extends BaseHibernateDao implements MovePositionBillDao{
	public List<MovePositionBill> loadAllMovePositionBill(Long[] ids) {
		return this.loadAll(MovePositionBill.class,ids);
	}
	public MovePositionBill loadMovePositionBill(Long id) {
		return this.load(MovePositionBill.class,id);
	}
	public void storeMovePositionBill(MovePositionBill movePositionBill) {
           this.store(movePositionBill);		
	}
	public void deleteMovePositionBill(MovePositionBill movePositionBill) {
		this.delete(movePositionBill);
		
	}
	public void deleteAllMovePositionBill(Collection<MovePositionBill> movePositionBill) {
	    this.deleteAll(movePositionBill);
		
	}

}
