package com.yongjun.tdms.service.asset.spare.move.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.move.MovePositionBillDao;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBill;
import com.yongjun.tdms.model.asset.spare.move.MovePositionStatus;
import com.yongjun.tdms.service.asset.spare.move.MovePositionBillManager;

public class DefaultMovePositionBillManager extends BaseManager implements MovePositionBillManager{
    private final MovePositionBillDao movePositionBillDao;
    private final SequenceManager movePostionBillSequenceManager;
    public DefaultMovePositionBillManager(MovePositionBillDao movePositionBillDao,SequenceManager movePostionBillSequenceManager){
    	this.movePositionBillDao=movePositionBillDao;
    	this.movePostionBillSequenceManager = movePostionBillSequenceManager;
    }
	public List<MovePositionBill> loadAllMovePositionBill(Long[] ids) {
		return movePositionBillDao.loadAllMovePositionBill(ids);
	}
	public MovePositionBill loadMovePositionBill(Long id) {
		return this.movePositionBillDao.loadMovePositionBill(id);
	}
	public void storeMovePositionBill(MovePositionBill movePositionBill) {
		//自动生成编号
		if(movePositionBill.isNew()){
			movePositionBill.setBillNo((String)movePostionBillSequenceManager.generate("-"));
		}
		this.movePositionBillDao.storeMovePositionBill(movePositionBill);
		
	}
	public void deleteMovePositionBill(MovePositionBill movePositionBill) {
		this.movePositionBillDao.deleteMovePositionBill(movePositionBill);
		
	}
	public void deleteAllMovePositionBill(Collection<MovePositionBill> movePositionBills) {
		this.movePositionBillDao.deleteAllMovePositionBill(movePositionBills);
		
	}
	/**
	 * 对选中需要失效的记录进行循环失效
	 * @throws Exception 
	 */
	public void disableMovePostionBill(List<MovePositionBill> movePositionBills) throws Exception {
		for(MovePositionBill mpb:movePositionBills){
			if(mpb.getMoveStatus().equals(MovePositionStatus.movePositioning)||mpb.getMoveStatus().equals(MovePositionStatus.movePositioned)){
				throw new Exception("The Runtime Exception");
			}
			mpb.setDisabled(true);
			movePositionBillDao.storeMovePositionBill(mpb);
		}
	}
	/**
	 * 对选中需要失效的记录进行循环有效
	 */
	public void enableMovePostionBill(List<MovePositionBill> movePositionBills) {
		for(MovePositionBill mpb:movePositionBills){
			mpb.setDisabled(false);
			movePositionBillDao.storeMovePositionBill(mpb);
		}
	}
}
