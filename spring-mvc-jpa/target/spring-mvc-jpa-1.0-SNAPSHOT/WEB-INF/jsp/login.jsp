<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
${msg}
<form action="http://localhost:8080/demo/login" method="post">
    用户名：<input type="text" name="name"><br>
    密&nbsp;&nbsp;&nbsp;码:
    <input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>