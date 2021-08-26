<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="library.dto.Book"%>
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
<div class="header">書籍情報検索結果</div>
<div class="warning" style="display:<%=flagMsg %>"><%=request.getAttribute("Msg") %></div>

<% List<Book> resultList = (List<Book>)request.getAttribute("resultList"); %>
<br/>
<table class="searchResult">
 <tr class="searchResult">
      <td class="searchResult">タイトル</td>
      <td class="searchResult">著者</td>
      <td class="searchResult">出版社</td>
      <td class="searchResult">詳細</td>
  </tr>
  <c:forEach var="book" items="<%=resultList%>">
  <tr class="searchResult">
    <th class="searchResult">${book.title}</th>
    <th class="searchResult">${book.writer}</th>
    <th class="searchResult">${book.publisher}</th>
    <th class="searchResult"><form method="post" action="<%=request.getContextPath()%>/LibraryServlet">
    <input type="submit"class="Button-style-logout" value="詳細"><input type="hidden" name="bookKey" value="${book.bookKey}"><input type="hidden" name="viewId" value="BookSearchResult"></form></th>

<%--
    <th>${book.publicatoindate }</th>
    <th>${book.stock}</th>
    <th>${book.genreId}</th>
     --%>
   </tr>
 </c:forEach>
                     <%--    <thead>
                        	<tr>
  	                      		<th>タイトル</th>
      	                  		<th>著者</th>
        	              		<th>出版社</th>
            	          		<th>詳細</th>
                        	</tr>
                        	</thead>
                        	<ui:repeat var="list" value="#{searchInputBean.customerEntityList}">
	                        	<tr>
		                        	<td>
			                        	<h:outputText value="#{list.customerid}"></h:outputText>

                        			</td>
                        			<td>
                                		<h:outputText value="#{list.sei}　"></h:outputText>
                                		<h:outputText value="#{list.mei}"></h:outputText>
                        			</td>
                        			<td>
                                		<h:outputText value="#{list.seikana}　"></h:outputText>
                                		<h:outputText value="#{list.meikana}"></h:outputText>
                                	</td>
                        			<td>
                                		<h:outputText value="#{list.birthday}">
                                    		<f:convertDateTime pattern="yyyy年MM月dd日"/>
                                		</h:outputText>
                        			</td>
		                       		<td>
                                    	<h:outputText value="#{list.address}"></h:outputText>
        		                	</td>
                                	<td>
                	                	<input type="submit" action="#{customerListBean.doShowKeiyakuAction}" value="詳細">
                    	                	<f:param name="customerId" value="#{list.customerid}"/>
		                        	</td>
        	                	</tr>
                        	</ui:repeat>--%>
                        </table>

<!--
<table border="0">
<tbody>
   <tr>
     <td><label for="title">タイトル（※必須）</label></td>
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
     <td><input type="text" id="year" name="year" pattern="^[0-9]{1,4}" title="4桁以下の自然数（半角）"></td>
   </tr>

   <tr>
     <td></td>
     <td><input type="submit" value="検索"></td>

   </tr>

</tbody>

</table>
 -->

<div class="article">
<br/>

<table>
<tbody>
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<form method="post" action="<%=request.getContextPath()%>/book/BookSearch.jsp">
<input type="submit" class="Button-style" value="検索画面へ戻る">
<input type="hidden" name="viewId" value="logout">
</form>
</td>
</tr>
</tbody>
</table>
</div>
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