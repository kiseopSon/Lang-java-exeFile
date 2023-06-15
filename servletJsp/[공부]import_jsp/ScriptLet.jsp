<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- jsp(java server pages) : 내부적으로 연산능력을 갖는 html이다.
	jsp는 작업이 완료되어 호출할 떄, 서블릿으로 자동으로 변환되어 실행되며,
	서블릿만으로는 한계가 있는 ui디자인등을 쉽게 해주는 기능이다. -->
<%
	//스크립트 릿 : jsp에서 자바코드를 사용하고자 할 때 지정하는 영역. 여기서 변수나 메서드는 지역변수 개념.
	//수신인코딩이 get일 경우에는 상관없지만, post일 경우 한글데이터가 꺠진다.
	//그러므로 post타입의 전송값을 받을시 아래와 같은 처리가 필요.
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"));
	String tel = request.getParameter("tel");
%>
<%! //선언부 : 변수 또는 메서드를 정의하는 영역(전역변수 개념)
<%-- jsp주석 : 자리는 먹고, 브라우저상 안보임 --%>
		Random rd;
		int n = 0;
	%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%request.setCharacterEncoding("utf-8");%>
		<p>&lt;<%@ 내용 %>&gt;<br>jsp헤더 : 전송시 인코딩, import문장등을 설정하기 위해 반드시 필요.</p>
		<p>&lt;<% 자바코드 %>&gt;<br>스크립트 릿.</p>
		<p>&lt;<%= 변수명 %>&gt;<br>스크립트릿에서 선언한 변수의 값을 출력하기 위한 코드. ;넣으면 안됨.</p>
		<p><%= name%></p>
		<p><%= age%></p>
		<p><%= tel%></p>

		<% for(int i=1; i<=9;i++){%>
			<tr>
				<% for(int j = 2; j<=9; j++){ %>
				<td><%= j+"*"+i+"="+ j*i %></td>
				<%}%>
			</tr>
		<%} %>
	</body>

	<script>
		//VO(ValueObject) : DB의 여러가지 정보를 묶어서 저장하는 하나의 클래스

	</script>
</html>