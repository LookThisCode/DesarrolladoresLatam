<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enviar mensaje XMPP</title>
</head>
<body>
<h2>Agregar a alguien al chat</h2> 
	<form action="invitechat">
		Destinatario:<input name="toField" type="text" />
		<button type="submit">Invitar</button>

	</form>
<br>
<h2>Enviar mensaje</h2>
	<form action="sendchat">

		Destinatario:<input name="toField" type="text" /> <br> Mensaje:
		<textarea cols="40" rows="5" name="messageField"></textarea>
		<br>
		<button type="submit">Enviar!</button>

	</form>
</body>
</html>