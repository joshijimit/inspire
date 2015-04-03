<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<body>
<div class="label">Blogs</div>
<div style="text-align: left;">
<table cellspacing="0">
<logic:present name="msg">
<bean:write name="msg"/>
</logic:present>
<logic:present name="result">
<logic:iterate id="blog" name="result">
<tr class="blogTableTR">
	<td>
		<h1><B>"<bean:write name="blog" property="blog_title" filter="false"/>"</B></h1>
	</td>
</tr>
<tr class="blogTableTR">
	<td>
		<bean:write name="blog" property="blog_content" filter="false"/>
	</td>
</tr>
<tr class="blogTableTR">
<td>
Posted By : <bean:write name="blog" property="posted_by"/> on <bean:write name="blog" property="posted_date"/> 
<br/>
Filed Under Category : <a href="#"><bean:write name="blog" property="category_name"/></a> 
<br/>
<a href="CM.do?ID=<bean:write name="blog" property="blog_id"/>">Comments (<bean:write name="blog" property="comment_Count"/>)</a> 
</td> 
</tr>
<tr><td>
<hr>
</td></tr>
</logic:iterate>
</logic:present>
</table>
</div>
</body>
</html>