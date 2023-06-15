package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.CartVO;

public class CartDAO {
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static CartDAO single = null;

	public static CartDAO getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new CartDAO();
		//생성된 객체정보를 반환
		return single;
	}
	
	SqlSessionFactory factory;
	
	public CartDAO() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//회원별 장바구니 목록
	public List<CartVO> select(int m_idx){
		List<CartVO> list = null;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("c.cart_list", m_idx);
		sqlSession.close();
		return list;
 	}
	//회원별 장바구니 상품의 총 합
	public int selectTotalAmount(int m_idx) {
		SqlSession sqlSession = factory.openSession();
		int total = sqlSession.selectOne("c.cart_total_amount", m_idx);
		sqlSession.close();
		return total;
	}
	//상품갯수, 상품번호, 유저번호를 파라미터로 받아서 update
	public int update_cnt(int c_idx, int c_cnt){
		SqlSession sqlSession = factory.openSession(true);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("c_idx", c_idx);//1개씩 보내야 가능.
		map.put("c_cnt", c_cnt);
		
		int res = sqlSession.update("c.cart_cnt_update",map);//보낼땐 통째로
		sqlSession.close();
		return res;
	}
	
	//장바구니에서 상품 삭제
	public int delete(int c_idx) {
		SqlSession sqlSession = factory.openSession(true);
		int res = sqlSession.delete("c.cart_cnt_delete",c_idx);
		sqlSession.close();
		return res;
	}
	
	//이미 장바구니에 등록된 상품이 있는지 확인
	public CartVO selectOne(CartVO vo) {
		CartVO resVO = null;
		SqlSession sqlsession = factory.openSession();
		resVO = sqlsession.selectOne("c.cart_one",vo);
		sqlsession.close();
		return resVO;
	}
	
	//장바구니에 상품 등록하기
	public int insert(CartVO vo) {
		SqlSession sqlSession = factory.openSession(true);
		int res = sqlSession.insert("c.cart_insert",vo);
		sqlSession.close();
		return res;
	}
}
