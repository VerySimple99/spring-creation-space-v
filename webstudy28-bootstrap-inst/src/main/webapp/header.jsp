<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- jstl import 는 필요한 컨텐트만 정의한다 ( 즉 선언부는 제외되어야 함 ) --%>
<form action="" method="post">
<input type="text" name="id" placeholder="아이디" required="required" size="12">
<input type="password" name="password" placeholder="패스워드" required="required" size="12">
<button type="submit">로그인</button>
</form>












