Hangman Game
========================

Technology

Java + JSP + Spring MVC + Hibernate 

For the guessing characters interactions, the program should use JQuery with AJAX.

Mockito & Junit are used for testing

scripts for accessing the dictionary file is below (picking words whose length is more than 6 characters)
---------------------------------------------------

cat /usr/share/dict/words | tr '[:upper:]’ '[:lower:]’ | grep -E "^[a-z]{6,}$" | sort | uniq | while read l; do echo "INSERT INTO WORDS (WORD) VALUES ('$l');"; done > dictionary.txt

The dictionary.txt and dbScript.txt files can be found in resources folder


Although the best practice is that HashCode and equals must be overridden, The Games set would work with out hashcode and equals in this scenario because database has unique constraint and there is no possibility of duplication.
---------------------------------------------------
