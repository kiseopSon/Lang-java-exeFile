package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import vo.ProductVO;

@WebServlet("/shop/list.do")
public class ProductListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//list.do?category=com001 여기서 실행하면 처음에 아무것도 없음.
		String category = request.getParameter("category");
		
		if(category == null || category.isEmpty()) {
			category = "com001";//첫 실행시 반드시 컴퓨터 카테고리에서 시작
		}
		
		List<ProductVO> list = ProductDAO.getInstance().select(category);
		request.setAttribute("list", list);
		
		RequestDispatcher disp = request.getRequestDispatcher("product_list.jsp");
		disp.forward(request, response);
	}

}
