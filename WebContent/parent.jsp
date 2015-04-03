<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
 function openChild(){
	window.open("child.jsp","new");
 }
 function refresh(value,href){	 
	document.getElementById("a1").value = value; 
	document.getElementById("a1").innerText = href; 
 }
 

</script>
<body>
<form method="get"></form>
	<input type="button" onclick="openChild()" value="OpenChild">
	<a id="a1" href="#" ></a>
</form>
</body>
</html>