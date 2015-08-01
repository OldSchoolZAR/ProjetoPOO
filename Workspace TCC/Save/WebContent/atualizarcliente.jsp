<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Atualizar Cliente</title>
	</head>
<body>
	<form action="./atualizarcliente" method="post">
		<table align="center">
			<tr align="center">
				<td colspan="2"><h3>Alteração de Cliente</h3></td>
			</tr>
			<tr>
				<td>Nome:</td>
				<td><input type="text" name=txtNome></td>
			</tr>
			<tr>
				<td>Telefone:</td>
				<td><input type="text" name=txtFone></td>
			</tr>
			<tr>
				<td>Celular:</td>
				<td><input type="text" name=txtCel></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name=txtEmail></td>
			</tr>
			<tr>
				<td>Idade:</td>
				<td><input type="text" name=txtNasc></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="Atualizar"></td>
			</tr>
		</table>
	</form>
</body>
</html>