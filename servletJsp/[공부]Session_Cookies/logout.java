package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout.do")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에 등록되어 있는 user라는 키를 삭제
		HttpSession session = request.getSession();
		session.removeAttribute("user");//vo.getUser를 NULL로 만들어 힙 정보를 지운다.
		
		RequestDispatcher disp = request.getRequestDispatcher("Session_Cookies.jsp");
	}

}
