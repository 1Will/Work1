package com.yongjun.tdms.model.asset.spare.spareWareHouse;

import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.Spare;
/**
 * <p>Title: SpareLocation
 * <p>Description: 备件库总台帐</p>
 * <p>Copyright: Copyright (c) 2011 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SpareLocation.java 28207 2010-10-28 10:48:20Z zbzhang $
 */
public class SpareWareHouse extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{



	/**
	 * 
	 */
	private static final long serialVersionUID = -5930982041871163986L;
	//关联备件
	private Spare spare;
	//关联仓库
	private Warehouse wareHouse;
	//最大库存[当仓库备件库存高于该数量，报警，提示库存已多余，目前该字段不启用]
	private Long maxStocks;
	//最小库存[当仓库备件库存低于该数量，报警，提示需要采购]
	private Long minStocks;
	//总库存[该备件在仓库中所有的库存]
	private Long stocks;
	private Long disableStocks=0L;//不可用数量
	private String oldSpare;
	
	//是否可用
	private String spareState;
	
	

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Long getDisableStocks() {
		return disableStocks;
	}

	public void setDisableStocks(Long disableStocks) {
		this.disableStocks = disableStocks;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public Long getMaxStocks() {
		return maxStocks;
	}

	public void setMaxStocks(Long maxStocks) {
		this.maxStocks = maxStocks;
	}

	public Long getMinStocks() {
		return minStocks;
	}

	public void setMinStocks(Long minStocks) {
		this.minStocks = minStocks;
	}

	public Long getStocks() {
		return stocks;
	}

	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}

	public Warehouse getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(Warehouse wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getOldSpare() {
		return oldSpare;
	}

	public void setOldSpare(String oldSpare) {
		this.oldSpare = oldSpare;
	}

	public String getSpareState() {
		return spareState;
	}

	public void setSpareState(String spareState) {
		this.spareState = spareState;
	}

	
}
