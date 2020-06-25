package UI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cards {
	int[][] cards = new int[5][2];
	int type = 0;
	public static Map<Character,Integer> mp = new HashMap<Character,Integer>(){{
		put('T', 10);put('J', 11);put('Q', 12);
		put('K', 13);put('A', 14);
		
		put('D', 0);put('S', 1);
		put('H', 2);put('C', 3);
	}};
	/*
	 2H 3D 5S 9C KD
	2C 3H 4S 8C AH
	*/
	public Cards(String s) {
		int index = 0;
		int t = 0;
		int[] cnt = new int[15];
		while(index < s.length()) {
			char ch = s.charAt(index);
			if(ch != ' ') {
				if(Character.isDigit(ch)) {
					cards[t][0] = (int)(ch-'0');
				}else {
					cards[t][0] = mp.get(ch);
				}
				index++;
				cards[t][1] = mp.get(s.charAt(index));
				cnt[cards[t][0]] ++;
				index ++;
				t ++;
			}else
				index ++;	
				
		}
		Arrays.sort(cards,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				
				if(cnt[o1[0]] > cnt[o2[0]])
					return -1;
				else if(cnt[o1[0]] < cnt[o2[0]])
					return 1;
				if(o1[0] > o2[0])
					return -1;
				else if(o1[0] < o2[0])
					return 1;
				return 0;
			}
			
		});
		boolean a = true;
		boolean b = true;
		for(int i = 1; i < 5; i ++) {
			if(cards[i][0] != cards[i-1][0]-1)
				a = false;
			if(cards[i][1] != cards[i-1][1])
				b = false;
		}
		if(a==true && b==true) {
			type = 6;
			return;
		}else if(a==true) {
			type = 4;
			return;
		}else if(b == true) {
			type = 5;
			return;
		}
		index = 1;
		t = 0;
		int len = 1;
		int maxlen = 1;
		while(index < 5) {
			if(cards[index][0] == cards[index-1][0]){
				len ++;
			}else
				len = 1;
			maxlen = Math.max(len, maxlen);
			if(len == 2)
				t ++;
			index ++;
		}
		
		if(maxlen >= 3) {
			type = 3;
			if(cards[3][0] < cards[4][0]) {
				int tmp = cards[3][0];
				cards[3][0] = cards[4][0];
				cards[4][0] = tmp;
			}
		}else if(maxlen == 2) {
			type = 1;
			if(t == 2)
				type = 2;
		}
	}
	
	public static int compare(Cards a, Cards b) {
		if(a.type > b.type)
			return 1;
		else if(a.type < b.type)
			return -1;
		for(int i = 0; i < 5; i ++) {
			if(a.cards[i][0] > b.cards[i][0])
				return 1;
			else if(a.cards[i][0] < b.cards[i][0])
				return -1;
		}
		return 0;
	}
}
