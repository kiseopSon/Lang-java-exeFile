<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.regi{
		width: 600px;
		text-align:center;
		margin: auto;
		margin-bottom: 10px;
		
	}
</style>
<script type="text/javascript">
	function regi() {
		location.href="insert_form.do";
	}
</script>
</head>
<body>
	<jsp:include page="index.jsp"/><!-- 갖다 붙이기 그냥 위에 include쓴거랑 거의 비슷 -->
	
	<div class="regi">
		<input id="reg" type="button" value="상품등록하기" onclick="regi();">
	</div>
	
	
	<table align="center" border="1" width="600" style="border-collapse: collapse;">
		<tr bgcolor="#dedede">
			<th width="15%">제품번호</th>
			<th width="10%">이미지</th>
			<th width="30%">제품명</th>
			<th width="20%">제품가격</th>
			<th>비고</th>
		</tr>
		
		<!-- 제품이 없는 경우 -->
		<c:if test="${empty list }"><!-- com001이 없어서 그럼 -->
			<tr>
				<td colspan="5" align="center">해당 제품이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="p" items="${list }"><!--현재는 com001을 뺸 나머지들 출력 -->
		<tr align="center">
			<td>${p.p_num }</td>
			<td>
				<img alt="" src="../images/${p.p_image_s }" width="100" height="90">
			</td>
			<td>
				<a href="view.do?idx=${p.idx }">${p.p_name}</a>
			</td>
			<td>
				할인가 : <br>
				<fmt:formatNumber value="${p.p_saleprice }"/><br>
				<font color="red">(${p.sale_rate}% 할인)</font>
			</td>
			<td>
				단가 : <br>
				<fmt:formatNumber value="${p.p_price }"/>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>