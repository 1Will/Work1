/*
 * YONGJUN-TEACHNOLOGY
 */
package com.yongjun.tdms.model.asset.spare;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * @author yli-JohnSon
 * @Date 2009-4-11
 */
public class Location extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5070004312452569035L;
	/*
	 * YONGJUN-TEACHNOLOGY
	 */
	//库位号
	private String code;
	//状态
	private LocationStatus status = LocationStatus.NON_USE;
	//最大承重量
	private Double maxWeight = 0.0;
	//库位长度
	private Double length = 0.0;
	//库位高度
	private Double hight = 0.0;
	//宽度
	private Double wide = 0.0;
	//体积
	private Double volume = 0.0;
	//描述
	private String describe;
	//关联的的备件库位表
	private Set<SpareLocation> spareLoc = new HashSet<SpareLocation>();
	//仓库
	private Warehouse warehouse;
	//库区
	private Regional regional;
	//库位类型
	private CodeValue locationType;
	//最大载重
	private CodeValue bearload;
	//相同备件存放
	private CodeValue samestore;
	//混放模式
	private CodeValue mixmode;
	//过道
	private String passageway;
	//过道排位
	private String row;
	//货架层次
	private String level;
	//货架格位
	private String site;
	//	仓库级别
	private CodeValue storageGrade;
	
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPassageway() {
		return passageway;
	}

	public void setPassageway(String passageway) {
		this.passageway = passageway;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public CodeValue getBearload() {
		return bearload;
	}

	public void setBearload(CodeValue bearload) {
		this.bearload = bearload;
	}


	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

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
	/******************相应的setter、getter********************/
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Double getHight() {
		return hight;
	}

	public void setHight(Double hight) {
		this.hight = hight;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getWide() {
		return wide;
	}

	public void setWide(Double wide) {
		this.wide = wide;
	}

	public LocationStatus getStatus() {
		return status;
	}

	public void setStatus(LocationStatus status) {
		this.status = status;
	}

	public Set<SpareLocation> getSpareLoc() {
		return spareLoc;
	}

	public void setSpareLoc(Set<SpareLocation> spareLoc) {
		this.spareLoc = spareLoc;
	}

	public CodeValue getMixmode() {
		return mixmode;
	}

	public void setMixmode(CodeValue mixmode) {
		this.mixmode = mixmode;
	}

	public CodeValue getLocationType() {
		return locationType;
	}

	public void setLocationType(CodeValue locationType) {
		this.locationType = locationType;
	}

	public CodeValue getSamestore() {
		return samestore;
	}

	public void setSamestore(CodeValue samestore) {
		this.samestore = samestore;
	}

	public CodeValue getStorageGrade() {
		return storageGrade;
	}

	public void setStorageGrade(CodeValue storageGrade) {
		this.storageGrade = storageGrade;
	}

}
