<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function modify(f) {
		var c_cnt = f.c_cnt.value.trim();
		
		//수량이 정수인지 확인
		var num_patt = /^[0-9]+$/;
		if(!num_patt.test(c_cnt)){
			alert("수량은 정수로 입력하세요.");
			return;
		}
		f.submit();
	}
	function del(c_idx) {
		if(!confirm("정말로 삭제하시겠습니까?")){
			return;
		}
		location.href="cart_delete.do?c_idx="+c_idx;
	}
</script>
</head>
<body>
	<jsp:include page="index.jsp"/>
	<table border="1" align="center" width="600">
		<tr>
			<td colspan="6" align="center">장바구니 내용</td>
		</tr>
		<tr bgcolor="#dedede">
			<th>제품번호</th>
			<th width="19%">제품명</th>
			<th>단가</th>
			<th>수량</th>
			<th>금액</th>
			<th>삭제</th>
			
		</tr>
		<c:forEach var="vo" items="${list }">
		<tr align="center">
			<td>${vo.p_num }</td>
			<td>${vo.p_name }</td>
			<td>
				<fmt:formatNumber value="${vo.p_price }"/><br>
				<font color="red">
					세일가 : <fmt:formatNumber value="${vo.p_saleprice }"/>
				</font>
			</td>
			<td>
			<!-- 수량 조정 폼 -->
				<form action="cart_update.do">
					<input type="hidden" name="c_idx" value="${vo.c_idx }">
					<input size="2" name="c_cnt" value="${vo.c_cnt }" align="center">
					<input type="button" value="수정" onclick="modify(this.form);">
				</form>
			</td>
			<td><fmt:formatNumber value="${vo.c_cnt * vo.p_saleprice }"/></td>
			<td>
				<input type="button" value="삭제" onclick="del('${vo.c_idx}');">
			</td>
		</tr>
		
		</c:forEach>
		<c:if test="${empty list }">
		<tr>
			<td colspan="6" align="center">장바구니가 비었습니다.</td>
		</tr>
		</c:if>
		<tr>
			<td colspan="5" align="right">총 결재액 </td>
			<td>
				<fmt:formatNumber value="${total_amount}"/>
			</td>
		</tr>
	</table>
</body>
</html>