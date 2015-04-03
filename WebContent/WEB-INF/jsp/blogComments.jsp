<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">
function setfocus()
{
	if(<%=request.getAttribute("error") != null%>)
	{
		document.forms[0].commenter_name.focus();
	}
}
function doValidate()
{
	var email = document.forms[0].commenter_email;
	var name = document.forms[0].commenter_name;
	var commnet = document.forms[0].comment_detail;
	if(name.value == "")
	{
		alert("Please enter Name");
		name.select(); name.focus();
		return false;
	}
	if(commnet.value == "")
	{
		alert("Please enter commnet");
		commnet.select(); commnet.focus();
		return false;
	}
	
	return validate(email);
}
function validate(email)
{
	//check that the email address is valid
	var str = email.value

	if(email.value == null || email.value == "")
	{
		alert("Please Enter Email Address");
		email.select(); email.focus();
		return false;
	}else
	{
		
		var isEmail = /^.+@.+..{2,3}$/;
		if (!(isEmail.test(str)))
		{
		   alert('Please enter a valid e-mail address.');
		   email.select(); email.focus();
		   return false;
		}
		return true;
	}
}
</script>
<body onload="setfocus();">
<div class="label">Blog Responses</div>
<logic:present name="msg">
<div class="message">
<bean:write name="msg"/>
</div>
</logic:present>
<div style="text-align: left;">
<table cellspacing="0" width="100%">
<logic:iterate id="blog" name="result">
<tr class="blogTableTR">
	<td>
		<h1><B>"<bean:write name="blog" property="blog_title" filter="false"/>"</B></h1>
		<input type="hidden" name="blog_id" value="<bean:write name="blog" property="blog_id" filter="false"/>">
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
</td> 
</tr>
</logic:iterate>
</table>
</div>
<br/>

<logic:iterate id="comm" name="resultCom">
<div style="text-align: left;">
<table class="commentTable">
<tr>
	<td width="5%">
		<img src="<bean:write name="comm" property="upic" filter="false"/>" alt="Image Not Available" 
		width="50px" height="50px">		
	</td>
	<td width="95%">
		<bean:write name="comm" property="commented_by" filter="false"/> says:<br/>
		<bean:write name="comm" property="commented_date" filter="false"/>
	</td>
</tr>
<tr>
	<td colspan="2">
		<bean:write name="comm" property="comment_detail" filter="false"/>
	</td>
</tr>
</table>
</div>
<br/>
</logic:iterate>
<br/>
<div class="label">Add your Comment</div>
<div id="error" class="error">
<logic:present name="error">
<logic:iterate id="err" name="error">
<bean:write name="err"/><br/>
</logic:iterate>
</logic:present>
</div>
<html:form action="CM.do?insert=true" >
<div style="text-align: left;">
(<font color="RED">*</font>) are MandatoryFields
<table>
<tr>
<td>
<html:text property="commenter_name"></html:text> <font color="RED">*</font> Name
</td>
</tr>
<tr>
<td>
<html:text property="commenter_email"></html:text> <font color="RED">*</font>Email
</td>
</tr>
<tr>
<td>
<html:text property="commenter_website"></html:text> Website(Will Not Publish)
</td>
</tr>
<tr>
<td>
<html:textarea property="comment_detail" cols="50" rows="8"></html:textarea>
<font color="RED">*</font>Comment
</td>
</tr>
<tr>
<td align="right">
<html:submit title="Sibmit" onclick="return doValidate();"></html:submit>
</td>
</tr>
</table>
</div>
</html:form>
</body>
</html>