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
<div class="header">書籍情報削除</div>

<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<input type="hidden" name="viewId" value="BookRemove">
<input type="hidden" name="BookKey" value='<%=request.getAttribute("BookKey") %>'>
<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>
<div style="display:<%=flagComplete %>">
<br/>
以下の書籍情報を削除しますか？
<br/><br/>
</div>

<table border="0" style="display:<%=flagComplete %>" class="searchResult">
<tbody class="searchResult">
   <tr class="searchResult">
     <td class="searchResult"><label for="title">タイトル</label></td>
     <th class="searchResult"><%=request.getAttribute("title") %></th>
   </tr>

   <tr class="searchResult">
     <td class="searchResult"><label for="writer">著者</label></td>
     <th class="searchResult"><%=request.getAttribute("writer") %></th>
   </tr>

   <tr class="searchResult">
     <td class="searchResult"><label for="publisher">出版社</label></td>
     <th class="searchResult"><%=request.getAttribute("publisher") %></th>
   </tr>

   <tr class="searchResult">
     <td class="searchResult"><label for="year">出版年</label></td>
     <th class="searchResult"><%=request.getAttribute("year") %></th>
   </tr>
   <tr class="searchResult">
     <td class="searchResult"><label for="userId">所有者</label></td>
     <th class="searchResult"><%=request.getAttribute("user") %></th>
   </tr>
   </tbody>
</table>

<table style="display:<%=flagComplete %>" >
 <tbody>
   <tr><td>&nbsp;</td></tr>
   <tr>
     <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
     <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
</div>

 --%>
</body>
</html>