<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Teste</title>
	</head>
	<body>
		<p>Bem Vindo!</p>
		<p>Nome: ${name}</p>
		<p>Outro Nome: <s:property value="form.nome"/></p>
		<p>Sobrenome: <s:property value="form.sobrenome"/></p>
		
	</body>
</html>