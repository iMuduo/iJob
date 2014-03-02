<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="styl/index.css">
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
</head>
<body>
	<div style="DISPLAY: block" id="wrapper">
		<div id="content">
			<div id="m">
				<p id="lg">
					<img src="img/iJob.png" width="270px" height="129px">
				</p>
				<div id="fm">
					<form action="search" method="post">
						<span class="bg s_ipt_wr"> <input id="kw" class="s_ipt"
							maxlength="100" type="text" name="keyword" align="bottom">
						</span> <span class="bg s_btn_wr"> <input id="su" class="bg s_btn"
							value="搜索一下" type="submit"> </span>

					</form>
					<span class="tools"> <span id="mHolder"> </span> </span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
