package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author dy
 * @since 2016-09-18 & JDK 1.8.0_91
 */
public class SplitDate {
	/**
	 * 切割时间段
	 *
	 * @param dateType
	 *            交易类型 M/D/H/N -->每月/每天/每小时/每分钟
	 * @param start
	 *            yyyy-MM-dd HH:mm:ss
	 * @param end
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static List<String> cutDate(String dateType, String start, String end) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dBegin = sdf.parse(start);
			Date dEnd = sdf.parse(end);
			return findDates(dateType, dBegin, dEnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> findDates(String dateType, Date dBegin, Date dEnd) throws Exception {
		List<String> listDate = new ArrayList<>();
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (calEnd.after(calBegin)) {
			switch (dateType) {
			case "M":
				calBegin.add(Calendar.MONTH, 1);
				break;
			case "D":
				calBegin.add(Calendar.DAY_OF_YEAR, 1);
				break;
			case "H":
				calBegin.add(Calendar.HOUR, 1);
				break;
			case "N":
				calBegin.add(Calendar.SECOND, 1);
				break;
			}
			if (calEnd.after(calBegin))
				listDate.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calBegin.getTime()));
			else
				listDate.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calEnd.getTime()));
		}
		return listDate;
	}

	public static void main(String[] args) {
		String start = "2016-02-01 00:00:00";
		String end = "2016-09-02 00:00:00";
		List<String> list = cutDate("M", start, end);
		for (String str : list) {
			System.out.println(str);
		}
	}
}
