<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, pkgEntity.Cliente, java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Pesquisar Cliente</title>
</head>
<body>
		
	<form action="./pesqcliente" method="post">
		<table>
			<tr>
				<td>Nome:</td>
				<td><input type="text" name="txtNome"></td>
				<td><input type="submit" value="Pesquisar"></td>
			</tr>
		</table>
	</form>
	
	<%SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  List<Cliente> clientes = (List<Cliente>) request.getAttribute("CLIENTES");
	
		if (clientes!= null && clientes.size() > 0)  {%>
		<table border="1">
			<tr>
				<td>Nome</td>
				<td>Telefone</td>
				<td>Celular</td>
				<td>Email</td>
				<td>Nascimento</td>
			</tr>
			<% for (Cliente c : clientes) { %> 
				<tr>
					<td><%=c.getNome()%></td>
					<td><%=c.getTelefone()%></td>
					<td><%=c.getCelular()%></td>
					<td><%=c.getEmail()%></td>
					<td><%=sdf.format(c.getNascimento())%></td>
				</tr>
			<% } %>
		</table>
	<% } %>
</body>
</html>