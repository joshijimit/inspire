<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Contact us</title>
</head>
<script type="text/javascript">
function doClear(form)
{
	form.name.value = "";
	form.email.value = "";
	form.subject.value = "";
	form.content.value = "";
}
function validate(form)
{
	
	//check that the email address is valid
	var str = form.email.value	
	if(str == null || str == "")
	{		
		return true;
	}else
	{
		
		var isEmail = /^.+@.+..{2,3}$/;
		if (!(isEmail.test(str)))
		{
		   alert('Please enter a valid e-mail address.');
		   form.email.select(); form.email.focus();
		   return false;
		}
		return true;
	}
}
</script>
<body class="menubody">
<html:form action="CN.do">
<logic:present name="msg">
<div class="infoMsg">
<bean:write name="msg"/>
</div>
</logic:present>
<div class="label">Submit you Feedback or suggestion Here.</div>
<br/>
<div style="text-align: left;">
<font color="RED"><html:errors/></font>
<table>
<tr>
	<td>
	Name<font color="RED">*</font>
	</td>
	<td>:</td>
	<td>
	<html:text property="name"/>
	</td>
</tr>
<tr>
	<td>
	 Email Address<font color="RED">*</font>
	</td>
	<td>:</td>
	<td>
	<html:text property="email"/>
	</td>
</tr>
<tr>
	<td>
	 Subject<font color="RED">*</font>
	</td>
	<td>:</td>
	<td>
	<html:text property="subject"/>
	</td>
</tr>
<tr>
	<td>
	 Content<font color="RED">*</font>
	</td>
	<td>:</td>
	<td>
	<html:textarea property="content" cols="50" rows="10"></html:textarea>
	</td>
</tr>
<tr>
<td colspan="3" align="right">
<html:submit onclick="return validate(this.form)"/>
<input type="button" value="Clear" onclick="doClear(this.form);">
</td>
</tr>
</table>
</div>
</html:form>
</body>
</html>