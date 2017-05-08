package excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DealExcel {

	public static void main(String[] args) {
		File file = new File("");
		FileReader configFile = null;
		WriteFile w=new WriteFile();
		try {
			configFile = new FileReader(file.getAbsolutePath() + File.separator + "config.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bf = new BufferedReader(configFile);
		String config1 = null;
		String config2 = null;
		try {
			config1 = bf.readLine().split("#")[1];
			config2 = bf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File excelFile = new File(file.getAbsolutePath() + File.separator + config1);
		File sqlFile = new File(file.getAbsolutePath() + File.separator + "sql.txt");
		ArrayList<ArrayList<String>> data = new ReadExcel().read(excelFile);
		String deal[]= config2.split("#");
		String word =deal[1];
		for(int i=0;i<data.get(0).size();i++){
			if(i==(data.get(0).size()-1)){
				word +=data.get(0).get(i);
			}else{
				word +=data.get(0).get(i)+",";
			}
		}
		word+=deal[2];
		for (int i = 1; i < data.size(); i++) {
			String str=word;
			for (int j = 0; j < data.get(i).size(); j++) {
				if(j==(data.get(i).size()-1)){
					str +="'"+ data.get(i).get(j)+"'";
				}else{
					str +="'"+data.get(i).get(j)+"'"+",";
				}
			}
				str +=deal[3]+"\r\n";
				try {
					w.writeFile(str, sqlFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//System.out.println(str);
		}
		System.out.println("完成！");
	}
}
