package com.yongjun.tdms.model.prophase.business;
/**
 * 申购单明细枚举类
 * @author Administrator
 *
 */
public enum SubscribeDetailStatus {
	/**
	 * 新建
	 */
	NEW,
	/**
	 * 已汇总
	 */
	COLLECTED,
	/**
	 * 已采购
	 */
	PURCHASEED,
	/**
	 * 入库中
	 */
	INSPECTING,
	/**
	 * 已入库
	 */
	INSPECTED,
	/**
	 * 不采购
	 */
	NOTPURCHASEED
	
	
}
