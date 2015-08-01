<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>venda</title>
</head>
<body>
	<form action="./inserirvenda" method="post" >
		<div id="main">
			<p> data venda:  <input type="date" name="datavenda" required></p>
			<p> valor venda:  <input type="number" name="valorVenda" min=0 required ></p>
			<p> cliente:  <input type="text" name="datavenda"></p>
			<p> pago:  <input type="radio" name="rbtnpago" value="Sim">Sim 
					   <input type="radio" name="rbtnpago" value="Não" checked>Não</p>
			<p> forma de pagamento:  <select name ="SctFPagamento" required>
										  <option value="dinheiro">Dinheiro</option>
										  <option value="creditoV">Credito a Vista</option>
										  <option value="debito">Debito</option>
										  <option value="creditoP">Credito Parcelado</option>
									  </select>
			</p>
			<p> parcelado:  <input type="number" name="parcelas" min=1, max=12 step=1></p>
			<p> Entregue:  <input type="radio" name="rbtnentregue" value="Sim">Sim 
					   	   <input type="radio" name="rbtnentregue" value="Não" checked>Não</p>
			<p> data entrega:  <input type="date" name="dataentrega" required></p>
			<p> Prazo Entrega:  <input type="number" name="prazoaEntrega"></p>
			
			<p> PRODUTOS </p>


		</div>
		<div id="btn">
		<p> <input type="submit" value="adicionar">
		<input type="button" value="Limpar">
		<input type="button" value="Cancelar"></p>
		</div>		
	</form>
</body>
</html>