grammar Qsl1;
@header {
   package org.example.parser;
}

prog        : (expr NEWLINE)+ ;
expr        : object
            | listExpr
            | numExpr
            | textExpr
            ;

object      : LBRACE (lablExpr (COMMA lablExpr)* )? RBRACE ;
listExpr    : LSQUARE (expr (COMMA expr)* )? RSQUARE ;
lablExpr    : ident expr ;
numExpr     : term ( (OP_PLUS term) | (OP_MINUS term) )* ;
term        : factor ( ( OP_MULT factor) | ( OP_DIV factor ) )* ;
factor      : NUMBER
            | ID
            | LBRACK numExpr RBRACK
            ;
textExpr    : TEXT ;
ident       : ID COLON ;

OP_PLUS     : '+' ;
OP_MINUS    : '-' ;
OP_DIV      : '/' ;
OP_MULT     : '*' ;
COLON       : ':' ;
COMMA       : ',' ;
LBRACE      : '{' ;
RBRACE      : '}' ;
LBRACK      : '(' ;
RBRACK      : ')' ;
LSQUARE     : '[' ;
RSQUARE     : ']' ;
NEWLINE     : [\r\n]+ ;
ID          : [A-Za-z$_]([A-Za-z0-9$_])* ;
NUMBER      : [0-9]+(.[0-9]+)? ;
TEXT        : QUOTE [^"]* QUOTE ;
QUOTE       : '"' ;
// Ignore Whitespaces
WS: [ \t\r\n]+ -> skip;
