<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library.dto.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>蔵書管理システム</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<%@ include file="../LoginUser.jsp" %>
<%@ include file="../CompleteCheck.jsp" %>
<%@ include file="../Msg.jsp" %>
<%request.setCharacterEncoding("UTF8");%>
<% User user = (User)session.getAttribute("user");
%>
</head>
<body>
<div class="headButton" align="right">
<br/>
<form method="post" action="<%=request.getContextPath()%>/OperationSelect.jsp" style="display: inline">
<input type="submit" class="Button-style-logout" value="メニュー">
</form>
&nbsp;
<form method="post" action="<%=request.getContextPath()%>/LibraryServlet" style="display: inline">
<input type="submit" class="Button-style-logout" value="ログアウト">
<input type="hidden" name="viewId" value="logout">
</form>&nbsp;</div>
<div class="header">ユーザ情報削除</div>

<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<input type="hidden" name="viewId" value="UserRemove">
<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>
<table border="0" style="display:<%=flagComplete %>">
<tbody>
   <tr>
     <td><label for="userId">ID</label></td>
     <td><%=request.getAttribute("userId") %></td>
   </tr>

   <tr>
     <td><label for="userName">ユーザ名</label></td>
     <td><%=request.getAttribute("userName") %></td>
   </tr>
   <tr>
     <td></td>
     <td><input type="submit" class="Button-style" value="削除"></td>

   </tr>

</tbody>

</table>

</form>


<table style="display:<%=flagMenu %>">
<tbody>
<tr>
<td>
<form method="post" action="<%=request.getContextPath()%>/OperationSelect.jsp">
<input type="submit" class="Button-style" value="メニューに戻る">
<input type="hidden" name="viewId" value="logout">
</form>
</td>
</tr>

</tbody>

</table>


<%--
<div class="article">
<a href="<%=request.getContextPath()%>/OperationSelect.jsp">メニューに戻る</a>
<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<input type="submit" value="ログアウト">
<input type="hidden" name="viewId" value="logout">
</form>
</div> --%>
</body>
</html>