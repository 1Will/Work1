package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class WriteFile {    //字节流写文件
	//File file =new File("D:"+File.separator+"_a_my"+File.separator+"shujuju2.txt");
	
	public void outputFile(String str,File file) throws IOException{
		OutputStream out =new FileOutputStream(file,true);
		byte [] b = str.getBytes();
		out.write(b);
		out.close();
	}
	
	public void  writeFile(String str,File file) throws IOException {    //字符流写文件
		Writer writer=new FileWriter(file,true);
		writer.write(str);
		writer.close();
	}
	
	public void writeFileb(String str,File file) throws IOException {   //按固定字符集的字节流写文件
		FileOutputStream f=new FileOutputStream(file,true);
		Writer writer=new OutputStreamWriter(f,"UTF-8");
		writer.write(str);
		writer.close();
	}
	
	
//	public static void main(String[] args) {
//		WriteFile w=new WriteFile();
//		try {
//			w.outputFile("我爱中国！\r\n");
//			w.outputFile("我爱中国！\r\n");
//			w.writeFile("我也爱中国！\r\n");
//			w.writeFile("我也爱中国！\r\n");
//			w.writeFileb("我也爱中国啦！\r\n");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}