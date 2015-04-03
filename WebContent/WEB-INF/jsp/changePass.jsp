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
<div class="label">Change Password</div>

<form action="CP.do" method="post">
<div id="error" class="error">
<logic:present name="error">
<logic:iterate id="err" name="error">
<bean:write name="err"/><br/>
</logic:iterate>
</logic:present>
</div>
<div class="message">
<logic:present name="msg">
<bean:write name="msg"/>
</logic:present>
</div>
<div style="text-align: left;">
<table>
<tr>
<td>
Old Password :
</td>
<td><html:password name="user" property="oldPass"/>
</td>
</tr>
<tr>
<td>
New Password :
</td>
<td><html:password name="user" property="newPass"/>
</td>
</tr>
<tr>
<td>
Confirm Password :
</td>
<td><html:password name="user" property="confirmPass"/>
</td>
</tr>
<tr>
<td align="right" colspan="2">
<input type="submit" name="Submit" value="Submit"/>
<input type="button" name="Reset" value="Reset" onclick="doReset();"/>
</td>
</tr>
</table>
</div>
</form>
</body>
</html>