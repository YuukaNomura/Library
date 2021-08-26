<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library.dto.Book"%>
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
<div class="header">書籍情報更新</div>
<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<input type="hidden" name="viewId" value="BookUpdate">
<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>
<div style="display:<%=flagComplete %>">
<br/>
更新情報を入力してください
<br/><br/>
</div>
<table border="0" style="display:<%=flagComplete %>">
<tbody>
   <tr>
     <td><label for="title">タイトル</label></td>
     <td><input required type="text" id="title" name="title" value="<%=request.getAttribute("title") %>"></td>
     <td>※必須</td>
   </tr>

   <tr>
     <td><label for="writer">著者</label></td>
     <td><input type="text" id="writer" name="writer" value="<%=request.getAttribute("writer") %>"></td>
   </tr>

   <tr>
     <td><label for="publisher">出版社</label></td>
     <td><input type="text" id="publisher" name="publisher" value="<%=request.getAttribute("publisher") %>"></td>
   </tr>

   <tr>
     <td><label for="year">出版年</label></td>
     <td><input type="text" id="year" name="year" maxlength=4 pattern="^[0-9]{1,4}" title="4桁以下の自然数（半角）" value="<%=request.getAttribute("year") %>"></td>
   </tr>
   <tr>
     <td><label for="userId">所有者のID</label></td>
     <td><input type="text" id="userId" name="userId" maxlength=10 pattern="^[0-9A-Za-z]{1,10}" title="10桁以下の半角英数" value="<%=request.getAttribute("userId") %>"></td>
     <td>※未入力の場合、現在のユーザを所有者として登録</td>
   </tr>
   <tr>
     <td></td>
     <td></td>
   </tr>
   <tr>
     <td></td>
     <td><input type="submit"  class="Button-style" value="更新"></td>

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