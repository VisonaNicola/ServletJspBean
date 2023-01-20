<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ page import="org.negoziocd.beans.*" %>
</head>
<body>
	<jsp:useBean id="shoppingcart" scope="session" class="org.negoziocd.beans.ShoppingCart"></jsp:useBean>
	<%if(shoppingcart.getCdlist().size()==0){%>
		<h3>Il tuo carrello è vuoto</h3>
		<a href="market.jsp">Torna al negozio</a>
	<%}else{%>
		<form action="market.jsp" method="get"><input type="submit" name="submit" value="Market"></form>
		<table>
		<%for(CD cd : shoppingcart.getCdlist()){%>
			<tr>
				<td><%=cd.getTitle() %></td>
				<td><%=cd.getAuthor() %></td>
				<td><%=cd.getPrice() %></td>
				<td><%=cd.getQta() %></td>
				<td>
					<form method="post" action="/NegiozioCD/ServletModifyQuantity">
						<input type="hidden" name="id" value=<%=cd.getId()%>>
						<input type="number" name="value" min=0 value=<%=cd.getQta() %>>
						<input type="submit" name="submit" value="Modify quantity">
					</form>
				</td>
			</tr>
		<%}%>
		</table>
		<h3>Totale:<%=shoppingcart.getTotal() %></h3>
	<%} %>	
</body>
</html>