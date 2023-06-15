package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.deptVO;

//INSERT_DELETE_UPDATE안씀. 이미있음?있음.
public class deptDAO {
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static deptDAO single = null;

	public static deptDAO getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new deptDAO();
		//생성된 객체정보를 반환
		return single;
	}
	
	SqlSessionFactory factory;
	
	public deptDAO() {
		//factory는 mybatisconnector.xml의 정보를 가지고 있다.
		//어떤 db를 쓸것인가, mapper는 무엇을 쓸것인가 등의 정보를 가지고 있다.
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	//사원목록 가져오기
	public List<deptVO> select(){
		List<deptVO> list= null;
		
		//1.sql처리객체 얻어오기
		SqlSession sqlSession = factory.openSession();//정보만 있음.
		
		//2.위의 처리객체를 통해 mapper를 수행
		list = sqlSession.selectList("dept.dept_list");//명령어 수행
		
		sqlSession.close();//반드시 닫아줘야 한다.
		
		return list;
	}
	
	public List<deptVO> select(int deptno){//딱 하나만 보낼수 있음. 내가만든게 아니기 때문에.
		List<deptVO> list = null;
		SqlSession sqlSession = factory.openSession();//이것도 나중에 spring servletBean을 통해 컨테이너로 관리하여 사용됨.
		list = sqlSession.selectList("dept.dept_list_deptno",deptno);
		return list;
	}
}
