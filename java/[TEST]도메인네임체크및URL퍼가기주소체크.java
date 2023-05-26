import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
//str중에 메인도메인주소가 특정사이트의 주소를 가르키는 도메인네임이 아니면 결과를 알려주는 함수임
public class youtubeURL퍼가기 {
	public static void main(String[] args) {
		//str안에 어떻게든 전체 링크가 들어오게 하기만 하면되고, http, .com/watch?v= 반드시 이 형태가 갖추어야함.
		//네이버 v앱이나 특정 사이트들은 url퍼가기 링크형태가 달라서 아예 새로 틀을 맞춰줘야하는 상황이 있었음. - 2023.05.26
		String str = "https://www.youtube.com/watch?v=YS10Cdaz2Kk&t=3s";//특정 url동영상 주소
		System.out.println(str.contains("&t="));
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < str.length()-1; i++) {
			String ch = ""+str.charAt(i);
			if(ch.equals(".")) {
				list.add(i);
			}
		}
		String eq = str.substring(list.get(0)+1,list.get(1)); //10번 String str부터 18번 String eq까지는 공통 소스.
		if(eq.equals("youtube") || eq.equals("YOUTUBE")) {
			int vCheck = str.indexOf("=");//=첫번째 인덱스
			int endCheck = str.lastIndexOf("&");//마지막째 인덱스
			str = "https://www.youtube.com/embed/"+str.substring(vCheck+1,endCheck);
			//여기서 str로 결과 보내주면 됨.
			System.out.println(str);
		}
		else {
			//youtube가 아니면 뭔지 확인 하는 용도.
			System.out.println(eq);
		}
	}//MAIN
}
