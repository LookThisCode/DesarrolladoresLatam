<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.google.appengine.api.rdbms.AppEngineDriver" %>

<html>
  <body>

<%
Connection c = null;
c = DriverManager.getConnection("jdbc:google:rdbms://nbortolotticloudsqldatabase:example1/demoClientes");
ResultSet rs = c.createStatement().executeQuery("SELECT nombreCliente, descripcionCliente, clienteID FROM entries"); %>

<table style="border: 1px solid black">
<tbody>
<tr>
<th width="35%" style="background-color: #CCFFCC; margin: 5px">Nombre</th>
<th style="background-color: #CCFFCC; margin: 5px">Descripcion</th>
<th style="background-color: #CCFFCC; margin: 5px">ID</th>
</tr> <%
while (rs.next()){
    String Nombre = rs.getString("nombreCliente");
    String Descripcion = rs.getString("descripcionCliente");
    int id = rs.getInt("clienteID"); %>

<tr>
<td><%= Nombre %></td>
<td><%= Descripcion %></td>
<td><%= id %></td>
</tr>

<% }
c.close(); %>

</tbody>
</table>
<br />
No more messages!
<p><strong>Ingrese en la base de Clientes!</strong></p>
<form action="/sign" method="post">
    <div>Nombre: <input type="text" name="nombre"></input></div>
    <div>Descripcion:
    <br /><textarea name="descripcion" rows="3" cols="60"></textarea>
    </div>
    <div><input type="submit" value="Enviar Consulta" /></div>
    <input type="hidden" name="nombreclientes" />
  </form>
  </body>
</html>