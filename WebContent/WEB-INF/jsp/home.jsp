<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><bean:message key="Title"/></title>
<LINK REL="SHORTCUT ICON" HREF="<%=request.getContextPath() %>/favicon.ico">
</head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<script type="text/javascript">
function doThought(){
	
	document.forms[0].action = "TH.do";
	document.forms[0].submit();
}

function doStories(){
	
	document.forms[0].action = "ST.do";
	document.forms[0].submit();
}

function doVideo(){
	
	document.forms[0].action = "VD.do";
	document.forms[0].submit();
}
function doIdeal(){
	
	document.forms[0].action = "ID.do";
	document.forms[0].submit();
}
function doLocaleChange(lang)
{	
	document.forms[0].action = "HM.do?lang=" + lang.value;
	document.forms[0].submit();	
}
</script>
<body class="menubody">

<form method="post" action="LG.do">
<table width="100%" height="100%">
<tr>
<td width="100%" class="homeTD">
<div class="homeLabel"><bean:message key="HomeTitle"/></div>
<table width="100%" height="100%" class="homeTable">
<tr>
<td width="40%" align="center" class="homeLabels">Thoughts</td>
<td width="40%" align="center" class="homeLabels">Story</td>
</tr>
<tr>
<td width="50%" align="center" class="homeTD">
<table width="100%" height="180px">
<tr><td align="center" class="contDispFont">
<logic:iterate id="thought" name="resTH">
<table cellspacing="5" width="100%">
<tr><td align="center"><bean:write name="thought" property="tcontent" filter="false"/></td></tr>
<tr><td align="right"> - <bean:write name="thought" property="tauther" filter="false"/></td></tr>
</table>
</logic:iterate>
</td></tr>
<tr>
<td align="right" style="vertical-align: bottom;">
<a href="TH.do">View More</a>
</td>
</tr>
</table>
</td>
<td width="50%" align="center" class="homeTD">
<table width="100%">
<tr>
<td align="center" class="contDispFont">
<logic:iterate id="story" name="resST">
<table cellspacing="5" width="100%">
<tr><td align="center"><bean:write name="story" property="stitle" filter="false"/></td></tr>
<tr><td align="left">
<div style="width: 300px;height: 110px;overflow: hidden;">
<bean:write name="story" property="scontent" filter="false"/>
</div><a href="ST.do?ID=<bean:write name="story" property="sid" filter="false"/>" class="info">Read More</a>
</td></tr>
</table>
</logic:iterate>
</td></tr>
<tr>
<td align="right">
<a href="ST.do">View More</a>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td width="50%" align="center" class="homeLabels">Video</td>
<td width="50%" align="center" class="homeLabels">Ideal</td>
</tr>
<tr>
<td width="50%" align="center" class="homeTD">
<table width="100%">
<tr><td align="center" class="contDispFont">
<logic:iterate id="video" name="resVD">
<table cellspacing="5">
<tr><td align="center">
<bean:write name="video" property="vcontent" filter="false"/></td></tr>
<tr><td align="center"><bean:write name="video" property="vtitle" filter="false"/></td></tr>
</table>
</logic:iterate>
</td></tr>
<tr>
<td align="right">
<a href="VD.do">View More</a>
</td>
</tr>

</table>
</td>
<td width="50%" align="center" class="homeTD">
<table width="100%">
<tr><td align="center" class="contDispFont">
<logic:iterate id="ideal" name="resID">
<table cellspacing="5">
<tr><td align="center"><img src="<bean:write name="ideal" property="ipicpath"/>"
 width="145px" height="150px"/></td></tr>
<tr><td align="center"><bean:write name="ideal" property="iname" filter="false"/></td></tr>
</table>
</logic:iterate>
</td></tr>
<tr>
<td align="right">
<a href="ID.do">View More</a>
</td>
</tr>
</table>
</td>
</tr>
<!-- start Adds -->
<tr>
<td width="50%" align="center" class="homeLabels">Advertisement</td>
<td width="50%" align="center" class="homeLabels">Advertisement</td>
</tr>
<tr>
<td width="50%" align="center" class="homeTD">
<table width="100%">
<tr><td align="center" class="contDispFont">
<!-- begin: pogads -->
<script language="javascript" type="text/javascript" src="http://ads1.pogads.com/show_ad.php?zid=26580&amp;sz=120x240"></script>
<a href="http://www.pogads.com/lead-generation-management.php" title="Lead Generation" id="pogads_site26580" class='ad_adv26580 ad_headline26580 ad_table26580' target='_blank'>Lead Generation</a>
<!-- end: pogads -->
</td></tr>
</table>
</td>
<td width="50%" align="center" class="homeTD">
<table width="100%">
<tr><td align="center" class="contDispFont">
<!-- begin: pogads -->
<script language="javascript" type="text/javascript" src="http://ads1.pogads.com/show_ad.php?zid=26580&amp;sz=120x240"></script>
<a href="http://www.pogads.com/lead-generation-management.php" title="Lead Generation" id="pogads_site26580" class='ad_adv26580 ad_headline26580 ad_table26580' target='_blank'>Lead Generation</a>
<!-- end: pogads -->
</td></tr>
<!-- end adds -->
<!-- <tr><td colspan="2">
 <table>
<tr>	
<td>Language</td>
<td><select id="lang" onchange="doLocaleChange(this.options[this.selectedIndex])"
	>
  <option value="English">--Select--</option>	
  <option value="English">English</option>
  <option value="Japanese">日本語</option>
  <option value="Gujarati">ગુજરાતી</option>
  <option value="Hindi">हिन्दी</option>
  <option value="Marathi">मराठी</option>
</select></td>
</tr>
</table>
</td></tr>-->
</table>
</td>
</tr>
</table>
</form>

</body>
</html>