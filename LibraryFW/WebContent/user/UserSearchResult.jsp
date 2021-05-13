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
<%@ include file="../Msg.jsp" %>
<%request.setCharacterEncoding("UTF8");%>
<% User user = (User)request.getAttribute("user");
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
<div class="header">ユーザ情報検索結果</div>

<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>
<table border="0">
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
     <td><form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
         <input type="submit" class="Button-style" value="更新">
         <input type="hidden" name="viewId" value="UserSearch.update" onClick='<%session.setAttribute("oldUser", user);%>'>
         </form>
     </td>

     <td><form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
         <input type="submit" class="Button-style" value="削除">
         <input type="hidden" name="viewId" value="UserSearch.remove" onClick='<%session.setAttribute("user", user);%>'>
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
</div>--%>
</body>
</html>