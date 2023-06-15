<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");

	//gugudan_ajax.jsp?dan=3
	int dan = Integer.parseInt( request.getParameter("dan") );
	
	String msg = "";
	
	for( int i = 1; i <= 9; i++ ){
		msg += dan + " * " + i + " = " + (dan*i) + "<br>";
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=msg %>
</body>
</html>






