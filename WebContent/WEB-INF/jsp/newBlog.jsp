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
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/js/html2xhtml.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/js/richtext_compressed.js"></script>
<script type="text/javascript">
function doPublish(form)
{
	updateRTEs();
	form.action.value = "publish";
}
function doSubmit(form)
{
	updateRTEs();
	form.action.value = "submit";
}
</script>
<body>
<form action="BGI.do" method="post">
<input type="hidden" name="action">
<div class="label">Publish Your Blog</div>
<br/>
<div id="error" class="error">
<logic:present name="error">
<logic:iterate id="err" name="error">
<bean:write name="err"/><br/>
</logic:iterate>
</logic:present>
</div>
<div style="text-align: left;">
<table class="addthought">
<tr>
<td align="center" colspan="3">
<logic:present name="msg">
	<font color="RED"><bean:write name="msg"/></font>
</logic:present>

</td>
</tr>
<tr>
	<td>Category</td>
	<td>:</td>
	<td>
	<html:select name="NewBlog" property="sel_Category_ID" style="width:158px;">
	<bean:define name="NewBlog" property="catList" id="clist" /> 
		<html:optionsCollection name="clist" value="category_ID" label="category_Name"/>
 	</html:select>	
	</td>
</tr>
<tr>
	<td>Title</td>
	<td>:</td>
	<td><html:text name="NewBlog" property="title"/></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
	<td>Content</td>
	<td>:</td>
	<td>
	<!-- <textarea name="scontent" cols="70" rows="15" name="content"></textarea> -->
	<div style="background-color: white;">
<bean:define name="NewBlog" id="content"></bean:define>

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
var content = new richTextEditor('content');
content.html = '';

//enable all commands for demo
content.cmdFormatBlock = true;
content.cmdFontName = true;
content.cmdFontSize = true;
content.cmdIncreaseFontSize = true;
content.cmdDecreaseFontSize = true;

content.cmdBold = true;
content.cmdItalic = true;
content.cmdUnderline = true;
content.cmdStrikethrough = true;
content.cmdSuperscript = true;
content.cmdSubscript = true;

content.cmdJustifyLeft = true;
content.cmdJustifyCenter = true;
content.cmdJustifyRight = true;
content.cmdJustifyFull = true;

content.cmdInsertHorizontalRule = true;
content.cmdInsertOrderedList = true;
content.cmdInsertUnorderedList = true;

content.cmdOutdent = true;
content.cmdIndent = true;
content.cmdForeColor = true;
content.cmdHiliteColor = true;
content.cmdInsertLink = true;
content.cmdInsertImage = true;
content.cmdInsertSpecialChars = true;
content.cmdInsertTable = true;
content.cmdSpellcheck = true;

content.cmdCut = true;
content.cmdCopy = true;
content.cmdPaste = true;
content.cmdUndo = true;
content.cmdRedo = true;
content.cmdRemoveFormat = true;
content.cmdUnlink = true;

content.toggleSrc = false;
//content.html = 'jimi&nbsp; <div><br /></div>';
setContent('<bean:write name="NewBlog" property="content" filter="false"/>');
content.build();
//-->
function setContent(cont)
{
	content.html = cont;
}
</script>
</div>
	</td>
</tr>
<tr>
<td align="right" colspan="3">
<input type="submit" value="Save" onclick="doSubmit(this.form);"/>
<input type="submit" value="Publish" onclick="doPublish(this.form);"/></td>
</tr>
</table>
</div>
</form>
</body>
</html>