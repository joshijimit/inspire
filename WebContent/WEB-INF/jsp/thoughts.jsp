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
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/js/richtext_compressed.js"></script>
<title>Insert title here</title>
</head>

<script type="text/javascript">
function insertData(form) {

	
	
	  updateRTEs();
	 
	  var auth = form.tauther;
	  var cont = form.tcontent;
	  
	  if(auth == null || auth.value == "")
	  {
		alert("Please Enter Auther Name First");
		auth.select();auth.focus();
		return false;
	  }
	  else if(cont == null || cont.value == "")
	  {
			alert("Please Enter Thought Content First");
			return false;
	  }
	  else
	  {
		form.method.value = "insert";  
		document.forms[0].action = "TH.do?method=input";
		document.forms[0].submit();
	  }
	  
}

function selectAll() {

	var chkAll = document.getElementById('chkAll');
	var inputs = document.getElementsByTagName('input');
	var checkboxes = [];
	var checked = false;
	
	if(chkAll.checked)
	{
		checked = true;
	}
	for (var i = 0; i < inputs.length; i++) 
	{
		//alert(inputs[i].id);
		if (inputs[i].type == 'checkbox' && inputs[i].id!='chkSrctcontent') {
			inputs[i].checked =checked;
		}
	}
}
function doApprove(form)
{
	
	var inputs = document.getElementsByTagName('input');
	var checkboxes = [];
	var idList ='';
	for (var i = 0; i < inputs.length-1; i++) 
	{
		if (inputs[i].type == 'checkbox') {
			
			if(inputs[i].checked && inputs[i].id != 'chkAll' && inputs[i].id != 'chkSrctcontent')
			{
				idList += inputs[i].id;
				if(i<inputs.length-5)
					idList = idList + ',';
			}
		}
	}
	
	if(idList != '')
	{
	form.update.value = idList;
	form.method.value = "insert";
	
	}
	else
	{
		alert('Please select atleast one Record to Approve');
	}
}
</script>
<body>
<form method="post"	action="TH.do">
<logic:present name="msg">
<div class="infoMsg">
<bean:write name="msg"/>
</div>
</logic:present>
<input type="hidden" name="method"/>
<input type="hidden" name="update"/>
<div class="label">Thoughts</div>
<br>
<div style="text-align: left;">

<table class="addthought">
<tr>
<td>
Language
</td>
<td>:</td>
<td>
<html:select name="Thought" property="sel_Lang_Id" style="width:158px;">
	<bean:define name="Thought" property="lagList" id="llist" /> 
		<html:optionsCollection name="llist" value="languageId" label="languageName"/>
</html:select>
</td>
</tr>
<tr>
	<td>Search By Auhor</td>
	<td>:</td>
	<td><input type="text" name="auther"/></td>
	<td><input type="submit" value="Search"/></td>
</tr>
</table>
</div>
<bean:define id="chkAll" name="chkAll"></bean:define>
<bean:define id="size" name="size"></bean:define>
<br>
<div style="text-align: left;">
<display:table id="result "name="result" class="thoughtTable" 
pagesize="5" partialList="true" size="<%=Integer.parseInt(size.toString()) %>"
requestURI="TH.do" decorator="org.inspire.decorator.ThoughtDecorator">
<display:setProperty name="paging.banner.placement" value="both"/>
<logic:present name="sessionName">
<logic:equal name ="sessionName" value="admin@inspire.com">
<display:column headerClass="authorHeader" class="thoughtAuthor" property="tid" title="<%=chkAll.toString() %>"></display:column>
</logic:equal>
</logic:present>
<display:column headerClass="contentHeader" class="thoughtContent" property="tcontent" title="Thoughts">
</display:column>
</display:table>
</div>
<br/>
<logic:present name="sessionName">
<logic:equal name ="sessionName" value="admin@inspire.com">
<input type="submit" value="Approve" onclick="doApprove(this.form)"/>
<br/><br/>
</logic:equal>
</logic:present>

<div class="label">Suggest your Thought</div>
<div id="error" class="error">
</div>
<div id="out" class="message">
</div>
<div style="text-align: left;">
<table class="addthought">
<tr>
	<td>Auhor</td>
	<td>:</td>
	<td><input type="text" name="tauther"/></td>
</tr>
<tr>
<td>
Language
</td>
<td>:</td>
<td>
<html:select name="Thought" property="sel_SugLang_Id" style="width:158px;">
	<bean:define name="Thought" property="sugLagList" id="llist" /> 
		<html:optionsCollection name="llist" value="languageId" label="languageName"/>
</html:select>
</td>
<tr>
<tr><td>&nbsp;</td></tr>
<tr>
	<td>Thought</td>
	<td>:</td>
	<td>
	<div style="background-color: white">
<script language="JavaScript" type="text/javascript">
<!--

//Usage: initRTE(imagesPath, includesPath, cssFile, genXHTML, encHTML)
initRTE("<%=request.getContextPath() %>/images/", "<%=request.getContextPath() %>/cbrte/", "<%=request.getContextPath() %>/css/", false,false);
//-->
</script>
<noscript><p><b>Javascript must be enabled to use this form.</b></p></noscript>

<script language="JavaScript" type="text/javascript">
<!--
//build new richTextEditor
var tcontent = new richTextEditor('tcontent');
tcontent.html = '';

//enable all commands for demo
tcontent.cmdFormatBlock = true;
tcontent.cmdFontName = true;
tcontent.cmdFontSize = true;
tcontent.cmdIncreaseFontSize = true;
tcontent.cmdDecreaseFontSize = true;

tcontent.cmdBold = true;
tcontent.cmdItalic = true;
tcontent.cmdUnderline = true;
tcontent.cmdStrikethrough = true;
tcontent.cmdSuperscript = true;
tcontent.cmdSubscript = true;

tcontent.cmdJustifyLeft = true;
tcontent.cmdJustifyCenter = true;
tcontent.cmdJustifyRight = true;
tcontent.cmdJustifyFull = true;

tcontent.cmdInsertHorizontalRule = true;
tcontent.cmdInsertOrderedList = true;
tcontent.cmdInsertUnorderedList = true;

tcontent.cmdOutdent = true;
tcontent.cmdIndent = true;
tcontent.cmdForeColor = true;
tcontent.cmdHiliteColor = true;
tcontent.cmdInsertLink = true;
tcontent.cmdInsertImage = true;
tcontent.cmdInsertSpecialChars = true;
tcontent.cmdInsertTable = true;
tcontent.cmdSpellcheck = true;

tcontent.cmdCut = true;
tcontent.cmdCopy = true;
tcontent.cmdPaste = true;
tcontent.cmdUndo = true;
tcontent.cmdRedo = true;
tcontent.cmdRemoveFormat = true;
tcontent.cmdUnlink = true;

tcontent.toggleSrc = false;

tcontent.build();
//-->
</script>

</div>
	</td>
</tr>
<tr><td align="right" colspan="3"><input type="submit" value="submit" onclick="return insertData(this.form);"/></td></tr>
</table>
</div>
</form>
</body>
</html>