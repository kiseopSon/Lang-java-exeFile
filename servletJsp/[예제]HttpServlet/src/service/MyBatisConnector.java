package service;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//db를 이전엔 그냥 바로 접속했다면 여기를 거치고 db에 들어간다.
public class MyBatisConnector {
	SqlSessionFactory factory = null;
	private static MyBatisConnector connector;
	
	public MyBatisConnector() {
		try {
			//sqlmapconfig.xml을 읽어온다.
			Reader reader = Resources.getResourceAsReader("config/mybatis/sqlMapConfig.xml");//대소문자 구분함.
			factory = new SqlSessionFactoryBuilder().build(reader);//context(name) -> mapconfig.xml(끝value)
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}//생성자
	
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	public static MyBatisConnector getInstance() {
		//생성되지 않았으면 생성
		if (connector == null)
			connector = new MyBatisConnector();
		//생성된 객체정보를 반환
		return connector;
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}
