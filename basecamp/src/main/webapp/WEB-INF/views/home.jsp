<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<title>NHN Ent. 사전 과제</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="col-md-12">
		<h1>NHN Ent. 사전 과제 : 방명록</h1>
		</div>
		<!-- 글 작성 파트 -->
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">POST</h3>
				</div>
				<div class="panel-body">
					<form id="postText">
						<div class="form-group">
							<label for="inputEmail">이메일 주소</label>
							<div class="input-group">
								<span class="input-group-addon">@</span>
								<input type="email" class="form-control" id="inputEmail" placeholder="example@host.com">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword">암호</label>
							<input type="password" class="form-control" id="inputPassword" placeholder="password">
						</div>
						<div class="form-group">
							<label for="inputText">내용</label>
							<textarea type="text" rows="5" class="form-control" id="inputText"></textarea>
						</div>
						<button type="button" class="btn btn-default btn-block" onclick="newArticle()">post</button>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-12" id="articleList">
			<!-- 믿기 힘들지만, 이것이 반복문이라고 한다 -->
			<!-- jade님 저를 구원해주세요 -->
			<c:forEach var="article" items="${allArticle}">
				<div class="panel panel-default">
	                <div class="panel-heading">
	                	<h3 class="panel-title">${article.email}</h3>
	                </div>
	                <div class="panel-body">
	                	${article.content}
	                </div>
	            </div>
			</c:forEach>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		function newArticle(){
			//console.log($("#inputEmail").val());
			if (!($("#inputEmail").val()) || !($("#inputPassword").val()) || !($("#inputText").val())) {
				console.log("null");
			} else if (!isEmail($("#inputEmail").val())) {
				console.log("not email!!");
			} else {
				$.ajax({
					type: 'POST',
					url: "/newarticle",
					data: {"email":$("#inputEmail").val(), "password":$("#inputPassword").val(), "content":$("#inputText").val()},
					success: function(data) {
						Console.log(data);
						$("#inputEmail").val() = "";
						$("#inputPassword").val() = "";
						$("#inputText").val() = "";
					},
					dataType: "json",
					error: function(data) {
						console.log("ERROR!");
						console.log(data);
					}
				});	
			}
		}
		
		function isEmail(addr){
			console.log(addr);
			var list = addr.split("@");
			if(list.length === 2){
				return true;
			} else {
				return false;
			}
		}
	</script>
	
</body>
</html>
