<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>蔵書管理システム</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<%@ page import="library.dto.User"%>
<%@ include file="../LoginUser.jsp" %>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	User loginUser = (User)session.getAttribute("loginUser");
%>
</head>
<body>
<div class="headButton" align="right">
<br/>

<form method="post" action="<%=request.getContextPath()%>/LibraryServlet" style="display: inline">
<input type="submit" class="Button-style-logout" value="ログアウト">
<input type="hidden" name="viewId" value="logout">
</form>&nbsp;</div>
<div class="header">操作選択</div>

<p>ログイン： <%=session.getAttribute("LoginUserName")%>さん</p>
<table border="0">

<tbody>
   <tr>
     <td><div class="sub">蔵書管理</div></td>
   <tr>
   <tr>
     <td></td>
     <td>
        <form method="post" action="<%=request.getContextPath()%>/book/BookSearch.jsp">
        <input type="submit" class="Button-style" value="　　検索　　">
        </form>
     </td>
   </tr>
   <tr>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <td></td>
     <td>
        <form method="post" action="<%=request.getContextPath()%>/book/BookInsert.jsp">
        <input type="submit" class="Button-style" value="　　登録　　">
        </form>
   </tr>
   <tr>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <td><div class="sub">ユーザ管理</div></td>
   </tr>
   <tr>
     <td></td>
     <td>
        <form method="post" action="<%=request.getContextPath()%>/user/UserInsert.jsp">
        <input type="submit" class="Button-style" value="　　登録　　">
        </form>
   </tr>
   <tr>
     <td>&nbsp;</td>
   </tr>
   <tr>
     <td></td>
     <td>
        <form method="post" action="<%=request.getContextPath()%>/user/UserSearch.jsp">
        <input type="submit" class="Button-style" value="  更新・削除  ">
        </form>
   </tr>
</tbody>
</table>



<%--
<tbody>
   <tr>
     <td><div class="sub">蔵書管理</div></td>
   <tr>
   <tr>
     <td></td>
     <td><a href="./book/BookSearch.jsp">検索</a></td>
   </tr>
   <tr>
     <td></td>
     <td><a href="./book/BookInsert.jsp">登録</a></td>
   </tr>
   <tr>
     <td><div class="sub">ユーザ管理</div></td>
   </tr>
   <tr>
     <td></td>
     <td><a href="./user/UserInsert.jsp">登録</a></td>
   </tr>
   <tr>
     <td></td>
     <td><a href="./user/UserSearch.jsp">更新・削除</a></td>
   </tr>
</tbody>
</table>--%>


<%--
<div class="article">
<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<br/>
<input type="submit" class="Button-style-logout" value="ログアウト">
<input type="hidden" name="viewId" value="logout">
</form>
</div>
 --%>
</body>
</html>