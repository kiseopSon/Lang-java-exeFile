package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDAO;
import vo.CartVO;

/**
 * Servlet implementation class CartDelAction
 */
@WebServlet("/shop/cart_delete.do")
public class CartDelAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int c_idx = Integer.parseInt(request.getParameter("c_idx"));
		int m_idx = 1;
		
		//장바구니에서 항목삭제
		CartDAO.getInstance().delete(c_idx);
		
		List<CartVO> list= CartDAO.getInstance().select(m_idx);//재조회
		int total_amount = CartDAO.getInstance().selectTotalAmount(m_idx);//총계도 다시
		
		request.setAttribute("list", list);
		request.setAttribute("total_amount", total_amount);
		
		RequestDispatcher disp = request.getRequestDispatcher("cartList.jsp");
		disp.forward(request, response);
	}

}
