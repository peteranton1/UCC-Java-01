grammar Qsl02;
@header {
   package org.example.parser;
}

prog        : module+ ;
module      :
            (MODULE textIdNum)? stmts
            ;

stmts       : stmt+
            ;

stmt        : DEF varExpr block
            | EXEC varExpr
            | COMPUTE varExpr
            | ifBlock
            | clause+
            ;

clause      :
              clauseKwd textIdNum+
            ;
clauseKwd   : OPTIONS
            | QT
            | ANS
            ;

ifBlock     : ifConditionBlock
              elseIfConditionBlock*
              elseBlock?
            ;

ifConditionBlock :
            IF condition block
            ;

elseIfConditionBlock :
            ELSE IF condition block
            ;

elseBlock :
            ELSE block
            ;

condition   : LPAREN (varExpr (operator varExpr)*) RPAREN
            ;

block       : LBRACE stmts RBRACE
            ;

varExpr     : ID LPAREN varSubExpr RPAREN
            ;

varSubExpr  : (textIdNum (operator textIdNum)*)?
            ;

textIdNum   : TEXT
            | ID
            | NUMBER
            ;

operator    : COMMA
            | OP_OR
            | OP_OR_OR
            | OP_AND
            | OP_AND_AND
            ;

ANS         : 'ans' ;
COMMA       : ',' ;
COMPUTE     : 'compute' ;
DEF         : 'def' ;
END         : 'end' ;
ELSE        : 'else' ;
EXEC        : 'exec' ;
IF          : 'if' ;
MODULE      : 'module' ;
QT          : 'qt' ;
OP_NOT      : '!' ;
OP_OR       : '|' ;
OP_OR_OR    : '||' ;
OP_AND      : '&' ;
OP_AND_AND  : '&&' ;
OPTIONS     : 'options' ;
LBRACE      : '{' ;
RBRACE      : '}' ;
LPAREN      : '(' ;
RPAREN      : ')' ;
ID          : [A-Za-z$_]([A-Za-z0-9$_])* ;
NUMBER      : [+-]?[0-9]+(.[0-9]+)? ;
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


