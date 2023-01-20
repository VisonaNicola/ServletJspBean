<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="org.negoziocd.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CD market</title>

</head>
<body>
	<jsp:useBean id="cdmarket" scope="session" class="org.negoziocd.beans.CDMarket"></jsp:useBean>
	<%
	if(cdmarket.getCdlist().size()==0)
		response.sendRedirect("/NegiozioCD/ServletCD");
	%>
	<form action="shoppingcart.jsp" method="get"><input type="submit" name="submit" value="Shopping cart"></form>
	<table>
		<tr>
			<td>Name</td><td>Author</td><td>Price</td>
		</tr>
		<%
		for(CD cd : cdmarket.getCdlist()){
			%>
				<tr>
					<td><%=cd.getTitle() %></td>
					<td><%=cd.getAuthor() %></td>
					<td><%=cd.getPrice() %></td>
					<td>
						<form method="post" action="/NegiozioCD/ServletShoppingCart">
							<input type="hidden" name="id" value=<%=cd.getId()%>>
							<input type="submit" name="submit" value="Add">
						</form>
					</td>
				</tr>			
		<%}
		%>		
	</table>
</body>
</html>