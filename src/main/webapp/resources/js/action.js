function algo(userId) {
	var letter = $.trim($("#insert").val());
	if(letter.length !== 1 || $.isNumeric(letter)){
		$('#message').html('<p>Please enter only 1 character and it must not be a digit</p>');
		return;
	}
	var url = "/HangmanGameNexMo/gameOn/" + userId + "?letter=" + letter
	$.ajax({
		url : url,
		type : 'POST'
	}).done(function(game) {
		updateGameInfo(game, letter);
	}).error(function() {
		$('#message').html('<p>Problem occurred</p>');
	});
}

function updateGameInfo(game, letter) {
	$('#usedLetters').html("Characters used are " + game['usedLetters']);
	$('#message').html("");
	if (game['containsCharacter'] == true) {
		$('#message').html("Alphabet  "+letter + " has already been taken, please try a new alphabet");
		return;
	}
	if (game['gameStatus'] == 'WON' && game['numberOfTry'] >= 0
			&& game['remainingChars'].length == 0) {
		
		$('#word').html(game['wordToShow']);
		$('#message').html('<p>Hurray!  You have won.</p>');
		return;
	} else if (game['gameStatus'] == 'LOST' && game['numberOfTry'] == 0
			&& game['remainingChars'].length > 0) {
		$('#message').html('<p>Sorry!  You lost.</p>');
		return;
	}

	$('#word').html(game['wordToShow']);
	$('#moves').html(game['numberOfTry']);
	$('#guesses').html(game['guesses']);
	$('#misses').html(game['misses']);
}

function invalidateCurrentGameSession(){
	var url = "/HangmanGameNexMo/invalidateCurrentGame/"
	$.ajax({
		url : url,
		type : 'POST'
	}).done();
}

function contentsToShow(){
	invalidateCurrentGameSession();
	$('#gameInfo').html('');
	$('#newGame').show();
	$('#buttonGuess').prop('disabled', true);
}
