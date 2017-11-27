package com.yongjun.tdms.presentation.webwork.action.base.house;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.tdms.model.base.house.Building;
import com.yongjun.tdms.model.base.house.Floor;
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.base.meter.ElectricMeter;
import com.yongjun.tdms.service.base.house.BuildingManager;
import com.yongjun.tdms.service.base.house.FloorManager;
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.base.meter.ElectricMeterManager;

public class ImportHouseAction extends FileTransportAction {
	private String message = "";
	private final HouseManager houseManager;
	private final CodeValueManager codeValueManager;
	private final FloorManager floorManager;
	private final BuildingManager buildingManager;
	private final ElectricMeterManager electricMeterManager;
	public DecimalFormat format = new DecimalFormat("0.00");
	private static final long serialVersionUID = -877987215782827292L;

	public void prepare() throws Exception {

	}

	public ImportHouseAction(HouseManager houseManager, CodeValueManager codeValueManager,
			FloorManager floorManager,BuildingManager buildingManager,ElectricMeterManager electricMeterManager) {
		this.houseManager = houseManager;
		this.codeValueManager = codeValueManager;
		this.floorManager = floorManager;
		this.buildingManager = buildingManager;
		this.electricMeterManager = electricMeterManager;
	}

	public String save() throws Exception {
		File file = getFile();// 获取文件
		InputStream is = new FileInputStream(file);// 转化为流
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);//
		if (hssfWorkbook != null) {
			String result = checkFile(hssfWorkbook);// 检查表格
			if (result.equals("")) {// 检查结果为空。表示数据正常，
				insertFile(hssfWorkbook);// 导入数据
			}
		}

