<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="VO.personVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//map에 담긴 값을 el표기법으로 출력하기
	Map<String, String> map = new HashMap<>();
	map.put("driver", "oracle.jdbc.driver");
	map.put("url", "jdbc:oracle:thin@192.168.0.0");
	map.put("user", "test");
	map.put("pwd", "1111");
	request.setAttribute("myMap", map);
%>
<%
	//vo접근
	personVO vo = new personVO();
	vo.setName("홍길동");
	vo.setAge(20);
	request.setAttribute("vo1", vo);
	
	//list접근
	List<personVO> arr = new ArrayList<>();
	arr.add(vo);
	request.setAttribute("arr", arr);
	
	//jstl를 쓰면 스크립트릿 안씀
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	EL사칙연산자<br>
	${1+1 } <!-- 주석도 오류남\${1+2} 일반문자로인식 -->
	<hr>
	\${3gt2 } <!-- gt>, ge>=, lt<, le<=, 로써 결과가 true또는false로 반환됨. -->
	<hr>
	파라미터값 : ${empty param.msg ? '값없음' : '값:' +param.msg }<br>
	<hr>
	EL 논리연산자<br>
	파라미터 : ${para.msg  == null or param.msg == '' ? 'x':param.msg}<br>
	driver : ${myMap.get("driver") }<br>
	url : ${myMap['url'] }<br>
	user : ${myMap.user }<br>
	vo이름 : ${vo1.name }<br> <!-- 알아서 getter를 인식해줌 -->
	vo나이 : ${vo1.age }<br>
	list이름 : ${arr[0].name }<br>
	list나이 : ${arr[0].age }<br>
</body>
</html>