public class 문제더하기 {
	public static void main(String[] args) {
		//42분걸림. 01.14.2022
		//관건 보여질 부분과 계산될 부분을 따로 구분해야할것!
		int[] st = {1,2,3,4,5};
		
		String result = ""; 
		int plus = 0;
		for (int i = 0; i < st.length; i++) {
			if(i == 0) {
				result += st[i];
				System.out.println(result + "=" + st[i]);
				plus = st[i];
			} else {
				result += "+" + st[i];
				plus += st[i];
				System.out.println(result + "=" + plus);
			}
		}
	}
}
