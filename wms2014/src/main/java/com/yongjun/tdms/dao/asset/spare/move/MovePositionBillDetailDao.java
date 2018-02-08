package com.yongjun.tdms.dao.asset.spare.move;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBillDetail;

public interface MovePositionBillDetailDao {
	  List<MovePositionBillDetail> loadAllMovePositionBillDtl(Long[] ids);
	  MovePositionBillDetail loadMovePositionBillDtl(Long id);
	  void storeMovePositionBillDtl(MovePositionBillDetail movePositionBillDtl);
	  void deleteMovePositionBillDtl(MovePositionBillDetail movePositionBillDtl);
	  void deleteAllMovePositionBillDtl(Collection<MovePositionBillDetail> movePositionBillDtls);
}
