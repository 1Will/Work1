package test;

import java.util.Calendar;

public class Testdate {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		int mon = cal.get(Calendar.MONTH)+1;
		int da =cal.get(Calendar.DAY_OF_MONTH);
		boolean guoqing = !((mon==10)&&(da<8));//国庆节为false
		System.out.println(guoqing);
	}

}
