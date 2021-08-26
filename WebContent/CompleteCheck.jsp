<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
    String flagComplete;
    String flagMenu;
    if(request.getAttribute("Complete")!=null){
    	flagComplete = "none";
    	flagMenu = "";
    }else{
    	flagComplete = "";
    	flagMenu = "none";
    }
%>