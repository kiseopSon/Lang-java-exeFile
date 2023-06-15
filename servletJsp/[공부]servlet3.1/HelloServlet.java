package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello.do")//중복되면 절대 안됨. + 대소문자 가림. 클래스복붙금지 바꿔도 안됨 초기상태를 기준으로 함.
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * 서블릿(server + let): 서버에서 실행되는 사용자의 요구사항을 제공해주는 클래스
	 * - 웹 응용프로그램을 만드는 자바기술이며 실행 결과값을 html형식으로 돌려준다.
	 * - html의 정적인 문제를 해결할 수 있는 동적인 특징을 갖는다.
	 * - 자바 언어로 구성되어 있어 자바의 일반적인 특징을 모두 가지고 있다.
	 * - 자동으로 스레딩처리가 되어, 동시다발적인 사용자의 요청에 대한 응답이 용이하다.
	 * 서블릿은 새로고침이 안되고 서버를 껏다 다시 켜야함. 
	 * */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//웹프로그래밍 에서는 request(요청 처리객체)와, response(응답처리 객체)가 매우매우 중요하다.
		//요청자 (클라이언트)의 ip를 획득
		String ip = request.getRemoteAddr();//해당 매개변수는 String타입으로 전송받는다.

		System.out.println(ip+"이 입장했습니다.");
		//접속한 사용자에게 html을 통해 응답처리
		//응답시 한글꺠짐 방지
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String para_name = request.getParameter("para_name");
		String msg ="";//초기값이 안나오는 라디오 버튼의 경우 방지
		
		//1) hello.do?para_name = : para_name이 ""인 상태
		//2) hello.do? : para_name이 null인 상태
		if(para_name == null || para_name.isEmpty()) {// = para_name.equals("");
			para_name = "kor";
		}
		if(para_name.equals("kor")) {
			msg = "안녕하세요";
		}
		else if(para_name.equals("eng")) {
			msg = "hello";
		}
		else if(para_name.equals("jpn")) {
			msg = "おはようございます";
		}
		else if(para_name.equals("chn")) {
			msg = "问候语";
		}

		out.printf("[%s]님 방문을 환영합니다.",ip);
		out.println("<html>");
		
		out.println("<head>");
		out.println("<title>첫 서블릿 예제: 나라별 인사말 </title>");
		out.println("<style>h1{color:red;} </style>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h1>"+ip+"님 환영합니다</h1>");
		out.println("<h1>"+msg+"</h1>");
		out.println("</body>");
		
		out.println("</html>");
	}

}
