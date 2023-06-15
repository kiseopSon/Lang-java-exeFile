package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Paging;
import dao.BoardDAO;
import vo.BoardVO;


@WebServlet("/board/list.do")
public class BoardListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list.do?page=1
		int nowPage = 1;
		String page = request.getParameter("page");
		if(page  != null && !page.isEmpty()) {
			//정상적동 이라면
			nowPage = Integer.parseInt(page);
		}
		//한 페이지에 표시되는 게시물의 시작과 끝번호를 계산
		int start = (nowPage -1) * Common.Board.BLOCKLIST +1;
		int end = start + Common.Board.BLOCKLIST -1 ;
		
		//start 와 end를 맵에 저장 그래야 db에 동적할당이 되니깐
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start",start);
		map.put("end", end);
		
		//게시글 전체목록 가져오기
		
		List<BoardVO> list = null;
		BoardDAO dao = BoardDAO.getInstance();
		list = dao.selectList(map);
		
		//전체 게시물 수 구하기
		int row_total = dao.getRowTotal();
		
		//paging 클래스를 사용하여 페이지 메뉴 생성하기  =  < 1 2 3 > 만들어줌.
		String pageMenu = Paging.getPaging("list.do", nowPage, row_total, Common.Board.BLOCKLIST, Common.Board.BLOCKPAGE);
		
		request.setAttribute("list", list);
		request.setAttribute("pageMenu", pageMenu);
		
		//세션에 기록된 show정보 삭제
		request.getSession().removeAttribute("show");
		
		RequestDispatcher disp = request.getRequestDispatcher("board_list.jsp");
		disp.forward(request, response);
	}

}
