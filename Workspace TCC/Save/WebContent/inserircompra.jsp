<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nova Compra</title>
</head>
<body>
	<form action="./inserircompra" method="post">
		<div id="Main">
		<p> Fornecedor: 
			<select required>
			  <option value="MaryKay">Mary Kay</option>
			</select>
		</p>
			<p>Data Compra: <input type="date" name="datacompra"> </p> 
			<p>Quantidade Pontos: <input type="number" name="quantidade pontos" min="0"> </p>
			<p>Data Recebimento: <input type="date" name="datar ecebimento"></p>
			<p>Valor Compra: <input type="number" name="valor compra" min="0"></p>
			<p>Porcentagem Desconto: <input type="number" name="porcentagem desconto" min="0" max="40" step="5"></p>
			
			<div id= "tblprodutos">
			
			</div>
			<div>
				<input type="button" name="btnincluirProduto" onclick="" onkeypress="13" value="incluir">
			</div>
		</div>
	</form>
</body>
</html>