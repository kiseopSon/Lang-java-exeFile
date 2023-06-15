package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import vo.MemberVO;

@WebServlet("/login.do")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO user = MemberDAO.getInstance().selectOne(id);//기본 getterVO에 dao.selectOne함수를 통해 vo에 데이터를 매핑시켜준다.
		//해당 파일을 지워놓았다, 필요하면 테스트용으로 하나 만들어두길 권장한다.
		
		String param = "";
		String result = "";
		
		//로그인 하려는 id가 db에 존재하지 않거나 틀린 경우.
		if(user == null) {
			param = "no_id";
			result = String.format("[{'param':'%s'}]", param);
			response.getWriter().println(result);
			return;
		}
		//비밀번호 매칭 실패시
		if(!(user.getPwd().equals(pwd))) {
			param = "no_pwd";
			result = String.format("[{'param':'%s'}]", param);
			response.getWriter().println(result);
			return;
		}
		//user객체를 세션에 저장한다.
		//단, 세션은 서버의 메모리 램을 사용하기 떄문에 많이 쓸 수록 브라우저를 느리게 만들고, 비용이 상승한다.
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		//세션의 기본유지시간은 30분이다. 이것을 1시간으로 바꾼다.
		session.setMaxInactiveInterval(60*60);
		
		param="clear";
		result = String.format("[{'param':'%s'}]", param);
		response.getWriter().println(result);//서블릿
	}

}
