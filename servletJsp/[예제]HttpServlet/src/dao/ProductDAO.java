package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.ProductVO;

public class ProductDAO {
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static ProductDAO single = null;

	public static ProductDAO getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new ProductDAO();
		//생성된 객체정보를 반환
		return single;
	}
	
	SqlSessionFactory factory;
	public ProductDAO() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//카테고리별 상품 조회
	public List<ProductVO> select(String category){
		List<ProductVO> list = null;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("pro.product_list",category);
		sqlSession.close();
		return list;
	}
	
	public int insert(ProductVO vo) {
		SqlSession sqlSession = factory.openSession(true);//오토 커밋
		int cnt = sqlSession.insert("pro.product_insert",vo);
//		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}
	
	//상품 상세보기 : 한개만 검색할떈 vo가 더 쉽고 빠름.
	public ProductVO selectOne(int idx) {
		ProductVO vo = null;
		SqlSession sqlSession = factory.openSession();
		vo = sqlSession.selectOne("pro.product_one",idx);
		sqlSession.close();
		return vo;
	}
}
