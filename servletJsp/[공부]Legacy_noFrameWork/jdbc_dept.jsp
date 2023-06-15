<%@page import="VO.DeptVO"%>
<%@page import="java.util.List"%>
<%@page import="dao.DeptDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//부서목록 가져오기 : 딱 하나가지고 재활용
	DeptDAO dao = DeptDAO.getInstance();
	List<DeptVO> dept_list = dao.selectList();//한번에 와일문내용 여기에 다 담기.
//결국 select list를 통해 검색하면 됨
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
	<caption>부서목록</caption>
		<tr>
			<td>부서번호</td>
			<td>부서명</td>
			<td>부서위치</td>
		</tr>
		<% for(int i = 0; i<dept_list.size();i++){
			DeptVO vo = dept_list.get(i);%>
		<tr>
			<td><%= vo.getDeptNo() %></td>
			<td><%= vo.getdName()%></td>
			<td><%= vo.getLoc() %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>