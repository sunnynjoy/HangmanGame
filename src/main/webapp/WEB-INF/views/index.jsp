
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>HangMan Game</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/resources/css/screen.css"/>" />
</head>

<body>
	<div id="content">
		<h2>HangMan Game!</h2>
		<form:form commandName="user" method="post" action="submit">
			<table>
				<tr>
					<td>Please Enter Your UserName :</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td colspan="4" align="RIGHT"><input type="submit"
						value="submit" /></td>
				</tr>
				<c:if test="${not empty user.name}">
				<c:set var="name" value="${user.name}" scope="request"/>
				<tr>
				<td>
				Hi ${user.name}, want to start playing HangMan game, 
				<c:url value="/newGame/${user.name}" var="newGame"/>
 				<a href="<c:out value='${newGame}'/>">click here</a>
				</td>
				</tr>
				</c:if>

			</table>
		</form:form>

		<div id="footer">
			<p>
				Developed by Sunny Ghosh<br />
			</p>
		</div>
	</div>
</body>
</html>
