import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class 은행계좌확인 {
	public static void main(String[] args) {
	
		/*
		 * xxxx - xxxx - xxxx - xxxx (0~9 정수) xxxxxxxxxxxxxxxx 이런식도 있음.
		 * 
		 * 가장 오른쪽 부터 짝수 번째 자리 숫자들에 2를 곱한후 모두 더한다. 이것을 (1)이라고 하고, 만약 2를 곱한 수가 두자릿수면 둘의 값을
		 * 자릿수끼리 값을 더해 한자리수로 만든다. 가장 오른쪽 부터 홀수번째 있는 숫자를 모두 더하고 이를 (2)라고 한다. (1)+(2) 는
		 * 10으로 나누어 떨어지면 유효한 번호다. 그외는 모두 올바르지 않은 신용 카드 번호이다. 신용카드 번호들이 담긴 문자열 배열
		 * card_numbers가 주어질때 각 카드 번호가 올바르면 1, 다르면 0으로 표기하여 배열을 return 하도록 함수를 작성하라.
		 * 
		 * 
		 * card_numbers 길이는 1~10이하 , String타입. 전부 숫자로 구성된 값들이고, 각 문자열 길이는 1~20이하 - 포함이라서
		 * 20이하 이다. 1에서 16까지값 값은 3542-4399-4673-4823 4 9 14 은 -자리.
		 */

		String[] card_number = { "3542-4399-4673-4833", "3285376499342453", "1111111111111111" };

		clear cl = new clear();
		String[] clear = cl.card_number(card_number);
		// 여기서 필터 만들어서 데이터 넣어서 받는값은 거기서 인트배열로 true/false로 반환하면 끝.

		String[] result = cl.Luhn(clear);
		System.out.println("결과 : {" + Arrays.toString(result) + "}");
	}
}

class clear {
	// 정리
	public String[] card_number(String[] card_number) {
		String[] card = {};
		card = new String[card_number.length];
		for (int i = 0; i <= card_number.length - 1; i++)
			card[i] = card_number[i];

		String test = "";// 기본 데이터
		String deldata = "";// 깨끗해진 데이터

		for (int i = 0; i <= card.length - 1; i++) {
			test = card[i];
			char test2 = test.charAt(4);
			if (test2 == '-') {
				for (int j = 0; j <= 18; j++) {
					if (!(j == 4 || j == 9 || j == 14))
						deldata += test.charAt(j);
				}
				System.out.println("-가 들어간 값 : " + deldata);
				card[i] = deldata;
			} else {
				System.out.println("-가 안들어간 값  : " + test);
				card[i] = test;
			}
		}

		return card;
	}

	// 필터
	public String[] Luhn(String[] number) {
		System.out.println("데이터가 제대로 필터에 들어왔는지 : " + Arrays.toString(number));
		String[] result = new String[number.length];
		int[] num = new int[number[0].length()];// 문자열 길이 기준.
		int[][] num2 = new int[result.length][num.length];// [i][j] i에 값이 다 들어있음.
		int plus = 0;// 짝수
		int plus2 = 0;// 홀수

		// 전체 데이터에서 ~
		for (int i = 0; i <= result.length - 1; i++) {
			// 각각의 숫자 위치를 String 배열에서 int 배열로 변환.
			System.out.print((i + 1) + "번째 : ");
			for (int j = 0; j <= num.length - 1; j++) {
				num2[i][j] = number[i].charAt(j) - '0';
				if (j % 2 == 1) {// 짝수
					// System.out.print(num2[i][j]);
					int count = (num2[i][j] * 2);
					String count2 = "" + (num2[i][j] * 2);

					if (count2.length() == 2) {
						int a = count2.charAt(0) - '0';// 10의자리
						int b = count2.charAt(1) - '0';// 1의자리
						plus += a + b;
					} else
						plus += count;

					// 11/28 현재 여기서 막힘 .각각 result 갯수만큼만 결과가 나와야하는데 정수선언를 재활용 하기 어려움.
				}
				if (j % 2 == 0) {// 홀수
					// System.out.print(num2[i][j]);
					int count = (num2[i][j] * 2);
					String count2 = "" + (num2[i][j] * 2);

					if (count2.length() == 2) {
						int a = count2.charAt(0) - '0';// 10의자리
						int b = count2.charAt(1) - '0';// 1의자리
						plus2 += a + b;
					} else
						plus2 += count;
				}
			}
			System.out.println();
			System.out.println("짝 : " + plus);
			System.out.println("홀 : " + plus2);
			System.out.println("나누어 떨어 지는가? : " + ((plus + plus2) / 100));// 내가 만든 조건 : 단, 100으로 나눌 때 나눠지면 1, 나머진 0으로
																			// 처리.
		}

		return result;// 소수점밑으로 떨어져서 결과값이 맞지않음. 문제가 이상함.
	}
}