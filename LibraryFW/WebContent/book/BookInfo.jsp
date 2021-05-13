<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="library.dto.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>蔵書管理システム</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<%request.setCharacterEncoding("UTF8");
%>
<%@ include file="../LoginUser.jsp" %>
<%-- Book resultBook = (Book)request.getAttribute("book");
   String title = resultBook.getTitle();
   String writer = resultBook.getWriter();
   String publisher=resultBook.getPublisher();
   String year = "";
   String user = "";

   if(!(resultBook.getYear() == -1)){
	   year = resultBook.getYear() + "年";
   }

   if(resultBook.getUser()!=null){
	   if("removeUser".equals(resultBook.getUser().getId())){
		   user = resultBook.getUser().getName();
	   }else{
		   user =  resultBook.getUser().getId() + " : " + resultBook.getUser().getName();
	   }
   }
--%>
<%
   Book resultBook = (Book)request.getAttribute("book");
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
<div class="header">書籍情報</div>
<br/>
<table border="0" class="searchResult">
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
     <td class="searchResult"><label for="year">出版年（年）</label></td>
     <th class="searchResult"><%=request.getAttribute("year") %></th>
   </tr>
   <tr class="searchResult">
     <td class="searchResult"><label for="user">所有者</label></td>
     <th class="searchResult"><%=request.getAttribute("user") %></th>
   </tr>
   </table>
   <br/>
<table>
<tbody>
   <tr>
     <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
     <td><form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
         <input type="submit" class="Button-style" value="更新">
         <input type="hidden" name="viewId" value="BookInfo.update" onClick='<%session.setAttribute("oldBook", resultBook);%>'>
         </form>
     </td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
     <td><form method="post" action="<%=request.getContextPath()%>/LibraryServlet">

         <input type="submit" class="Button-style" value="削除">
         <input type="hidden" name="viewId" value="BookInfo.remove" onClick='<%session.setAttribute("removeBook", resultBook);%>'>
         </form>
     </td>

   </tr>

</tbody>

</table>

<div class="article">
<br/>

</div>

</body>
</html>