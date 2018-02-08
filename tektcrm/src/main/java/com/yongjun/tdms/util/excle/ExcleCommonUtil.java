package com.yongjun.tdms.util.excle;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import com.yongjun.pluto.util.DateUtil;


/** 
  * @author 创建人: xichunguang
  * @date 创建时间：2017年11月30日 下午2:33:14 
  * Excel辅助工具类   
  */
public class ExcleCommonUtil {
	    /**  
	     * 判断是否为老版本的excel  
	     * @Title: isExcel2003  
	     * @param filePath   *.xls  
	     * @return  
	     * boolean  
	     * @author wangqinghua   
	     * @date 2017-3-21 上午11:05:19  
	     */  
	    public static boolean isExcel2003(String filePath){  
	        return StringUtils.isNotEmpty(filePath) && filePath.matches("^.+\\.(?i)(xls)$");  
	    }  
	      
	    /**  
	     * 判断是否为新版本的excel  
	     * @Title: isExcel2007  
	     * @param filePath   *.xlsx  
	     * @return  
	     * boolean  
	     * @author wangqinghua   
	     * @date 2017-3-21 上午11:05:43  
	     */  
	    public static boolean isExcel2007(String filePath){  
	        return StringUtils.isNotEmpty(filePath) && filePath.matches("^.+\\.(?i)(xlsx)$");  
	    }  
	      
	    /**  
	     * 根据单元格获取内容  
	     * @Title: getCellFormatValue  
	     * @param cell  
	     * @return  
	     * String  
	     * @author wangqinghua   
	     * @date 2017-3-10 下午10:19:04  
	     */  
	    public static String getCellFormatValue(HSSFCell cell) {  
	        String result="";  
	        switch (cell.getCellType()) {  
	        case HSSFCell.CELL_TYPE_STRING:  
	            result = cell.getRichStringCellValue().getString();  
	            break;  
	        case HSSFCell.CELL_TYPE_NUMERIC:  
	            if(HSSFDateUtil.isCellDateFormatted(cell)){    
	                Date date = cell.getDateCellValue();   
	                result =  DateUtil.parseDate(date, "yyyy-MM-dd");  
	            }else{    
	                result = String.valueOf(new BigDecimal(cell.getNumericCellValue()));    
	            }  
	            break;  
	        case HSSFCell.CELL_TYPE_FORMULA:  
	            result = String.valueOf(cell.getNumericCellValue());  
	            break;  
	        default:  
	            result = "";  
	            break;  
	        }  
	        return result;  
	    }  
}