		return "success";
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		}
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			double cellValue = hssfCell.getNumericCellValue();
			String cdd = new DecimalFormat("#").format(cellValue);
			return cdd;
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public String checkFile(HSSFWorkbook hssfWorkbook) throws DaoException {
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		for (int rowNum = 1; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
			int indexNum = rowNum + 1;
			System.out.println(indexNum + "==============================");
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow == null) {
				this.message = "第" + indexNum + "行无数据<br/>";
			}
			HSSFCell code = hssfRow.getCell((short) 0);// 房间编号

			if (code == null) {
				// this.message+="第"+indexNum+"行房间编码不能为空<br/>";
			} else {
				if (getValue(code).trim().equals("")) {
					// this.message+="第"+indexNum+"行房间编码不能为空<br/>";
				} else {
					List<House> houses = houseManager.loadByKey("code", getValue(code).trim());
					if (houses != null && houses.size() > 0) {
						this.message += "第" + indexNum + "行编码在系统中已经存在<br/>";
					}
				}
			}

			HSSFCell name = hssfRow.getCell((short) 1);// 房间名称
			if (name == null) {
				this.message += "第" + indexNum + "行房间名称不能为空<br/>";
			} else {
				if (getValue(name).trim().equals("")) {
					this.message += "第" + indexNum + "行房间名称不能为空<br/>";
				} else {
					List<House> houses  = houseManager.loadByKey("name", getValue(name).trim());
					if (houses != null && houses.size() > 0) {
						this.message += "第" + indexNum + "行房间房间名称在系统中已经存在<br/>";
					}
				}
			}

			HSSFCell khfl = hssfRow.getCell((short) 2);// 房间面积
			if (khfl == null) {
				this.message += "第" + indexNum + "行房间面积不能为空<br/>";
			} else {
				String ret = getValue(khfl).trim();
				if (!isNumeric(ret)) {
					this.message += "第" + indexNum + "行房间面积必须是数字<br/>";
				} else {
					if (Double.parseDouble(ret) < 0) {
						this.message += "第" + indexNum + "行房间面积必须是正数<br/>";
					}
				}
			}

			// HSSFCell qyxz = hssfRow.getCell((short)3);//房间的房间规格
			// if (qyxz == null) {
			// this.message+="第"+indexNum+"行房间规格不能为空<br/>";
			// }else{
			// String ret =getValue(qyxz).trim();
			// if(ret.equals("")){
			// this.message+="第"+indexNum+"行房间规格不能为空<br/>";
			// }
			// }

			HSSFCell sshy = hssfRow.getCell((short) 4);// 房间的单价
			if (sshy != null) {
				String ret = getValue(sshy).trim();
				if (!ret.equals("")) {
					if (!isNumeric(ret)) {
						this.message += "第" + indexNum + "行房间单价必须是数字<br/>";
					} else {
						if (Double.parseDouble(ret) < 0) {
							this.message += "第" + indexNum + "行房间单价必须是正数<br/>";
						}
					}
				}
			}

			HSSFCell khzt = hssfRow.getCell((short) 5);// 房间总价
			if (khzt == null) {
				// this.message+="第"+indexNum+"行房间销售价不能为空<br/>";
			} else {
				String ret = getValue(khzt).trim();
				if (ret.equals("")) {
					// this.message+="第"+indexNum+"行房间销售价不能为空<br/>";
				} else {
					if (!isNumeric(ret)) {
						this.message += "第" + indexNum + "行房间总价必须是数字<br/>";
					} else {
						if (Double.parseDouble(ret) < 0) {
							this.message += "第" + indexNum + "行房间总价必须是正数<br/>";
						}
					}
				}
			}
		}

		return this.message;
	}

	public void insertFile(HSSFWorkbook hssfWorkbook) throws DaoException {
		List<House> houseList = new ArrayList<House>();
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		for (int rowNum = 1; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
			System.out.println("excel第"+rowNum + "行==============================");
			House house = new House();
			house.setCode(null);
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			
			HSSFCell code = hssfRow.getCell((short) 0);// 房间编码
			if (code != null) {
				String ret_code = getValue(code).trim();
				house.setCode(ret_code);
				String [] temp = ret_code.split("-");
				List<Building> buildings = buildingManager.loadByKey("code", temp[0]);//设置大楼
				if(buildings!=null && buildings.size()>0){
					house.setBuilding(buildings.get(0));
				}
				List<Floor> floors = floorManager.loadByKey("code", temp[0]+"-"+temp[1]);//设置楼层
				if(floors!=null && floors.size()>0){
					house.setFloor(floors.get(0));
				}
			}
			
			
			HSSFCell name = hssfRow.getCell((short) 1);// 房间名称
			String ret_name = getValue(name).trim();
			house.setName(ret_name);
			
			HSSFCell khfl = hssfRow.getCell((short) 2);// 房间面积
			Double ret_khfl = khfl.getNumericCellValue();
			house.setSquare(Double.valueOf(format.format(ret_khfl)));
			
			HSSFCell qyxz = hssfRow.getCell((short) 3);// 房间单价
			if (qyxz != null) {
				Double ret_qyxz = qyxz.getNumericCellValue();
				house.setPrice(Double.valueOf(format.format(ret_qyxz)));
			}
			
			HSSFCell sshy = hssfRow.getCell((short) 4);// 房间总价
			if (sshy != null) {
				Double ret_sshy = sshy.getNumericCellValue();
				house.setTotal(Double.valueOf(format.format(ret_sshy)));
			}
			
			HSSFCell khzt = hssfRow.getCell((short) 10);// 房间类别
			if (khzt != null) {
				String ret_khzt = getValue(khzt).trim();
				if (ret_khzt != null && !ret_khzt.equals("")) {
					List<CodeValue> category = codeValueManager.loadByKey("name", ret_khzt);
					if(category != null && category.size()>0){
						house.setCategory(category.get(0));
					}
				}
			}
			
			HSSFCell khdj = hssfRow.getCell((short) 11);// 房间来源
			if (khdj != null) {
				String ret_khdj = getValue(khdj).trim();
				if (ret_khdj != null && !ret_khdj.equals("")) {
					List<CodeValue> source = codeValueManager.loadByKey("name", ret_khdj);
					if(source != null && source.size()>0){
						house.setSource(source.get(0));
					}
				}
			}

			HSSFCell zt = hssfRow.getCell((short) 13);// 状态
			if (zt != null) {
				String ret_zt = getValue(zt).trim();
				List<CodeValue> state = codeValueManager.loadByKey("name", ret_zt);
				if(state != null && state.size()>0){
					house.setState(state.get(0));
				}
			}
			
			HSSFCell zy = hssfRow.getCell((short) 14);// 是否主营
			if (zy != null) {
				String ret_zy = getValue(zy).trim();
				if("是".equals(ret_zy)){
					house.setIsMain(true);
				}else{
					house.setIsMain(false);
				}
			}

			HSSFCell cx = hssfRow.getCell((short) 15);// 朝向
			if (cx != null) {
				String ret_cx = getValue(cx).trim();
				List<CodeValue> orientation = codeValueManager.loadByKey("name", ret_cx);
				if(orientation != null && orientation.size()>0){
					house.setOrientation(orientation.get(0));
				}
			}
			
			HSSFCell cg = hssfRow.getCell((short) 16);// 层高
			if(cg!=null){
				String ret_cg = getValue(cg).trim();
				house.setHeight(Double.valueOf(ret_cg));
			}
			
			HSSFCell ktbs = hssfRow.getCell((short) 17);// 空调表数
			if(ktbs!=null){
				String ret_ktbs = getValue(ktbs).trim();
				if(!"".equals(ret_ktbs)){
					house.setAirNum(Integer.parseInt(ret_ktbs));
				}
			}
			
			HSSFCell wlfl = hssfRow.getCell((short) 18);// 网络费率
			if(wlfl!=null){
				String ret_wlfl = getValue(wlfl).trim();
				if(!"".equals(ret_wlfl)){
					house.setNetfee(Double.valueOf(ret_wlfl));
					house.setHasNetMeter(true);
				}
			}
			
			
			List<CodeValue> renovation = codeValueManager.loadByKey("name", "简装");
			if(renovation != null && renovation.size()>0){
				house.setRenovation(renovation.get(0));
			}
			
			HSSFCell zmbl = hssfRow.getCell((short) 22);// 照明电表倍率
			if(zmbl!=null){
				String ret_zmbl = getValue(zmbl).trim();
				List<ElectricMeter> eMeters = electricMeterManager.loadByKey("code", "zm-"+ret_zmbl);
				if(eMeters != null && eMeters.size()>0){
					house.setAeMeter(eMeters.get(0));
				}
			}
			
			HSSFCell dlbl = hssfRow.getCell((short) 23);// 动力电表倍率
			if(dlbl!=null){
				String ret_dlbl = getValue(dlbl).trim();
				List<ElectricMeter> eMeters  = electricMeterManager.loadByKey("code", "dl-"+ret_dlbl);
				if(eMeters != null && eMeters.size()>0){
					house.setBeMeter(eMeters.get(0));
				}
			}
			
			HSSFCell dflx = hssfRow.getCell((short) 24);// 电费类型
			if (dflx != null) {
				String ret_dflx = getValue(dflx).trim();
				List<CodeValue> eType = codeValueManager.loadByKey("name", (ret_dflx+"电"));
				if(eType != null && eType.size()>0){
					house.seteType(eType.get(0));
				}
			}
			
			HSSFCell wy = hssfRow.getCell((short) 25);// 是否有物业
			if (wy != null ) {
				String ret_wy = getValue(wy).trim();
				if("有".equals(ret_wy)){
					house.setHasProperty(true);
				}else{
					house.setHasProperty(false);
				}
			}
			
			houseList.add(house);
		}
		this.houseManager.saveAllHouse(houseList);
		this.message = "已经成功导入" + houseList.size() + "条房间信息";

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
