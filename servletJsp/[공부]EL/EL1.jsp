<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//EL 표현식: JSP에서 사용하는 표현식을 더 간결하게 쓰기 위한 표현형태. JSP에서만 가능.
	/*
		EL(Expression language)
		-EL로 값을 표현하려면 4개 영역(4개 영역은 모두 jsp 객체) 을 통해 값을 전달해야 하며
		전달할 값을 누구와 공유할 것인지를 나타낸다.
		1)page 영역 : page영역은 현재 페이지에서만 값을 공유할 수 있다.
		2)request 영역 : 가장 많이 사용하는 영역 : 지역개념으로 페이지가 닫히면 종료.
		request영역에 저장된 값을 두개의 페이지가 값을 공유할 수 있다.
		3)session 영역 : 전역개념으로 브라우저가 닫히기전까지는 값이 유지.
		같은 브라우저에서 실행중인 모든 페이지들에 공유가 가능. - static개념
		4)application 영역 : 프로젝트에서 요청되는 모든 페이지들이 값을 공유 - 잘 안씀.
	*/
	String msg="안녕";//일반 스크립트 릿안에 선언된 변수
	pageContext.setAttribute("msg", msg);
	request.setAttribute("msg", "requestScope영역이 호출됨");
	session.setAttribute("msg", "안쪽은 키값 여긴세션 스코프에 담긴 내용");//키값
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		EL표현식(pageScope) : ${pageScope.msg}<br>
		EL표현식(requestScope) : ${requestScope.msg}<br>
		EL표현식(session) : ${sessionScope.msg }<br>
		EL표현식(생략식) : ${msg }<br>
		EL표현식(파라미터) : ${param.abc }<br> 
		<!-- 생략식 영역 참조 순서
			1.pageScope
			2.requestScope
			3.sessionScope
			4.applicationScope -->
	</body>
</html>