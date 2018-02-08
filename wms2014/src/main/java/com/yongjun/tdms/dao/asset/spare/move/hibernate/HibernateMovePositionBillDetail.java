package com.yongjun.tdms.dao.asset.spare.move.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.move.MovePositionBillDetailDao;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBillDetail;

public class HibernateMovePositionBillDetail extends BaseHibernateDao implements MovePositionBillDetailDao{

	public List<MovePositionBillDetail> loadAllMovePositionBillDtl(Long[] ids) {
		
		return this.loadAll(MovePositionBillDetail.class,ids);
	}

	public MovePositionBillDetail loadMovePositionBillDtl(Long id) {
		
		return this.load(MovePositionBillDetail.class,id);
	}

	public void storeMovePositionBillDtl(MovePositionBillDetail movePositionBillDtl) {
			this.store(movePositionBillDtl);
	}

	public void deleteMovePositionBillDtl(MovePositionBillDetail movePositionBillDtl) {
	        this.delete(movePositionBillDtl);
	}

	public void deleteAllMovePositionBillDtl(Collection<MovePositionBillDetail> movePositionBillDtls) {
			this.deleteAll(movePositionBillDtls);
	}

}
