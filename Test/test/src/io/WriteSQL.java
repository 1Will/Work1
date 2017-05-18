package io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WriteSQL {

	public static void main(String[] args) throws IOException {
		WriteFile wFile =new WriteFile();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.set(1980,1,1);
		File file =new File("D:"+File.separator+"_a_my"+File.separator+"insert.txt");
		
		for(int i=0;i<=750;i++){
			cal.add(Calendar.DATE, 1);
			Date date=cal.getTime();
			String str ="update [crm2016_20170120].[dbo].[t_contactArchives] set BIRTH_DATE= '"+formatter.format(date)+"' where id = "+i+"\r\n";
			wFile.writeFile(str, file);
		}
		
	}

}
