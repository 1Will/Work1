package com.yongjun.tdms.model.base.house;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.meter.ElectricMeter;

public class House extends BaseInfoEntity {

	private static final long serialVersionUID = -1298144819146616060L;

	private String code;// 编号
	private String name;
	private Building building;//楼
	private Floor floor;// 层
	private boolean hasWaterMeter;// 是否有水表
	private float waterMeterNum;// 水表数量
	private boolean hasElectricMeter;// 是否有电表
	private float electricMeterNum;// 电表数量
	private boolean hasAirMeter;// 是否有空调
	private float airMeterNum;// 空调表数量
	private boolean hasNetMeter;// 是否有网络
	private Double square;//面积
	private ElectricMeter aeMeter;//A电表
	private ElectricMeter beMeter;//B电表
	private Double price;//单价
	private Double total;//总价
	private CodeValue category;//房源类别
	private CodeValue source;//来源
	private CustomerInfo customerInfo;//供应商
	private CodeValue state;//状态
	private boolean isMain;//是否主营
	private CodeValue orientation;//朝向
	private Double height;//楼高
	private int airNum;//空调表数
	private CodeValue renovation;//装修
	private CodeValue eType;//电费类型
	private boolean hasProperty;//是否有物业
	private boolean hasEShare;//是否有电费公摊
	private int netNum=0;//端口数
	private Double netfee=0.0;//网络端口费
	private String outline;//说明
	private Double waterdisplay=0D;//水表示数
	private Double aedisplay=0D;//a电表示数
	private Double bedisplay=0D;//b电表示数
	private Double airdisplay=0D;//空调表总示数
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public String getCode() {
		return code;
	}

	public Floor getFloor() {
		return floor;
	}

	public String getName() {
		return name;
	}

	public boolean getHasWaterMeter() {
		return hasWaterMeter;
	}

	public Building getBuilding() {
		return building;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHasWaterMeter(boolean hasWaterMeter) {
		this.hasWaterMeter = hasWaterMeter;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public float getWaterMeterNum() {
		return waterMeterNum;
	}

	public boolean getHasElectricMeter() {
		return hasElectricMeter;
	}

	public float getElectricMeterNum() {
		return electricMeterNum;
	}

	public boolean getHasAirMeter() {
		return hasAirMeter;
	}

	public float getAirMeterNum() {
		return airMeterNum;
	}

	public boolean getHasNetMeter() {
		return hasNetMeter;
	}

	public void setWaterMeterNum(float waterMeterNum) {
		this.waterMeterNum = waterMeterNum;
	}

	public void setHasElectricMeter(boolean hasElectricMeter) {
		this.hasElectricMeter = hasElectricMeter;
	}

	public void setElectricMeterNum(float electricMeterNum) {
		this.electricMeterNum = electricMeterNum;
	}

	public void setHasAirMeter(boolean hasAirMeter) {
		this.hasAirMeter = hasAirMeter;
	}

	public void setAirMeterNum(float airMeterNum) {
		this.airMeterNum = airMeterNum;
	}

	public void setHasNetMeter(boolean hasNetMeter) {
		this.hasNetMeter = hasNetMeter;
	}

	public Double getSquare() {
		return square;
	}

	public void setSquare(Double square) {
		this.square = square;
	}

	public ElectricMeter getAeMeter() {
		return aeMeter;
	}

	public ElectricMeter getBeMeter() {
		return beMeter;
	}

	public void setAeMeter(ElectricMeter aeMeter) {
		this.aeMeter = aeMeter;
	}

	public void setBeMeter(ElectricMeter beMeter) {
		this.beMeter = beMeter;
	}

	public Double getPrice() {
		return price;
	}

	public Double getTotal() {
		return total;
	}

	public CodeValue getCategory() {
		return category;
	}

	public CodeValue getSource() {
		return source;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public CodeValue getState() {
		return state;
	}

	public CodeValue getOrientation() {
		return orientation;
	}

	public Double getHeight() {
		return height;
	}

	public int getAirNum() {
		return airNum;
	}

	public CodeValue getRenovation() {
		return renovation;
	}

	public CodeValue geteType() {
		return eType;
	}

	public boolean getHasProperty() {
		return hasProperty;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setCategory(CodeValue category) {
		this.category = category;
	}

	public void setSource(CodeValue source) {
		this.source = source;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public void setOrientation(CodeValue orientation) {
		this.orientation = orientation;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public void setAirNum(int airNum) {
		this.airNum = airNum;
	}

	public void setRenovation(CodeValue renovation) {
		this.renovation = renovation;
	}

	public void seteType(CodeValue eType) {
		this.eType = eType;
	}

	public void setHasProperty(boolean hasProperty) {
		this.hasProperty = hasProperty;
	}

	public boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(boolean isMain) {
		this.isMain = isMain;
	}

	public boolean getHasEShare() {
		return hasEShare;
	}

	public void setHasEShare(boolean hasEShare) {
		this.hasEShare = hasEShare;
	}

	public int getNetNum() {
		return netNum;
	}

	public void setNetNum(int netNum) {
		this.netNum = netNum;
	}

	public Double getNetfee() {
		return netfee;
	}

	public void setNetfee(Double netfee) {
		this.netfee = netfee;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public Double getWaterdisplay() {
		return waterdisplay;
	}

	public Double getAedisplay() {
		return aedisplay;
	}

	public Double getBedisplay() {
		return bedisplay;
	}

	public Double getAirdisplay() {
		return airdisplay;
	}

	public void setWaterdisplay(Double waterdisplay) {
		this.waterdisplay = waterdisplay;
	}

	public void setAedisplay(Double aedisplay) {
		this.aedisplay = aedisplay;
	}

	public void setBedisplay(Double bedisplay) {
		this.bedisplay = bedisplay;
	}

	public void setAirdisplay(Double airdisplay) {
		this.airdisplay = airdisplay;
	}
	
}
