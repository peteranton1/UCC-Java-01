grammar Qsl1;
@header {
   package org.example.parser;
}

prog    :   ((object | expr) NEWLINE)+ ;
ident   :   ID COLON ;
object  :	'{' (ident expr) (',' ident expr)* '}' ;
expr    :	expr ('*'|'/') expr
        |	expr ('+'|'-') expr
        |	NUMBER
        |   ID
        |   TEXT
        |	'(' expr ')'
        ;

NEWLINE : [\r\n]+ ;
ID : [A-Za-z$_]([A-Za-z0-9$_])* ;
COLON : ':' ;
NUMBER  : [0-9]+(.[0-9]+)? ;
TEXT   : '"' [^"]* '"' ;

// Ignore Whitespaces
WS: [ \t\r\n]+ -> skip;
