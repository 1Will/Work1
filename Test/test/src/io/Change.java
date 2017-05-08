package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Change {

	public static void main(String[] args) throws IOException {
		FileReader fr;
		File file =new File("D:"+File.separator+"_a_my"+File.separator+"shujuju2.txt");
		Writer writer=new FileWriter(file,true);
		try {
			fr = new FileReader("D:"+File.separator+"_a_my"+File.separator+"shujuju.txt");
			BufferedReader bf = new BufferedReader(fr);
			String a = null;
			int i=0;
			int j=0;
			while ((a=bf.readLine())!=null){
			//System.out.println(a);
			String data[] = a.split(", CAST\\(");
			String need[] =a.split("\\$");
			//System.out.println(data.length);
			if(data.length==7){
				String str =need[0]+data[2].split("AS")[0] + need[1]+ need[2].split("AS")[0]+";"+"\r\n";
				System.out.println(str);
				writer.write(str);
				i++;
			}
			j++;
			}
			writer.close();
			System.out.println("行数:"+i);
			System.out.println("总行数:"+j);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
