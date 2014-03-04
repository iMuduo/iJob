<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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

<title>�ҹ�����iJob</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="styl/result.css">
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="js/result.js"></script>
</head>

<body>
	<div id="fm">
		<img id="logo" src="img/logo.png" />
		<form action="search" method="post">
			<span class="bg s_ipt_wr"> <input id="kw" class="s_ipt"
				maxlength="100" type="text" name="keyword" align="bottom"> </span>
			<span class="bg s_btn_wr"> <input id="su" class="bg s_btn"
				value="����һ��" type="submit"> </span>

		</form>
		<span class="tools"> <span id="mHolder"> </span> </span>
	</div>
	<div>

		<s:iterator var="item" value="result">
			<table>
				<tr>
					<td><span class="desc">��˾���ƣ�</span><span class="content"><s:property value="#item['cpnm']" /></span></td>
					<td><span class="desc">�������ƣ�</span><span class="content"><s:property value="#item['jbnm']" /></span></td>
				</tr>
				<tr> 
					<td><span class="desc">��˾��ģ��</span><span class="content"><s:property value="#item['cpscale']" /></span></td>
					<td><span class="desc">��˾���ͣ�</span><span class="content"><s:property value="#item['cptype']" /></span></td>
				</tr>
				<tr>
					<td><span class="desc">��˾��ҵ��</span><span class="content"><s:property value="#item['cptrade']" /></span></td>
					<td><span class="desc">�Ա�Ҫ��</span><span class="content"><s:property value="#item['genderrq']" /></span></td>
				</tr>
				<tr>
					<td><span class="desc">��Ƹ������</span><span class="content"><s:property value="#item['rcnos']" /></span></td>
					<td><span class="desc">����Ҫ��</span><span class="content"><s:property value="#item['agerq']" /></span></td>
				</tr>
				<tr>
					<td><span class="desc">������ʽ��</span><span class="content"><s:property value="#item['wkform']" /></span></td>
					<td><span class="desc">��ֹ���ڣ�</span><span class="content"><s:property value="#item['deadline']" /></span></td>
				</tr>
				<tr>
					<td><span class="desc">ѧ��Ҫ��</span><span class="content"><s:property value="#item['degreerq']" /></span></td>
					<td><span class="desc">н�ʴ�����</span><span class="content"><s:property value="#item['salary']" /></span></td>
				</tr>
				<tr>
					<td><span class="desc">�������飺</span><span class="content"><s:property value="#item['wkxp']" /></span></td>
					<td><span class="desc">�����ص㣺</span><span class="content"><s:property value="#item['wkplace']" /></span></td>
				</tr>

				<tr>
					<td><span class="desc">ְλ������</span><span class="content"><s:property value="#item['jbdesc']" /></span></td>
					<td><span class="desc">����Ҫ��</span><span class="content"><s:property value="#item['wkrq']" /></span></td>
				</tr>
				<tr>
					<td><span class="desc">��ظ�����</span><span class="content"><s:property value="#item['benefit']" /></span></td>
					<td><span class="desc">�������ڣ�</span><span class="content"><s:property value="#item['date']" /></span></td>
				</tr>
				<tr>
					<td><span class="desc">��˾��Ϣ��</span><span class="content"><s:property value="#item['cpinfo']" /></span></td>
					<td><span class="desc">��Ϣ��Դ��</span><span class="content"><s:property value="#item['origin']" /></span></td>
				</tr>
				<tr>
					<td><span class="desc">������Ϣ���ӣ�</span><span class="content"><s:property value="#item['jburl']" /></span></td>
					<td><span class="desc">��˾��Ϣ���ӣ�</span><span class="content"><s:property value="#item['cpurl']" /></span></td>
				</tr>
			</table>
		</s:iterator>
	</div>
	<label id="keyword" style="display:none"> <s:property
			value="keyword" /> </label>
</body>
</html>
