package com.yongjun.tdms.util.excle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/** 
  * @author 创建人: xichunguang
  * @date 创建时间：2017年12月1日 上午9:31:28 
  * 类说明 
  */
public class ImportExcle {
	@SuppressWarnings("unused")
	private List<List<Object>> ExceltoList(File file)  
            throws IOException {  
        List<List<Object>> list = new LinkedList<List<Object>>();  
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = hwb.getSheetAt(0);  
        Object value = null;  
        HSSFRow row = null;  
        HSSFCell cell = null;  
        System.out.println("读取office 2003 excel内容如下：");  
        for (int i = sheet.getFirstRowNum(); i <= sheet  
                .getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);  
            if (row == null) {  
                continue;  
            }  
            List<Object> linked = new LinkedList<Object>();  
            for (short j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
                cell = row.getCell(j);  
                if (cell == null) {  
                    continue;  
                }  
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String  
                // 字符  
                SimpleDateFormat sdf = new SimpleDateFormat(  
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串  
                DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字  
                switch (cell.getCellType()) {  
                case HSSFCell.CELL_TYPE_STRING:  
                    // System.out.println(i + "行" + j + " 列 is String type");  
                    value = cell.getStringCellValue();  
                    System.out.print("  " + value + "  ");  
                    break;  
                case HSSFCell.CELL_TYPE_NUMERIC:  
                    // System.out.println(i + "行" + j  
                    // + " 列 is Number type ; DateFormt:"  
                    // + cell.getCellStyle().getDataFormatString());  
                    if ("@".equals(cell.getCellStyle().getDataFormat())) {  
                        value = df.format(cell.getNumericCellValue());  
  
                    } else if ("General".equals(cell.getCellStyle()  
                            .getDataFormat())) {  
                        value = nf.format(cell.getNumericCellValue());  
                    } else {  
                        value = sdf.format(HSSFDateUtil.getJavaDate(cell  
                                .getNumericCellValue()));  
                    }  
                    System.out.print("  " + value + "  ");  
                    break;  
                case HSSFCell.CELL_TYPE_BOOLEAN:  
                    // System.out.println(i + "行" + j + " 列 is Boolean type");  
                    value = cell.getBooleanCellValue();  
                    System.out.print("  " + value + "  ");  
                    break;  
                case HSSFCell.CELL_TYPE_BLANK:  
                    // System.out.println(i + "行" + j + " 列 is Blank type");  
                    value = "";  
                    System.out.print("  " + value + "  ");  
                    break;  
                default:  
                    // System.out.println(i + "行" + j + " 列 is default type");  
                    value = cell.toString();  
                    System.out.print("  " + value + "  ");  
                }  
                if (value == null || "".equals(value)) {  
                    continue;  
                }  
                linked.add(value);  
  
            }  
            System.out.println("");  
            list.add(linked);  
        }  
  
        return list;  
    }
}
