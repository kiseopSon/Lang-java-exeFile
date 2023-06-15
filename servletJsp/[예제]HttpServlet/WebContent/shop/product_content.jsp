<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/httpRequest.js"></script>
<script type="text/javascript">
	function addCart(p_idx,m_idx) {
		var url = "cart_insert.do?p_idx="+p_idx+"&m_idx="+m_idx;
		sendRequest(url,null,resultFn,"GET");
	}
	function resultFn() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			var json = eval(data);
			if(json[0].result =='yes'){
				alert("장바구니에 담았습니다.");
				if(!confirm("장바구니로 이동하시겠습니까?")){
					return;
				}
				location.href="cart_list.do";
			}
			else{
				alert("해당 상품은 이미 장바구니에 담겨져 있습니다.");
			}
		}
	}
</script>
</head>
<body>
	<jsp:include page="index.jsp"></jsp:include>
	<table align="center" width="600" border="1" style="border-collapse: collapse;">
		<tr>
			<td width="40%">제품분류</td>
			<td>${vo.category}</td>
		</tr>
		<tr>
			<td width="40%">모델명</td>
			<td>${vo.p_num}</td>
		</tr>
		<tr>
			<td width="40%">제품명</td>
			<td>${vo.p_name}</td>
		</tr>
		<tr>
			<td width="40%">제조사</td>
			<td>${vo.p_company}</td>
		</tr>
		<tr>
			<td width="40%">가격</td>
			<td>원가 : <fmt:formatNumber value="${vo.p_price}"/><br>
				할인가 : <fmt:formatNumber value="${vo.p_saleprice}"/>
				
			</td>
		</tr>
		<tr>
			<td colspan="2">제품설명</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<img src="../images/${vo.p_image_l }" width="500">
			</td>
		</tr>
		<tr>
			<td colspan="2">${vo.p_content}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="장바구니 담기" onclick="addCart('${vo.idx}','${1}')"/>
				<input type="button" value="장바구니 보기" onclick="location.href='cart_list.do'"/>
			</td>
		</tr>
	</table>
</body>
</html>