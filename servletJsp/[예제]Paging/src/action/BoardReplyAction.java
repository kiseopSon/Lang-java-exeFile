package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

@WebServlet("/board/reply.do")
public class BoardReplyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//idx를 사용하여 게시물 정보 얻기 ==ref몇이냐? 모르니깐 다가져와바
		BoardVO baseVO = dao.selectOne(idx);
		
		//기준글의 step보다 큰 값은 모두 step = step+1자리
		dao.update_step(baseVO);
		
		//달고자 하는 댓글을 vo에 포장
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setIp(ip);
		
		vo.setRef(baseVO.getRef());
		vo.setStep(baseVO.getStep()+1);
		vo.setDepth(baseVO.getDepth() +1);
		
		//댓글 등록!
		dao.reply(vo);
		
		String page = request.getParameter("page");
		response.sendRedirect("list.do?page=" + page);
	}

}
