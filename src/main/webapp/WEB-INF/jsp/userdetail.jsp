<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html>  
<head>  
<meta charset="UTF-8">  
<title>Insert title here</title>  

</head>  
<body>  
<form action="login" method="post">
<input name="name" type="text" value="${user.name}"/>
<input name="password" type="text" value="${user.password}"/>
<input type="submit" value="提交"/>
</form>
</body>  
</html>  
