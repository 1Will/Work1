package test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		Set<Long> a =new HashSet<>();
		a.add(1L);
		a.add(2L);
		a.add(3L);
		a.add(1L);
		System.out.println(a.toString());
	}

}
