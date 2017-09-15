package test;

public class StaticTest {

	public static int a = 0;
	public static String b = a+""; 
	public static void main(String args[]){
		for(int i=0;i<=10;i++){
			System.out.println("a= "+a);
			System.out.println("b= "+b);
			a++;
		}
	}
}
