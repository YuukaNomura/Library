<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library.dto.User"%>
<%
   //セッション情報の確認
   session = request.getSession(false);
   if (session == null) {
   	   /* まだ認証されていない */
	   response.sendRedirect(request.getContextPath() + "/Login.jsp");
   } else {
   	   Object loginCheck = session.getAttribute("loginUser");
   	   if (loginCheck == null) {
   		/* まだ認証されていない */
   		response.sendRedirect(request.getContextPath() + "/Login.jsp");
	   }
   }

%>