<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>
</head>
<script type="text/javascript">
function doReset()
{
	document.forms[0].oldPass.value = "";
	document.forms[0].newPass.value = "";
	document.forms[0].confirmPass.value = "";
}
</script>
<body>
<div class="label">My Profile</div>
<div id="error" class="error">
<logic:present name="error">
<logic:iterate id="err" name="error">
<bean:write name="err"/><br/>
</logic:iterate>
</logic:present>
</div>
<html:form action="UP.do" enctype="multipart/form-data" method="POST"  onsubmit="return validateIdeal(this);">
<div class="message">
<logic:present name="msg">
<bean:write name="msg"/>
</logic:present>
</div>
<div style="text-align: left;">
<table>
<tr>
<td>
First Name
</td>
<td>:</td>
<td><html:text name="user" property="fname"/>
</td>
</tr>
<tr>
<td>
Middle Name
</td>
<td>:</td>
<td><html:text name="user" property="mname"/>
</td>
</tr>
<tr>
<td>
Last Name
</td>
<td>:</td>
<td><html:text name="user" property="lname"/>
</td>
</tr>
<tr>
<td>
E-Mail Address
</td>
<td>:</td>
<td><html:text name="user" property="email" />
</td>
</tr>
<tr>
<td>
Web-Site
</td>
<td>:</td>
<td><html:text name="user" property="website"/>
</td>
</tr>
<tr>
	<td>User Picture</td>
	<td>:</td>
	<td><img src="<bean:write name="upicpath"/>" height="100" name="uImg" width="100">
	<html:hidden name="user" property="currentImg"/>
	</td>
</tr>
<tr>
<td></td>
<td></td>
	<td><html:file name="user" property="upic"></html:file></td>
</tr>
<tr>
<td align="right" colspan="3">
<input type="submit" name="Submit" value="Submit"/>
<input type="button" name="Reset" value="Reset" onclick="doReset();"/>
</td>
</tr>
</table>
</div>
</html:form>
</body>
</html>