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
<frameset rows="13%,80%,8%">
   <frame name="menu" src="MN.do" frameborder="0" scrolling="no" >
   <frame name="content" src="HM.do" frameborder="0">
   <frame name="footer" src="FT.do" frameborder="0" scrolling="no"> 
</frameset>
</html>