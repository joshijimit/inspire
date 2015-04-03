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
	<meta name="keywords" content="cross-browser rich text editor, rte, textarea, htmlarea, content management, cms, blog, internet explorer, firefox, safari, opera, netscape, konqueror" />
	<meta name="description" content="The cross-browser rich-text editor (RTE) is based on the designMode() functionality introduced in Internet Explorer 5, and implemented in Mozilla 1.3+ using the Mozilla Rich Text Editing API." />
	<meta name="author" content="Kevin Roth" />
	<meta name="ROBOTS" content="ALL" />
	<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/js/html2xhtml.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath() %>/js/richtext_compressed.js"></script>
<title>Ideals</title>
</head>
<link href="<%=request.getContextPath() %>/css/css.css"/>
<script type="text/javascript">
function getHTTPObject() {
    if (typeof XMLHttpRequest != 'undefined') {
        return new XMLHttpRequest();
    }
    try {
        return new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            return new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {}
    }
    return false;
}
var http = getHTTPObject();

function doSearch(form) {	 
	  var name = form.name;
	  form.action.value = "search";
	  
}
function dosubmit(form)
{
	form.update.value = "";
	form.action.value = "insert";
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
	form.action.value = "insert";	
	}
	else
	{
		alert('Please select atleast one Record to Approve');
	}
}
</script>
<body>
<html:form enctype="multipart/form-data" method="POST" action="ID.do" onsubmit="return validateIdeal(this);">
<input type="hidden" name="action"/>
<input type="hidden" name="update"/>
<logic:present name="msg">
<div class="infoMsg">
<bean:write name="msg"/>
</div>
</logic:present>
<div class="label">Ideals</div>

<br>
<div style="text-align: left;">
<table class="addthought">
<tr>
	<td>Search By Ideal Name</td>
	<td>:</td>
	<td><input type="text" name="name"/></td>
	<td><input type="submit" value="Search" onclick="doSearch(this.form)"/></td>
</tr>
</table>
</div>
<br>
<bean:define id="chkAll" name="chkAll"></bean:define>
<bean:define id="size" name="size"></bean:define>
<div style="text-align: left;">
<display:table name="result" class="thoughtTable" pagesize="5" 
partialList="true" size="<%=Integer.parseInt(size.toString()) %>"
 requestURI="ID.do" decorator="org.inspire.decorator.IdealDecorator">
<display:setProperty name="paging.banner.placement" value="both"/>
<logic:present name="sessionName">
<logic:equal name ="sessionName" value="admin@inspire.com">
<display:column headerClass="authorHeader" class="thoughtAuthor" property="vid" title="<%=chkAll.toString() %>"></display:column>
</logic:equal>
</logic:present>
<display:column headerClass="idealHeader" class="idealPic" property="ipicpath" title="Photo" ></display:column>
<display:column headerClass="contentHeader" class="idealDesc" property="idesc" title="Description" ></display:column>
</display:table>
</div>
<br/>
<logic:present name="sessionName">
<logic:equal name ="sessionName" value="admin@inspire.com">
<input type="submit" value="Approve" onclick="doApprove(this.form)"/>
<br/>
</logic:equal>
</logic:present>
<br>
<div class="label">Suggest your Ideal</div>
<div id="error" class="error">
<logic:present name="error">
<logic:iterate id="err" name="error">
<bean:write name="err"/><br/>
</logic:iterate>
</logic:present>
</div>
<div id="out" class="message">
</div>
<div style="text-align: left;">
<table class="addthought">
<tr>
	<td>Ideal Name</td>
	<td>:</td>
	<td><html:text property="iname"/></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
	<td>Ideal Photo</td>
	<td>:</td>
	<td><html:file disabled="false" property="ipics"></html:file></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
	<td>Description</td>
	<td>:</td>
	<td>
	<html:textarea property="idesc" cols="55" rows="10"></html:textarea>
	</td>
</tr>
<tr><td align="right" colspan="3"><html:submit onclick="dosubmit(this.form)">Submit</html:submit></td></tr>
</table>
</div>
</html:form>
</body>
</html>