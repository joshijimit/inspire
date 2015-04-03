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
	
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/js/html2xhtml.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/js/richtext_compressed.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">

function insertData(form) {
	  /*var myurl = "TSI.do";
	  var auth = form.author.value;
	  var cont = form.content.value;
	  http.open("GET", myurl + "?title=" + escape(auth)+"&cont="+escape(cont), true);
	  http.onreadystatechange = useHttpResponse;
	  http.send(null);*/
	  updateRTEs();
	  var title = form.stitle;
	  var cont = form.scontent;
	  if(title == null || title.value == "")
	  {
		alert("Please Enter Title Name First");
		title.select();title.focus();
		return false;
	  }
	  else if(cont == null || cont.value == "")
	  {
			alert("Please Enter Story Content First");
			return false;
	  }
	  else
	  {
		form.method.value = "insert";
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
	for (var i = 0; i < inputs.length; i++) 
	{
		if (inputs[i].type == 'checkbox') {
			
			if(inputs[i].checked && inputs[i].id != 'chkAll' && inputs[i].id != 'chkSrcscontent')
			{
				
				idList += inputs[i].id;
				if(i<inputs.length-5)
					idList = idList + ',';
			}
		}
	}
	if(idList != '')
	{
	form.method.value = "insert";
	form.update.value = idList;
	}
	else
	{
		alert('Please select atleast one Record to Approve');
	}
}
function getfilterList(form)
{
	alert("hi");
	var lang = form.sel_Lang_Id.value;
	document.forms[0].action = "ST.do?language="+lang;
}
</script>

<body>

<form method="post" action="ST.do">
<logic:present name="msg">
<div class="infoMsg">
<bean:write name="msg"/>
</div>
</logic:present>
<input type="hidden" name="method"/>
<input type="hidden" name="update"/>
 <div class="label">Stories</div>
<br><br> 
<table>
<tr>
<td>
Language :
</td>
<td>
<html:select name="story" property="sel_Lang_Id" style="width:158px;">
	<bean:define name="story" property="lagList" id="llist" /> 
		<html:optionsCollection name="llist" value="languageId" label="languageName"/>
</html:select>
</td>
<td><input type="submit" value="Search"/></td>
</tr>
</table>	
<br>
<bean:define id="chkAll" name="chkAll"></bean:define>
<logic:present name="title">
<bean:define id="title" name="title"></bean:define>
<bean:define id="size" name="size"></bean:define>
<div style="text-align: left;">
<br/>
<display:table name="result" class="thoughtTable" pagesize="5" 
 requestURI="ST.do"
decorator="org.inspire.decorator.StoryDecorator">
<display:setProperty name="paging.banner.placement" value="both"/>
<logic:present name="sessionName">
<logic:equal name ="sessionName" value="admin@inspire.com">
<display:column headerClass="authorHeader" class="thoughtAuthor" property="sid" title="<%=chkAll.toString() %>"></display:column>
</logic:equal>
</logic:present>
<display:column headerClass="contentHeader" class="storyContent" property="scontent" title="Stories" ></display:column>
</display:table>
</div>
</logic:present>

<logic:notPresent name="title">
<div id="out" class="message">
No Story found to display
</div>
</logic:notPresent>
<br/>
<br/>
<logic:present name="sessionName">
<logic:equal name ="sessionName" value="admin@inspire.com">
<input type="submit" value="Approve" onclick="doApprove(this.form)"/>
<br/><br/>
</logic:equal>
</logic:present>
<div class="label">Suggest your Story</div>

<logic:present name="done">
<div id="out" class="message">
"You can see your Story after being approved."
</div>
</logic:present>
<div style="text-align: left;">
<table class="addthought">
<tr>
	<td>Title</td>
	<td>:</td>
	<td><input type="text" name="stitle" style="width: 250px;"/></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
<td>
Language
</td>
<td>:</td>
<td>
<html:select name="story" property="sel_SugLang_Id" style="width:158px;">
	<bean:define name="story" property="sugLagList" id="llist" /> 
		<html:optionsCollection name="llist" value="languageId" label="languageName"/>
</html:select>
</td>
<tr><td>&nbsp;</td></tr>
</tr>
<tr>
	<td>Story</td>
	<td>:</td>
	<td>
	<!-- <textarea name="scontent" cols="70" rows="15" name="content"></textarea> -->
	<div style="background-color: white;">
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
var scontent = new richTextEditor('scontent');
scontent.html = '';

//enable all commands for demo
scontent.cmdFormatBlock = true;
scontent.cmdFontName = true;
scontent.cmdFontSize = true;
scontent.cmdIncreaseFontSize = true;
scontent.cmdDecreaseFontSize = true;

scontent.cmdBold = true;
scontent.cmdItalic = true;
scontent.cmdUnderline = true;
scontent.cmdStrikethrough = true;
scontent.cmdSuperscript = true;
scontent.cmdSubscript = true;

scontent.cmdJustifyLeft = true;
scontent.cmdJustifyCenter = true;
scontent.cmdJustifyRight = true;
scontent.cmdJustifyFull = true;

scontent.cmdInsertHorizontalRule = true;
scontent.cmdInsertOrderedList = true;
scontent.cmdInsertUnorderedList = true;

scontent.cmdOutdent = true;
scontent.cmdIndent = true;
scontent.cmdForeColor = true;
scontent.cmdHiliteColor = true;
scontent.cmdInsertLink = true;
scontent.cmdInsertImage = true;
scontent.cmdInsertSpecialChars = true;
scontent.cmdInsertTable = true;
scontent.cmdSpellcheck = true;

scontent.cmdCut = true;
scontent.cmdCopy = true;
scontent.cmdPaste = true;
scontent.cmdUndo = true;
scontent.cmdRedo = true;
scontent.cmdRemoveFormat = true;
scontent.cmdUnlink = true;

scontent.toggleSrc = false;

scontent.build();
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