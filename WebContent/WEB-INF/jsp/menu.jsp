<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<LINK REL="SHORTCUT ICON" HREF="<%=request.getContextPath() %>/favicon.ico">
<style type="text/css">
.class1 A:link {text-decoration: none;color: #002185;}
.class1 A:visited {text-decoration: none;color: #002185; font-weight: bold;}
.class1 A:active {text-decoration: none;  color: #FF3300;}
.class1 A:hover {text-decoration: underline; color: red;}

</style>
</head>

<script type="text/javascript">
function doHome(){
	document.forms[0].action = "HM.do";
	document.forms[0].submit();
}
function doInspire(){
	
	document.forms[0].action = "IS.do";
	document.forms[0].submit();
}
function doBlog(){
	
	document.forms[0].action = "BG.do";
	document.forms[0].submit();
}
function doContact(){
	document.forms[0].target = "content";
	document.forms[0].action = "CU.do";
	document.forms[0].submit();
}
function doAbout(){
	
	document.forms[0].action = "AU.do";
	document.forms[0].submit();
}

function doSubscribe(form){
	
	if(validate(form.email))
	{		
		document.forms[0].action = "SU.do?email="+form.email.value;
		document.forms[0].submit();		
				
	}else
	{
		return false;
	}
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
function doLogin(form){

	
	var user = form.user;
	var pass= form.pass;
	
	if(validate(user))
	{
		if( pass.value != null && pass.value != "")
		{						
			document.forms[0].action = "LG.do?user="+escape(user.value)+"&pass="+escape(pass.value);
			document.forms[0].submit();	
					
		}
		else
		{
			alert("Please Enter Password");
			pass.select(); pass.focus();
			return false;
		}
	}
}

function showMenu(color) {
	document.forms[0].action = "MN.do?color=" + color;
	document.forms[0].submit();
}
function doLocaleChange(lang)
{	
	document.forms[0].action = "MN.do?lang=" + lang.value;
	document.forms[0].submit();
	
}
function doRefresh()
{
	document.forms[0].action = "LO.do";
	document.forms[0].submit();
	
}
function doUpload()
{
	document.forms[0].action = "UB.do";
	document.forms[0].submit();
	window.parent.reload();
}
</script>

<logic:present name="sessionName" scope="session">
<bean:define id="bgname" name="sessionName"></bean:define>
</logic:present>
<logic:notPresent name="sessionName" scope="session">
<bean:define id="bgname" value="Header2"></bean:define>
</logic:notPresent>

<body style="background : url(images/<bean:write name="bgname"/>.jpg)" class="menubody">
<form  method="post" enctype="multipart/form-data">

<table width="100%">
<tr>
<td rowspan="2" style="vertical-align: baseline;"><img src="<%=request.getContextPath() %>/images/logo.png" alt="Inspiration" 
	width="270px"/></td>
<td style="vertical-align: top;" align="right">

<logic:present name="session">

<div class="loginDiv">
<table>
<tr>
<td align="left" colspan="5">
<logic:present name="msg">
<div class="message" >
<bean:write name="msg"/>
</div>
</logic:present>
</tr>
<tr>
	<td>Enter Your Email</td>
	<td>:</td>
	<td><input type="text" name="email"></td>
	<td><input type="button" value="Subscribe" onclick="doSubscribe(this.form);"></td>
	<td>
	<span class="class1">
	<a href="MN1.do">Back</a>
	</span>
	</td>
	</tr>
	</table>
</div>
</logic:present>
<logic:notPresent name="sessionName">
<logic:notPresent name="session">
<div class="loginDiv">
<table>
<tr>
<td align="left" colspan="9">
<logic:present name="error">
	<font color="RED"><bean:write name="error"/></font>
</logic:present>
</td>
</tr>
<tr>
	<td>Email</td>
	<td>:</td>
	<td><input type="text" name="user"></td>
	<td>&nbsp;</td>
	<td>Password</td>
	<td>:</td>
	<td><input type="password" name="pass"></td>
	<td>&nbsp;&nbsp;</td>
	<td><input type="button" value="Login" onclick="doLogin(this.form);"></td>
</tr>
<tr><td colspan="9" align="left">
If you are not a Member.Please subscribe Now.
<span class="class1"> 
<a href="SB.do">SubScribe</a>
</span>
</td>
</tr> 
</table>
</div>
</logic:notPresent>
</logic:notPresent>
<logic:present name="sessionName">
<table class="outerTable">
<tr><td align="right" colspan="2">
<font size="4" color="BLACK" style="font-weight: bold;">Welcome : <bean:write name="sessionName"/></font>
</td>
<tr>
<td align="right" colspan="2">
<span class="class1"> 
<a href="BGI.do" >New Blog</a>
</span> 
&nbsp;|&nbsp;
<span class="class1"> 
<a href="BGL.do" >My Blog List</a>
</span> 
&nbsp;|&nbsp;
<span class="class1"> 
<a href="UP.do?action=userProfile">My Profile</a>
</span> 
&nbsp;|&nbsp;
<span class="class1"> 
<a href="CP.do?action=changePass">Change Password</a>
</span>
&nbsp;|&nbsp;
<span class="class1"> 
<a href="#" onclick="doRefresh();">Logout</a>
</span> 
</td>
</tr>
<tr>
<td align="right">
Change Background Image : <input type="file" name="backImg"/> 
<input type="button" onclick="doUpload();" value="Save Changes">
</td>
</tr>
</table>
</logic:present>
</td>
</tr>
<tr>
<td align="right" class="menuTD">
<!-- 
<img src="<%=request.getContextPath() %>/images/home.png" id="1" width="60" height="25" 
		onmouseover="changeOver(1)" onmouseout="changeOut(1)" onclick="changeClick(1);doHome();"/>
		&nbsp;
		<img src="<%=request.getContextPath() %>/images/inspire.png" id="2" width="60" height="25" 
		onmouseover="changeOver(2)" onmouseout="changeOut(2)" onclick="changeClick(2);doInspire();"/>
		&nbsp;
		<img src="<%=request.getContextPath() %>/images/blog.png" id="3" width="60" height="25" 
		onmouseover="changeOver(3)" onmouseout="changeOut(3)" onclick="changeClick(3);doBlog();"/>
		&nbsp;
		<img src="<%=request.getContextPath() %>/images/contact.png" id="4" width="60" height="25" 
		onmouseover="changeOver(4)" onmouseout="changeOut(4)" onclick="changeClick(4);doContact();"/>
		&nbsp;
		<img src="<%=request.getContextPath() %>/images/about.png" id="5" width="60" height="25" 
		onmouseover="changeOver(5)" onmouseout="changeOut(5)" onclick="changeClick(5);doAbout();"/>
		 -->
<span class="class1"> 
<a href="HM.do">Home</a>
&nbsp;|&nbsp;
<a href="TH.do">Thoughts</a>
&nbsp;|&nbsp;
<a href="ST.do">Stories</a>
&nbsp;|&nbsp;
<a href="VD.do">Videos</a>
&nbsp;|&nbsp;
<a href="ID.do">Ideals</a>
&nbsp;|&nbsp;
<a href="BG.do">Blog</a>
&nbsp;|&nbsp;
<a href="BK.do">Book</a>
&nbsp;|&nbsp;
<a href="CU.do">Contact us</a>
&nbsp;|&nbsp;
<a href="AU.do">About us</a>		 
</td>
</tr>
</table>

</form>
</body>
</html>