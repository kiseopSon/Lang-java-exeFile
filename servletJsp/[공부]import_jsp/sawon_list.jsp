<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="VO.sawon_listVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int deptno = Integer.parseInt(request.getParameter("deptno"));
	
	InitialContext ic = new InitialContext();
	
	Context ctx = (Context)ic.lookup("java:comp/env");
	
	DataSource dataSource = (DataSource)ctx.lookup("jdbc/oracle_test");

	Connection conn = dataSource.getConnection();
	
	String sql ="select * from sawon where deptno =" + deptno;
	
	PreparedStatement pstmt = conn.prepareStatement(sql);
	
	ResultSet rs = pstmt.executeQuery();
	 
	List<sawon_listVO> list = new ArrayList<>();
	while(rs.next()){
		 sawon_listVO vo = new sawon_listVO();
		 vo.setDeptno(rs.getInt("deptno"));
		 vo.setSabun(rs.getInt("sabun"));
		 vo.setSajob(rs.getString("sajob"));
		 vo.setSaname(rs.getString("saname"));
		 list.add(vo);
	}
	rs.close();
	pstmt.close();
	
	conn.close();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>jsp 연습2</title>
	</head>
	<body>
		<table border="1">
		<caption>부서별 사원목록</caption>
			<tr>
				<td>부서번호</td>
				<td>사원번호</td>
				<td>직책</td>
				<td>이름</td>
			</tr>
			<%for(int i = 0; i<list.size();i++) {%>
				<tr>
					<td><%= list.get(i).getDeptno() %></td>
					<td><%= list.get(i).getSabun() %></td>
					<td><%= list.get(i).getSajob() %></td>
					<td><%= list.get(i).getSaname() %></td>
				</tr>
			<%} %>
		</table>
	</body>
</html>