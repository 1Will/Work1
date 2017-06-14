package test;

import java.io.File;
import java.text.DecimalFormat;

public class SpaceChecker {
	public static void main(String[] args) {
		DecimalFormat df =new DecimalFormat("#.0");  
		File[] roots = File.listRoots();
		for (File file : roots) {
			Long total =file.getTotalSpace();//总容量
			Long free = file.getFreeSpace();//空闲容量
			Long used = file.getTotalSpace() - file.getFreeSpace();//剩余容量
			double usable = (double)used*100/(double)total;//已用百分比
			
			System.out.println("Free space = " + free);
			System.out.println("Total space = " + total);
			System.out.println("used space  = " + used);
			System.out.println(file.getPath()+"硬盘已用"+df.format(usable)+"%");
			System.out.println();
		}
	}
}
