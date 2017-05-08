package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFile {
	//File file =new File("D:"+File.separator+"_a_my"+File.separator+"shujuju.txt");
	
	public byte [] inputFile(File file) throws IOException {    //字节流读取数据
		byte b[]=new byte[1024];
		InputStream in =new FileInputStream(file);
		in.read(b);  //读取内容，并写入b中，返回读取数据个数！
		in.close();
		return b;
	}
	
	public char[] readFile(File file) throws IOException {    //字符流读取数据
		char cbuf[] = new char[1024];
		Reader reader =new FileReader(file);
		reader.read(cbuf);
		reader.close();
		return cbuf;
	}
	
	public char[] inputFilec(File file) throws IOException {    //字符流按编码读取数据
		char cbuf[] = new char[1024];
		InputStream in =new FileInputStream(file);
		Reader reader =new InputStreamReader(in,"utf-8");
		reader.read(cbuf);
		reader.close();
		return cbuf;
	}
	
//	public static void main(String[] args) throws IOException {
//		ReadFile r=new ReadFile();
//		byte b[] = r.inputFile();
//		char c[] = r.readFile();
//		char cc[] = r.inputFilec();
//		System.out.println(new String(b,0,b.length));
//		System.out.println(new String(c,0,c.length));
//		System.out.println(new String(cc,0,cc.length));
//	}
}
