<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function send(f) {
		var title = f.title.value.trim();
		var photo = f.photo.value.trim();
		
		if(title == ''){
			alert("제목을 입력하세요");
			return;
		}
		if(photo == ''){
			alert("파일을 선택하세요.");
			return;
		}
		f.action = "upload.do";//파일명을 보내주는것임.
		f.submit();
	}
</script>
</head>
<body>
	<form method="POST" enctype="multipart/form-data"><!-- 파일업로드시, 필수사항 1. 반드시 post 2.enctype : 폼을 전송할 떄 사용할 인코딩 방법 -->
		제목 : <input name="title"><br>
		첨부 : <input type="file" name="photo"><br>
		<input type="button" value="업로드" onclick="send(this.form);">
	</form>
</body>
</html>