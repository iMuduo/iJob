<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<link rel="stylesheet" type="text/css" href="css/result.css">
<jsp:include page="base.jsp"></jsp:include>
<script type="text/javascript" src="js/result.js"></script>
<script>
var list=['Java','Js','JJ'];
	$(function(){
		$("input[name='keyword']").autocomplete({
			source: list
		});
		$("input[type='submit']").button();
		$("#result").accordion({header:'h3'});
	});
</script>
</head>

<body>
	<div id="search">
		<form action="search" method="post">
			<img src="css/img/logo.png" />
			<input type="text" name="keyword" /> 
			<input type="submit" value="Go" name="go" />
		</form>
	</div>
	<p class="tip">
		已搜索到：
		<s:property value="count"></s:property>
		条结果
	<p>
	<div id="result">
	</div>
	<label id="keyword" style="display:none"> <s:property
			value="keyword" /> </label>
</body>
</html>
