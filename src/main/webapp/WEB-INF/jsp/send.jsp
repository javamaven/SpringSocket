<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html>  
<head>  
<meta charset="UTF-8">  
<title>Insert title here</title>  

</head>  
<body>  
<form action="send" method="post">
    给
<input name="username" type="text"/>
    发送
<input name="msg" type="text"/>

<input type="submit" value="提交"/>
</form>

<br/>

<form action="sendall" method="post">
    给全部人发送
    <input name="msg" type="text"/>

    <input type="submit" value="提交"/>
</form>
</body>  
</html>  
