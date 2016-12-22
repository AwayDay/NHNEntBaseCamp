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
				<div class="panel panel-default" id="${article.id}">
	                <div class="panel-heading">
	                	<h3 class="panel-title">${article.email}</h3>
	                </div>
	                <div class="panel-body">
	                	<p>
	                		작성 시간 : ${article.createdDate}
	                	</p>
	                	<p class="articleContent">
	                		${article.content}
	                	</p>
	                	<button type="button" class="btn btn-default btn-block" 
	                	data-toggle="modal" data-target="#updateModal" data-id="${article.id}">
	                		수정하기
	                	</button>
	                </div>
	            </div>
			</c:forEach>
		</div>
	</div>
	
	<div class="modal fade" id="updateModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">글 수정</h4>
				</div>
				
				<div class="modal-body">
					<div class="panel panel-default">
						<div class="panel-body">
							<form id="updateText">
								<div class="form-group">
									<label for="inputPassword">암호 확인</label>
									<input type="password" class="form-control" id="confirmPassword" placeholder="password">
								</div>
								<div class="form-group">
									<label for="inputText">내용</label>
									<textarea type="text" rows="5" class="form-control" id="updateText"></textarea>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn" onclick="updateArticle(this.value)">UPDATE</button>
				</div>
			</div>
		</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		function newArticle(){
			//console.log($("#inputEmail").val());
			if (!($("#inputEmail").val()) || !($("#inputPassword").val()) || !($("#inputText").val())) {
				//console.log("null");
				alert("빈 칸이 없어야 합니다.");
			} else if (!isEmail($("#inputEmail").val())) {
				alert("올바른 이메일을 입력하세요.");
			} else {
				$.ajax({
					type: 'POST',
					url: "/article",
					data: {"email":$("#inputEmail").val(), "password":$("#inputPassword").val(), "content":$("#inputText").val()},
					success: function(data) {
						console.log(data);
						$("#inputEmail").val("");
						$("#inputPassword").val("");
						$("#inputText").val("");
					},
					//dataType: "json",
					error: function(data) {
						//console.log("ERROR!");
						console.log(data);
						alert(data);
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
		
		$('#updateModal').on('show.bs.modal', function (event) {
			var button = $(event.relatedTarget);
			var id = button.data('id');
			var content = $("#"+id+" .articleContent").text();
			
			var modal = $("#updateModal");
			console.log(id);
			modal.find('.modal-footer button').val(id);
			modal.find('.modal-body #updateText').val(content);
		})
			
		function updateArticle(id){
			console.log(id);
			console.log($("#confirmPassword").val());
			console.log($("#updateText").val());
			var update = {}; 
			update.password = $("#confirmPassword").val();
			update.content = $("#updateText").val();
			
			$.ajax({
				type: 'PUT',
				url: "/article/"+id,
				data: JSON.stringify(update),
				success: function(data) {
					console.log(data);
				},
				//dataType: "json",
				contentType: 'application/json',
				error: function(data) {
					console.log(data);
					alert(data);
				}
			});
		}
	</script>
	
</body>
</html>
