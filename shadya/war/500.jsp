<%@ page language="java" contentType="text/html;charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-BR" lang="pt-BR">
	<head>
		<meta http-equiv="content-type" content="text/html;charset=iso-8859-1" />
		<title>Shadya</title>	
	</head>
	<body>
		<div>
				<%			
				if (exception == null ) {
					exception = (Throwable) request.getAttribute("exception");
				}
				if (exception != null ) {
				%>
				<br />
				O sistema est&aacute; temporariamente indispon&iacute;vel e n&atilde;o
				foi poss&iacute;vel executar a opera&ccedil;&atilde;o desejada. <br />
				Se o problema persistir anote o Protocolo da Ocorr&ecirc;ncia e entre em
				contato que investigaremos o caso prontamente. <br />
				<br />
				<% Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Calendar c = Calendar.getInstance();
				%>
				<br />
				<b>Data da Ocorr&ecirc;ncia:&nbsp;&nbsp;</b> 
				<%= formatter.format(c.getTime()) %>
                <%if (request.getAttribute("ticket")!= null )  {%>
                 <br />
				 <br />
				 <b>Protocolo:&nbsp;&nbsp;&nbsp;</b><%= request.getAttribute("ticket") %>
				 <br />
				 <br />
				 O sistema est&aacute; temporariamente indispon&iacute;vel 
				 <% } }%> 
				 <br />
				<br />
				<a href="#" onclick="history.back();">voltar</a>
	 </div>

</body>
</html>