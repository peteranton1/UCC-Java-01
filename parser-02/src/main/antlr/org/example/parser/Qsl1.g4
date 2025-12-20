grammar Qsl1;
@header {
   package org.example.parser;
}

prog        : lablExpr* ;
lablExpr    : ident? expr ;
expr        : object
            | listExpr
            | numExpr
            ;

ident       : ID COLON ;
object      : LBRACE (lablExpr (lablExpr)* )? RBRACE ;
listExpr    : LSQUARE (expr (expr)* )? RSQUARE ;
numExpr     : term ( (OP_PLUS term) | (OP_MINUS term) )* ;
term        : factor ( ( OP_MULT factor) | ( OP_DIV factor ) )* ;
factor      : NUMBER
            | ID
            | TEXT
            | LBRACK numExpr RBRACK
            ;

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
ID          : [A-Za-z$_]([A-Za-z0-9$_])* ;
NUMBER      : [0-9]+(.[0-9]+)? ;
SEMICOLON   : ';' ;
TEXT        : STRING ;

// Ignore Whitespaces
WS: [ \t\r\n]+ -> skip;

STRING
    : '"' (ESC | SAFECODEPOINT)* '"'
    ;

fragment ESC
    : '\\' (["\\/bfnrt] | UNICODE)
    ;

fragment UNICODE
    : 'u' HEX HEX HEX HEX
    ;

fragment HEX
    : [0-9a-fA-F]
    ;

fragment SAFECODEPOINT
    : ~ ["\\\u0000-\u001F]
    ;


