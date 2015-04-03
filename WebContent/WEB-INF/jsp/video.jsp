<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>Insert title here</title>
</head>
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
function insertDataOld(form) {
	  var myurl = "VDI.do";
	  var title = form.vtitle;
	  var cont = form.vcontent;
	  if(title == null || title.value == "")
	  {
		alert("Please Enter Title Name First");
		title.select();title.focus();
		return false;
	  }
	  else if(cont == null || cont.value == "")
	  {
			alert("Please Enter Video Content First");
			cont.select();cont.focus();
			return false;
	  }
	  else
	  {
	  http.open("GET", myurl + "?title=" + escape(title.value)+"&cont="+escape(cont.value), true);
	  http.onreadystatechange = useHttpResponse;
	  http.send(null);
	  }
}
function useHttpResponse() {

	
	  if (http.readyState == 4) {

		  if(http.status == 10)
		  {
			document.getElementById("error").innerHTML = "You have to Logged in First to Upload your Video.";
		  }
		  else
		  {		  	
			document.getElementById("out").innerHTML = "You can see your video after being approved.";
		  }		  	  
		  	
	  }
	  document.forms[0].vtitle.value="";
	  	document.forms[0].vcontent.value="";
	
}
function insertData(form) {
	  var myurl = "VDI.do";
	  var title = form.vtitle;
	  var cont = form.vcontent;
	  if(title == null || title.value == "")
	  {
		alert("Please Enter Title Name First");
		title.select();title.focus();
		return false;
	  }
	  else if(cont == null || cont.value == "")
	  {
			alert("Please Enter Video Content First");
			cont.select();cont.focus();
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
	for (var i = 0; i < inputs.length-1; i++) 
	{
		if (inputs[i].type == 'checkbox') {
			
			if(inputs[i].checked && inputs[i].id != 'chkAll' && inputs[i].id != 'chkSrctcontent')
			{
				idList += inputs[i].id;
				if(i<inputs.length-2)
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
<form method="post" action="VD.do" name="Video">
<input type="hidden" name="method"/>
<input type="hidden" name="update"/>
<logic:present name="msg">
<div class="infoMsg">
<bean:write name="msg"/>
</div>
</logic:present>
<div class="label">Videos</div>
<br>
<div style="text-align: left;">
<table class="addthought">
<tr>
	<td>Search By Title</td>
	<td>:</td>
	<td><input type="text" name="title"/></td>
	<td><input type="submit" value="Search"/></td>
</tr>
</table>
</div>
<bean:define id="chkAll" name="chkAll"></bean:define>
<bean:define id="size" name="size"></bean:define>
<br>
<div style="text-align: left;">
<display:table name="result" class="videoTable" pagesize="2" 
partialList="true" size="<%=Integer.parseInt(size.toString()) %>"
 requestURI="VD.do" decorator="org.inspire.decorator.VideoDecorator">
<display:setProperty name="paging.banner.placement" value="both"/>
<display:setProperty name="paging.banner.page.link" value="<a href=\"{1}\" title=\"Go to page {0}\">{0}</a>"></display:setProperty>
<logic:present name="sessionName">
<logic:equal name ="sessionName" value="admin@inspire.com">
<display:column headerClass="authorHeader" class="thoughtAuthor" property="vid" title="<%=chkAll.toString() %>"></display:column>
</logic:equal>
</logic:present>
<display:column headerClass="videoHeader" class="videoContent" property="vcontent" title="Video" ></display:column>
<display:column headerClass="titleHeader" class="videoTitle" property="vtitle" title="Title"></display:column>
</display:table>
<br/>
<logic:present name="sessionName">
<logic:equal name ="sessionName" value="admin@inspire.com">
<input type="submit" value="Approve" onclick="doApprove(this.form)"/>
<br/>
</logic:equal>
</logic:present>
</div>
<br>
<div class="label">Suggest your Favorite Video</div>
<div id="error" class="error">
</div>
<div id="out" class="message">
</div>
<div style="text-align: left;">
<table class="addthought">
<tr>
	<td>Title</td>
	<td>:</td>
	<td><input type="text" name="vtitle"/></td>
</tr>
<tr>
	<td>Video</td>
	<td>:</td>
	<td><textarea name="vcontent" cols="30" rows="3"></textarea>
	</td>
</tr>
<tr><td align="right" colspan="3"><input type="submit" value="submit" onclick="return insertData(this.form);"/></td></tr>
</table>
</div>
</form>
</body>
</html>