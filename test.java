package UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class test {
	public static Map<Integer,Character> mp = new HashMap<Integer,Character>(){{
		put(10,'T');put(11,'J');put(12,'Q');
		put(13 , 'K');put(14 , 'A');
		
		put(0 , 'D');put(1 , 'S');
		put(2 , 'H');put(3 , 'C');
	}};
	public static void testByUser() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Black:");
		String s = sc.nextLine();
		System.out.print("White:");
		String t = sc.nextLine();
		Cards a = new Cards(s);
		Cards b = new Cards(t);
		int ans = Cards.compare(a, b);
		if(ans == 1) {
			System.out.println("Black wins");
		}else if(ans == -1) {
			System.out.println("White wins");
		}else
			System.out.println("Tie");
		sc.close();
	}
	public static String help() {
		Random rand = new Random();
		char[] tmp = {'1','2','3','4','5','6','7','8','9'};
		char[] s1 = new char[14];
		int index = 0;
		while(index < 14) {
			int t = rand.nextInt(14)+1;
			if(t < 10)
				s1[index] = tmp[t-1];
			else
				s1[index] = mp.get(t);
			t = rand.nextInt(4);
			s1[index+1] = mp.get(t);
			if(index+2 < 14)
				s1[index+2] = ' ';
			index += 3;
		}
		return new String(s1);
	}
	public static void testByComputer() {
		for(int i = 0; i < 1000; i ++) {
			String s1 = help();
			String s2 = help();
			Cards a = new Cards(s1);
			Cards b = new Cards(s2);
			System.out.print("Black: " + s1 + "  White: " + s2 + "  Winner:");
			int ans = Cards.compare(a, b);
			if(ans == 1) {
				System.out.println("Black wins");
			}else if(ans == -1) {
				System.out.println("White wins");
			}else
				System.out.println("Tie");
				
		}
	}
	public static void main(String[] args) {
		//testByUser();
		testByComputer();
	}

}
