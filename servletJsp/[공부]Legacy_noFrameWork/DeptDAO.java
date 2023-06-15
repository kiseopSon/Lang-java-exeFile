package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VO.DeptVO;//그외 모든것
import service.DBService; //연결 서비스

public class DeptDAO {// 접속 끈기
	//DAO : DATA ACCESS OBJECT : 데이터 접근을 목표로 하는 객체 (DB, 저장소 접근을 위한 클래스)
	//쿼리문을 통한 결과를 LIST형식으로 만들어 주는 클래스
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static DeptDAO single = null;

	public static DeptDAO getInstance() {//getinstance만 호출하면 끝.
		//생성되지 않았으면 생성
		if (single == null)
			single = new DeptDAO();
		//생성된 객체정보를 반환
		return single;
	}
	
	//부서테이블의 자원을 검색 _select -> list는 다가져오는것 one은 하나만 가져오는것 : 모든작업 다 되어있음. 
	public List<DeptVO> selectList() {

		List<DeptVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from dept";
		//String sql = "insert into dept values(deptno.nextVal,?,?,?,?)";


		try {
			//1.Connection획득
			conn = DBService.getInstance().getConnection();
			//2.명령처리객체정보를 얻어오기
			pstmt = conn.prepareStatement(sql);

			
			//2.pstmt parameter 채우기
			while (rs.next()) {
				DeptVO vo = new DeptVO();
				//현재레코드값=>Vo저장
				vo.setDeptNo(rs.getInt("deptno"));
				vo.setdName(rs.getString("dname"));
				vo.setLoc(rs.getString("location"));
				//ArrayList추가
				list.add(vo);
			}
			//4.결과행 처리객체 얻어오기
			rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
}
