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
<% User user = (User)session.getAttribute("oldUser");
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
<div class="header">ユーザ情報更新</div>
<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<input type="hidden" name="viewId" value="UserUpdate">
<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>
<table border="0">
<tbody style="display:<%=flagComplete %>">
   <tr>
     <td><label for="userID">ID</label></td>
     <td><%=request.getAttribute("userId") %></td>
   </tr>
   <tr>
     <td><label for="pass">現在のパスワード</label></td>
     <td><input type="password" id="pass" name="pass" maxlength=30 pattern="^[0-9A-Za-z]{1,30}" title="30桁以下の半角英数"></td>
   </tr>

   <tr>
     <td><label for="newPass1">新しいパスワード</label></td>
     <td><input type="password" id="newPass1" name="newPass1" maxlength=30 pattern="^[0-9A-Za-z]{1,30}" title="30桁以下の半角英数"></td>
   </tr>

   <tr>
     <td><label for="newPass2">新しいパスワード（確認用）</label></td>
     <td><input type="password" id="newPass2" name="newPass2" maxlength=30 pattern="^[0-9A-Za-z]{1,30}" title="30桁以下の半角英数"></td>
   </tr>

   <tr>
     <td><label for="userName">ユーザ名</label></td>
     <td><input type="text" id="userName" name="userName" maxlength=100 value="<%=request.getAttribute("userName") %>"></td>
   </tr>
   <tr>
     <td></td>
     <td></td>
   </tr>
   <tr>
     <td></td>
     <td><input type="submit" class="Button-style" value="更新"></td>
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
</div>
 --%>
 </body>
</html>