
grammar Json;
@header {
   package org.example.parser;
}

fileStmt
    : expr
    ;

expr
    : additiveExpr
    ;

additiveExpr
    : additiveExpr ('+' | '-') primaryExpr
    | primaryExpr
    ;

// Ignore Whitespaces
WS: [ \t\r\n]+ -> skip;
primaryExpr
    : NUM
    ;

NUM : [0-9]+ ;
SPACES : [ \t] -> skip ;
