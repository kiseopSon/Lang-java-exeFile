<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//세션 : 웹페이지들간의 클라이언트 상태유지를 위한 개념. 관련이 없는 페이지들간에 값을 주고 받을 수 있도록 연관성을 설정하는 방법.
	//쿠키 : 인터넷 사용자가 어떠한 웹사이트를 방문한 경우 그 사이트가 사용하고 있는 서버에서 사용자의 컴퓨터에 설치하는 작은 기록정보 파일.
	//쿠키정보는 사용자가 같은 웹사이트를 방문할 때 마다 읽히고 수시로 새로운 정보로 바뀐다.
	//브라우저를 재실행하면 같은 웹사이트를 방문할 때 마다 읽히고 수시로 새로운 정보로 바뀐다. 이렇게 브라우저 종료시 함께 종료되는 쿠기를 [세션쿠키].영속성이 없다.
	Cookie cookie = new Cookie("A","A1.jsp");
	response.addCookie(cookie);
%>
<c:if test="${empty sessionScope.user }"><!-- null -->
	<script>
	alert("로그인 후 이용하세요.");
	</script>
</c:if>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		A1.jsp를 방문해 주셔서 감사합니다.<br>
		<a ="B1.jsp">B페이지로 이동.</a>
	</body>
</html>