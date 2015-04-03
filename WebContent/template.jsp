<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css"> 
#fixme { position:absolute; right:-1px; top:0px; width:30px; padding-top:270px;}
#fixmetoo { position: absolute; right: 0px; bottom: 0px; width:100%;  background:#f7f3f7 }
div > div#fixme { position: fixed; }
div > div#fixmetoo { position: fixed; }
pre.fixit { overflow:auto;border-left:1px dashed #000;border-right:1px dashed #000;padding-left:2px; }
#feedback:hover{ background-position:0 -80px}
#feedback span{ display:none}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" ignore="true"/></title>
</head>
<script type="text/javascript">
function showContactUS()
{
	document.forms[0].action = "CU.do";
	document.forms[0].submit();
}
</script>
<body>

<div style=" position:relative; top:0; right:0; width:30px; float:right; text-align:right; clear:both;">      
   <div id="fixme"><a href="JavaScript:void(0);" onclick="showContactUS();"><img src="<%=request.getContextPath() %>/images/feed_blu.gif" align="right" /></a> 
       
        </div>	
        </div>
 
<div align="center">
<table>
<tr>
<td width="1024px"><tiles:insert attribute="header"/></td>
</tr>
<tr>
<td width="1024px"><tiles:insert attribute="body"/>	
</td>
</tr>
<tr>
<td width="1024px"><tiles:insert attribute="footer"/></td>
</tr>
</table>
</div>
</body>
</html>