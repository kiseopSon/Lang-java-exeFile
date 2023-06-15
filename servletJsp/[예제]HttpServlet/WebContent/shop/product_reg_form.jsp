<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkdata(f) {
		var category = f.category.value.trim();
		var p_num = f.p_num.value.trim();
		var p_name = f.p_name.value.trim();
		var p_company = f.p_company.value.trim();
		var p_price = f.p_price.value.trim();
		var p_saleprice = f.p_saleprice.value.trim();
		var p_image_s = f.p_image_s.value.trim();
		var p_image_l = f.p_image_l.value.trim();
		
		var pattern = /^[0-9]+$/;
		if (!pattern.test(p_price)){
			alert("가격은 정수로 입력해야 합니다");
			return;
		}
		if(!pattern.test(p_saleprice)){
			alert("할인가는 정수로 입력해야 합니다.");
			return;
		}
		if(category ==""){
			alert("카테고리를 선택하세요");
			return;
		}
		
		f.submit();
	}
</script>
</head>
<body>
	<jsp:include page="index.jsp"></jsp:include>
		<form action="insert.do" method="POST" enctype="multipart/form-data">
			<table align="center" width="600" border="3">
				<tr>
					<td>제품 카테고리</td>
					<td>
						<select name="category">
							<option value="">카테고리 선택</option>
							<option value="com001">컴퓨터</option>
							<option value="ele002">가전제품</option>
							<option value="sp003">스포츠</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>모델명</td>
					<td><input name="p_num"></td>
				</tr>
				<tr>
					<td>제품명</td>
					<td><input name="p_name"></td>
				</tr>
				<tr>
					<td>제조사</td>
					<td><input name="p_company"></td>
				</tr>
				<tr>
					<td>상품가격</td>
					<td><input name="p_price"></td>
				</tr>
				<tr>
					<td>제품 할인가</td>
					<td><input name="p_saleprice"></td>
				</tr>
				<tr>
					<td>제품 설명</td>
					<td><textarea rows="10" cols="50" style="resize: none;" name="p_content"></textarea></td>
				</tr>
				<tr>
					<td>제품사진(작은사진)</td>
					<td><input type="file" name="p_image_s"></td>
				</tr>
				<tr>
					<td>제품사진(큰사진)</td>
					<td><input type="file" name="p_image_l"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input value="등록" type="button" onclick="checkdata(this.form);">
						<input type="reset" value="초기화">
					</td>
				</tr>
			</table>
		</form>
</body>
</html>