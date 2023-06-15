package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

@WebServlet("/board/del.do")
public class BoardDelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//삭제를 위한 게시글의 idx와 pwd
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pwd = request.getParameter("pwd");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardVO baseVO = dao.selectOne(idx, pwd);
		
		String resultStr="";
		String result = "no";
		if(baseVO == null) {
			resultStr = String.format("[{'result':'%s'}]", result);
			response.getWriter().println(resultStr);
			return;
		}
		
		//찾아온 게시글의 정보를 수정
		baseVO.setSubject("삭제된 게시글 입니다.");
		baseVO.setName("known");
		
		int res = dao.del_update(baseVO);
		if(res == 1) {
			result = "yes";
		}
		resultStr = String.format("[{'result':'%s'}]", result);
		response.getWriter().println(resultStr);
	}

}
