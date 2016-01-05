<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:forEach var="game" items="${user.games}" end="0">
	<c:set var="gameWord" value="${game.word}" />
	<c:set var="wordToShow" value="${game.wordToShow}" />
</c:forEach>
<c:url value="/logout" var="logout" />
<c:url value="/newGame/${user.name}" var="newGame" />

<html>
<head>
<title>HangMan Game</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css" />" />
<script src="<c:url value="/resources/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/action.js"/>"></script>
</head>
<body>
	<div id="content">
		<h3>HangMan Game, Play On!!!!</h3>
		<div>Hi, ${user.name}</div>
		<div>
			<a href="<c:out value='${logout}'/>">Logout</a>
		</div>
		<div>Please find the ${fn:length(gameWord)} length word in 8
			guesses</div>
		<c:out value="${gameWord}" />
		<div id="word">
			<c:out value="${wordToShow}" />
		</div>
		<div>
			<input type="text" id="insert" placeholder="Please enter a letter" />
			<button type="button" id="buttonGuess"
				onclick="algo('${user.userId}')">Guess</button>
		</div>
		<div id="gameInfo">
			<div>
				You have <span id="moves">8</span> moves.
			</div>
			<div>
				You made <span id="guesses">0</span> guesses.
			</div>
			<div>
				You missed <span id="misses">0</span> times.
			</div>
			<div id="usedLetters"></div>
		</div>
		<div id="newGame">
			<a href="<c:out value='${newGame}'/>">Start a new Game</a>
		</div>
		<div id="message"></div>
		<div id="footer">
			<p>
				Developed by Sunny Ghosh<br />
			</p>
		</div>
	</div>

</body>
</html>

