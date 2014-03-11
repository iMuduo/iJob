<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>找工作，iJob</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="找工作,软件">
<meta http-equiv="description" content="iJob">
<link rel="stylesheet" type="text/css" href="css/index.css">
<jsp:include page="base.jsp"></jsp:include>
<script>
	var list = [];
	var kw;
	$(function() {
		J.autoComplete("input[name='keyword']","hotword!autoComplete");
		$("input[type='submit']").button();
		
		$.post('hotword!execute',{},function(r){
			var list=J.toJson(r);
			J.each(list,function(i,n){
				$("#hotword").append("<span>"+i+"</span>");
			});
			$("#hotword span").on('click',function(e){
			$("input[name='keyword']").val($(e.target).text());
			$("form").submit();
		});
		});
	});
</script>
</head>
<body>
	<div id="header">
		<a>登录</a>
		<div class="sp"></div>
		<a>注册</a>
	</div>
	<div id="search">
		<img class="logo" src="css/img/iJob.png" />
		<form action="search" method="post">
			<input type="text" name="keyword" /> <input type="submit" value="Go"
				name="go" />
		</form>
	</div>
	<div id="hotword">
	</div>
</body>
</html>
