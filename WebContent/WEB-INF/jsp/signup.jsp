<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
</head>
<body >
<html:form action="SU.do" method="post" onsubmit="return validateSignup(this);">

<html:errors/>
<logic:present name="msg">
<div class="message">
	<bean:write name="msg"/>
</div>
</logic:present>
<logic:notPresent name="msg">
<div class="signUpDiv">
<table>
<tr>
	<td>Email Address</td>
	<td>：</td>
	<td><html:text property="email" size="30" maxlength="30" style="width: 200px;"/>
	</td>
</tr>
<tr>
	<td>First Name</td>
	<td>：</td>
	<td><input type="text" name="fname"></td>
</tr>
<tr>
	<td>Last Name</td>
	<td>：</td>
	<td><input type="text" name="lname"></td>
</tr>
<tr>
	<td>Password</td>
	<td>：</td>
	<td><input type="password" name="pass"></td>
</tr>
<tr>
	<td>Confirm Password</td>
	<td>：</td>
	<td><input type="password" name="cnfpass"></td>
</tr>
<tr><td colspan="3" align="right"><html:submit>submit</html:submit></td></tr>
</table>
</div>
</logic:notPresent>
<html:javascript formName="signup"/>
</html:form>
</body>
</html:html>