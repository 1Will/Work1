package com.yongjun.tdms.model.asset.spare;

public class QueryUnMatchSpare {
	private Long inventoryNum;		// 实际盘点数
	private Long currentSysStocks;	// 当前系统台帐记录库存
	private Spare spare; // 盘点备件a
	public Long getCurrentSysStocks() {
		return currentSysStocks;
	}
	public void setCurrentSysStocks(Long currentSysStocks) {
		this.currentSysStocks = currentSysStocks;
	}
	public Long getInventoryNum() {
		return inventoryNum;
	}
	public void setInventoryNum(Long inventoryNum) {
		this.inventoryNum = inventoryNum;
	}
	public Spare getSpare() {
		return spare;
	}
	public void setSpare(Spare spare) {
		this.spare = spare;
	}

}
