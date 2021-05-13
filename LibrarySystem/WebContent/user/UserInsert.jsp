<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div class="header">ユーザ情報登録</div>

<form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
<input type="hidden" name="viewId" value="UserInsert">
<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>
<table border="0" style="display:<%=flagComplete %>">
<tbody>
   <tr>
     <td><label for="userId">ID</label></td>
     <td><input required type="text" id="userId" name="userId" maxlength=10 pattern="^[0-9A-Za-z]{1,10}" title="10桁以下の半角英数"></td>
     <td>※必須</td>
   </tr>

   <tr>
     <td><label for="pass1">パスワード</label></td>
     <td><input type="password" id="pass1" name="pass1"  maxlength=30 pattern="^[0-9A-Za-z]{1,30}" title="30桁以下の半角英数"></td>
   </tr>

   <tr>
     <td><label for="pass2">パスワード（確認用）</label></td>
     <td><input type="password" id="pass2" name="pass2"  maxlength=30 pattern="^[0-9A-Za-z]{1,30}" title="30桁以下の半角英数"></td>
   </tr>

   <tr>
     <td><label for="userName">ユーザ名</label></td>
     <td><input type="text" id="userName" name="userName" maxlength=100 ></td>
   </tr>
   <tr>
     <td></td>
     <td></td>
   </tr>

   <tr>
     <td></td>
     <td><input type="submit" class="Button-style" value="登録"></td>

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
</div>--%>
</body>
</html>