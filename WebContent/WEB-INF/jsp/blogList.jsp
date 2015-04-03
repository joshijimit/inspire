<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog List</title>
</head>
<body>
<form action="BGL.do" method="post">
<div class="label">Your Blog List</div>
<br/>
<div style="text-align: left;">
<table class="blogListTable" width="100%">
<tr>
<td>
<display:table name="result" class="thoughtTable" pagesize="5" requestURI="BGL.do" decorator="org.inspire.decorator.BlogDecorator">

<display:column  headerClass="authorHeader" class="thoughtAuthor" property="blog_id" title="Edit"></display:column>
<display:column headerClass="authorHeader" class="thoughtContent" property="posted_by" title="Delete" ></display:column>
<display:column headerClass="authorHeader" class="thoughtAuthor" property="blog_title" title="Title"></display:column>
<display:column headerClass="authorHeader" class="thoughtContent" property="posted_date" title="Posted On" ></display:column>

</display:table>
</td>
</tr>
</table>
</div>
</form>
</body>
</html>