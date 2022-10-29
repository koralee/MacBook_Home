package study;

import java.util.*;

public class Code01_369game {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("369게임");
		System.out.println("1~99의 숫자를 입력하시오");
		int num = sc.nextInt();
		
		if(num>0&& num<100) {
			System.out.println(num);
			
				if(num>=30 && (num-(num%10))%30==0) { 
				System.out.print("박수 짝!");
						if(num%10!=0 && num%3==0)
						System.out.println("짝!");
				}
			
				else if(num%10!=0 && (num%10)%3==0)
					System.out.println("박수 짝!");
				else
					System.out.println("박수X");
		}
		else
			System.out.println("범위를 잘못 입력하였습니다.");
	}

}
