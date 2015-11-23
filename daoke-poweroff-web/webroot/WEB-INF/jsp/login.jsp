<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<div align="center">
		<label>用户登录页面</label>
		<form action="login.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="20%">
				<tr>
					<td>用户名：</td>
					<td><input name="username" type="text"/></td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;码：</td>
					<td><input name="password" type="password"/></td>
				</tr>
				<tr align="center">
					<td colspan="2"><button type="submit">登录</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>