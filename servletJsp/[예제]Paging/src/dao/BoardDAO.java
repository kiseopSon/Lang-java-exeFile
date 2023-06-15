package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.BoardVO;

public class BoardDAO {
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static BoardDAO single = null;

	public static BoardDAO getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new BoardDAO();
		//생성된 객체정보를 반환
		return single;
	}
	SqlSessionFactory factory;
	
	public BoardDAO() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//전체 게시글 조회
	public List<BoardVO> selectList(){
		List<BoardVO> list = null;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("b.board_list");
		sqlSession.close();
		return list;
	}
	
	//페이징을 포함한 검색
	public List<BoardVO> selectList(Map<String, Integer> map){
		List<BoardVO> list = null;
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("b.board_list_condition",map);
		sqlSession.close();
		return list;
	}
	
	//게시글 추가
	public int insert(BoardVO vo) {
		SqlSession sqlSession = factory.openSession(true);
		int res = sqlSession.insert("b.board_insert",vo);
		sqlSession.close();
		return res;
	}
	
	//idx에 해당하는 게시글 한 건 얻어오기
	public BoardVO selectOne(int idx) {
		BoardVO vo = null;
		SqlSession sqlSession = factory.openSession();
		vo = sqlSession.selectOne("b.board_one",idx);
		sqlSession.close();
		return vo;
	}
	
	//글 삭제를 위한 게시글의 정보 가져오기
	public BoardVO selectOne(int idx, String pwd) {
		BoardVO vo = null;
		SqlSession sqlSession = factory.openSession();
		HashMap<String,Object> map = new HashMap<>();
		map.put("idx", idx);
		map.put("pwd", pwd);
		vo = sqlSession.selectOne("b.board_idx_pwd",map);
		sqlSession.close();
		return vo;
	}
	
	//게시글 삭제(된것 처럼) 업데이트
	public int del_update(BoardVO vo) {
		SqlSession sqlSession = factory.openSession(true);
		int res = sqlSession.update("b.board_del_update",vo);
		sqlSession.close();
		return res;
	}
	
	//조회수 증가
	public int update_readhit(int idx) {
		SqlSession sqlsession = factory.openSession(true);
		int res = sqlsession.update("b.board_update_readhit",idx);
		sqlsession.close();
		return res;
	}
	
	//기준글의 step보다 큰 값 +1 처리
	public int update_step(BoardVO baseVO) {
		SqlSession sqlSession = factory.openSession(true);
		int res = sqlSession.update("b.board_update_step",baseVO);
		sqlSession.close();
		return res;
	}
	
	//댓글달기
	public int reply(BoardVO vo) {
		SqlSession sqlSession = factory.openSession(true);
		int res = sqlSession.insert("b.board_reply",vo);
		sqlSession.close();
		return res;
	}
	
	//게시판의 전체 게시물 수
	public int getRowTotal() {
		SqlSession sqlSession = factory.openSession();
		int count = sqlSession.selectOne("b.board_count");
		sqlSession.close();
		return count;
	}
}
