package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Change3 {

	public static void main(String[] args) throws IOException {
		FileReader fr;
		File file =new File("D:"+File.separator+"_a_my"+File.separator+"shujuju2.txt");
		Writer writer=new FileWriter(file,true);
		try {
			fr = new FileReader("D:"+File.separator+"_a_my"+File.separator+"shujuju.txt");
			BufferedReader bf = new BufferedReader(fr);
			String a = null;
			while ((a=bf.readLine())!=null){
			//System.out.println(a);
			String data[] = a.split(", CAST\\(");
			String need[] =a.split("\\$");
			//System.out.println(data[1]);
			String check[] =data[1].split(",");
			System.out.println(check.length);
			if(check.length==25){
			System.out.println(data[1]);
			//if(data.length==7){
				//String str =need[0]+data[2].split("AS")[0] + need[1]+ need[2].split("AS")[0]+";"+"\r\n";
				//System.out.println(str);
				//writer.write(str);
			//}
			}
			}
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
