<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">

<title>蔵書管理システム</title>
<%@ include file="Msg.jsp" %>
<%request.setCharacterEncoding("UTF8"); %>
</head>
<body>
<div class="header"><br/>蔵書管理システム</div>
<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<input type="hidden" name="viewId" value="login">

<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>
<table border="0">
<tbody>
   <tr>
     <td><label for="id">ID</label></td>
     <td><input required type="text" name="id"></td>
   </tr>
   <tr>
     <td><label for="pass">パスワード</label></td>
     <td><input required type="password" name="pass"></td>
   </tr>
   <tr>
     <td></td>
     <td></td>
   </tr>
   <tr>
     <td></td>
     <td><input type="submit" name="next" class="Button-style" value="ログイン"></td>
   </tr>
</tbody>
</table>

</form>
</body>
</html>