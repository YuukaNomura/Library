<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>蔵書管理システム</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<%@ include file="../LoginUser.jsp" %>
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
<div class="header">書籍情報検索</div>

<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<input type="hidden" name="viewId" value="BookSearch">
<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>
<div>
<br/>
検索条件を入力してください
<br/><br/>
</div>
<table border="0">
<tbody>
   <tr>
     <td><label for="title">タイトル</label></td>
     <td><input type="text" id="title" name="title"></td>
   </tr>

   <tr>
     <td><label for="writer">著者</label></td>
     <td><input type="text" id="writer" name="writer"></td>
   </tr>

   <tr>
     <td><label for="publisher">出版社</label></td>
     <td><input type="text" id="publisher" name="publisher"></td>
   </tr>

   <tr>
     <td><label for="year">出版年</label></td>
     <td><input type="text" id="year" name="year" maxlength=4 pattern="^[0-9]{1,4}" title="4桁以下の自然数（半角）"></td>
   </tr>
   <tr>
     <td></td>
     <td></td>

   </tr>

   <tr>
     <td></td>
     <td><input type="submit" class="Button-style" value="検索"></td>

   </tr>

</tbody>

</table>

</form>


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