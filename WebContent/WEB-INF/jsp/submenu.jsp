<%@ page language="java" contentType="text/html; charset=windows-31j"
    pageEncoding="windows-31j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
</head>
<script type="text/javascript">

var cur = 0;
function fillColor(){
	for(i=1;i<=4;i++){
		var img = document.getElementById(i);
		img.style.backgroundColor  = '#330033';
	}
}
function changeOver(i){
	var img = document.getElementById(i);
	if(i != cur)
	img.style.backgroundColor  = '#FF3300';
}
function changeOut(i){
	var img = document.getElementById(i);
	if(i != cur)
	img.style.backgroundColor  = '#330033';
}
function changeClick(i){	
	var img = document.getElementById(i);	
	img.style.backgroundColor  = '#FF3300';	
	cur = i;
	
	for(j=1;j<=4;j++){
		if(i!=j){
			var img = document.getElementById(j);		
			img.style.backgroundColor  = '#330033';
		}
	}
	
}
function doThought(){
	document.forms[0].target = "subcontent";
	document.forms[0].action = "TH.do";
	document.forms[0].submit();
}

function doStories(){
	document.forms[0].target = "subcontent";
	document.forms[0].action = "ST.do";
	document.forms[0].submit();
}

function doVideo(){
	document.forms[0].target = "subcontent";
	document.forms[0].action = "VD.do";
	document.forms[0].submit();
}
function doIdeal(){
	document.forms[0].target = "subcontent";
	document.forms[0].action = "ID.do";
	document.forms[0].submit();
}
function doAbout(){
	document.forms[0].target = "content";
	document.forms[0].action = "AU.do";
	document.forms[0].submit();
}
function doHome(){
	document.forms[0].target = "content";
	document.forms[0].action = "HM.do";
	document.forms[0].submit();
}
</script>
<body onload="fillColor();" class="menubody">
<form  action="TH.do" method="post">
<table>
<tr><td><img src="<%=request.getContextPath() %>/images/thought.png" id="1" width="70" height="25" 
		onmouseover="changeOver(1)" onmouseout="changeOut(1)" onclick="changeClick(1);doThought();"/></td></tr>
<tr><td><img src="<%=request.getContextPath() %>/images/story.png" id="2" width="70" height="25" 
		onmouseover="changeOver(2)" onmouseout="changeOut(2)" onclick="changeClick(2);doStories();"/></td></tr>
<tr><td><img src="<%=request.getContextPath() %>/images/video.png" id="3" width="70" height="25" 
		onmouseover="changeOver(3)" onmouseout="changeOut(3)" onclick="changeClick(3);doVideo();"/></td></tr>
<tr><td><img src="<%=request.getContextPath() %>/images/ideal.png" id="4" width="70" height="25" 
		onmouseover="changeOver(4)" onmouseout="changeOut(4)" onclick="changeClick(4);doIdeal();"/></td></tr>
</table>
</form>
</body>
</html>