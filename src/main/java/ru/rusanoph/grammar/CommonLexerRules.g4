lexer grammar CommonLexerRules;

ID  : [A-Za-z]+ ;
INT : [0-9]+ ;
NEWLINE: '\r'? '\n' ;
WS : [ \t]+ -> skip ;
