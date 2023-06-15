<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script type="text/javascript" src="js/httpRequest.js"></script>

	<script type="text/javascript">
		//페이지 로드시 자동으로 호출되는 메서드
		//자동으로 보이지만 사실은 서버에서 제공하는 resources중 브라우저에 import된 데이터중에서도 json형식의 데이터를 읽어와서 읽어와주는 로직이다.
		window.onload = function(){
			var url = "movie_list_json.jsp";
			
			sendRequest(url, null, resultFn, "GET");
		}
	
		function resultFn(){
			if( xhr.readyState == 4 && xhr.status == 200 ){
				//서버로부터 Ajax통신에 문제가 없다면 데이터를 가져온다.
				var data = xhr.responseText;
				
				//서버에서 넘어온 정보는 ""에 묶여서 문자열 형식으로 넘어온다.
				//이를 JSON타입으로 변경해줘야 한다.
				var json = eval(data);
				
				var movie_select = document.getElementById("movie_select");
				for( var i = 0; i < json.length; i++ ){
					
					var option = document.createElement("option");
					option.innerHTML = json[i].title;
					option.value = json[i].path;
					
					movie_select.appendChild(option);
				}//for
			}
		}
		
		function movie_play(){
			//select태그에서 현재 선택되어 있는 항목의 value값(path경로)을 가져온다
			var path = document.getElementById("movie_select").value;
			
			var my_video = document.getElementById("my_video");
			my_video.src = path;
			my_video.play();
		}
		
	</script>

</head>
<body>
	비디오 목록 : 
	<select id="movie_select" onchange="movie_play();">
		<option>:::재생할 영상을 선택하세요:::</option>
	</select>
	
	<hr>
	
	<video src="" id="my_video" width="320" height="240" controls="controls">
	</video>
	
</body>
</html>


















