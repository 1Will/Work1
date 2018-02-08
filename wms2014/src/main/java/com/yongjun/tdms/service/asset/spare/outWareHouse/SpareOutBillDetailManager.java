/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.asset.spare.outWareHouse;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;

/**
 * <p>Title: SpareOutBillDetailManager
 * <p>Description: 备件出库明细业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface SpareOutBillDetailManager {
    List<SpareOutBillDetail> loadAllSpareOutBillDetail(Long[] spareOutBillDetailIds);
    List<Integer> loadSpareLocations(String warehouseid);
    Integer loadSpareOutBillDetailCount(String outBillId);
    SpareOutBillDetail loadSpareOutBillDetail(Long spareOutBillDetailId);
    @Transactional
    void storeSpareOutBillDetail(SpareOutBillDetail spareOutBillDetail);
    @Transactional
	void deleteSpareOutBillDetail(SpareOutBillDetail spareOutBillDetail);
    @Transactional
	void deleteAllSpareOutBillDetail(List<SpareOutBillDetail> spareOutBillDetails ,SpareOutBill spareOutBill)throws Exception;
    @Transactional
    public void addSpareOutBillDetail(String spareOutBillDetailIds,SpareOutBill spareOutBill,User LoginUser);
    @Transactional
    public void addAutoSpareOutBillDetail(String spareOutBillDetailIds,SpareOutBill spareOutBill,User LoginUser);
    @Transactional
    public void saveSpareOutBillDetail(String allDetailIds,String allNumber,String allborrowerPeople,
    		String allNewOrOld,String allOutDate,String allComment,String allOutPeopleIds,String allSheBei,String allBanZu);
    /**
     * 
     * @param allDetailIds
     * @param allNumber
     * @param allborrowerPeople
     * @param allNewOrOld
     * @param allOutDate
     * @param allComment
     * @param allOutPeopleIds
     * @param allSheBei
     * @param allBanZu
     * @param allSheBeiNo 设备编号
     * @param allUseTypes 备件用途
     */
    @Transactional
    public void saveSpareOutBillDetail(String allDetailIds,String allNumber,String allborrowerPeople,
    		String allNewOrOld,String allOutDate,String allComment,String allOutPeopleIds,String allSheBei,String allBanZu,String allSheBeiNo,String allUseTypes);
    @Transactional
    public void saveSpareOutBillDetailHaveDisable(String allDetailIds,String allNumber,String allborrowerPeople,
    		String allNewOrOld,String allOutDate,String allComment,String allOutPeopleIds,String allSheBei,String allBanZu);
    @Transactional
    void storeSpareBorrowToSpareInBillDtl(SpareOutBill spareOutBill,String spareBorrowIds);
    
    //入库明细保存到出库明细
	void storeOutBillDtlToInBillDtl(SpareInBill spareInBill, String addSpareOutBillIds);
}
