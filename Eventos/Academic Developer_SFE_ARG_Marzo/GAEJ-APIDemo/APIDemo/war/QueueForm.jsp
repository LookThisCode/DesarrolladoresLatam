<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Encolar una tarea</title>
</head>
<body>

<h2>Contar pelos</h2>
	<form action="/queueTask">
		Su Nombre:<input name="nameField" type="text" /> <br>
		Su Email:<input name="mailField" type="text" /> <br>
		Cantidad de veces: <input name="amountField" type="text" /> <br>
		<button type="submit">Contar!</button>

	</form>
</body>
</html>