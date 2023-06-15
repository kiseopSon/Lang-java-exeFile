<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL(JSP Standard Tag Library) 
     : if, forEach, choose, 포멧등을 jsp내에서 쉽고 간편하게 처리할 수 있도록 해 주는 라이브러리-->  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
    
<%
	//현재 년/월/일/시/분/초
	Date today = new Date();
	int money = 2100000000;
	
	//위의 값을 requestScope에 저장
	request.setAttribute("today", today);
	request.setAttribute("money", money);
	
	String[] array = {"사과", "배", "포도", "참외"};
	request.setAttribute("fruit_array", array);
	
	ArrayList<String> sido = new ArrayList<>();
	sido.add("서울");
	sido.add("대전");
	sido.add("대구");
	sido.add("부산");
	sido.add("인천");
	sido.add("제주");
	
	request.setAttribute("sido", sido);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	JSTL의 fmt(날짜, 숫자)<br>
	<fmt:formatDate value="${ today }"/><br>
	<fmt:formatDate value="${ today }" pattern="YYYY년 MM월 dd일"/><br>

	<fmt:formatNumber value="${ money }"/>
	
	<hr>
	
	JSTL의 forEach, if문<br>
	<ul>
		<!-- var : 한바퀴 돌때마다 갱신되는 begin값
		     begin : 시작값
		     end : 끝 값
		     step : 증가값 -->
		<c:forEach var="n" begin="1" end="5" step="1">
			
			<!-- test="EL표기법을 통한 조건식" -->
			<c:if test="${ n mod 2 eq 1 }">
				<li>
					안녕(${ n })
				</li>
			</c:if>
		
		</c:forEach>
	</ul>
	
	<hr>
	
	JSTL을 통한 배열출력<br>
	<c:forEach var="f" items="${ fruit_array }">
		${ f }<br>
	</c:forEach>
	
	<hr>
	
	<ul>
		<c:forEach var="v" items="${ sido }">
			<li>${ v }</li>
		</c:forEach>
	</ul>
	
	<hr>
	
	List출력(index접근)<br>
	<ul>
		
		<c:forEach var="value" items="${ sido }" varStatus="cnt">
		
			<li>
				<!-- cnt.index : 방번호(0번부터)
				     cnt.count : 순번(1번부터) -->
				${cnt.count} / ${ value }
			</li>
		
		</c:forEach>
		
	</ul>
	
</body>
</html>















