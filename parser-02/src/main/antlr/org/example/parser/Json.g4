
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

primaryExpr
    : NUM
    ;

NUM : [0-9]+ ;
SPACES : [ \t] -> skip ;
