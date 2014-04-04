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
		$("#header p").click(function(){
			if(!J.isVisible("#advance"))
			{
				$(this).text("普通搜索");
				$("#advance").slideDown(1000);
			}
			else
			{
				$(this).text("高级搜索");
				$("#advance").slideUp(1000);
			}
		});
		});
	});
</script>
</head>
<body>
	<div id="header">
		<p>高级搜索</p>
	</div>
	<div id="search">
		<img class="logo" src="css/img/iJob.png" />
		<form action="search" method="post">
			<input type="text" name="keyword" /> <input type="submit" value="Go"
				name="go" />
			<div id="advance">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<span>公司名称：</span><input type="text" name="cpnm" /> 
					</td>
					<td style="padding-left:10px;">
						<span>工作地点：</span><input type="text" name="wkplace" /> 
					</td>
				<tr>
				<tr>
					<td>
						<span>公司类型：</span><input type="text" name="cptype" /> 
					</td>
					<td style="padding-left:10px;">
						<span>公司行业：</span><input type="text" name="cptrade" /> 
					</td>
				<tr>
				<tr>
					<td>
						<span>工作形式：</span><input type="text" name="wkform" /> 
					</td>
					<td style="padding-left:10px;">
						<span>学历要求：</span><input type="text" name="degreerq" /> 
					</td>
				<tr>
			</table>
			</div>
		</form>
	</div>
	<div id="hotword">
	<p>热门搜索</p>
	</div>
	
</body>
</html>
