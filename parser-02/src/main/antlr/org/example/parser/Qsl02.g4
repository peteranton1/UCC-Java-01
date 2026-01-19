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

stmt        : defStmt
            | execStmt
            | computeStmt
            | ifBlock
            | clause+
            ;

defStmt     : DEF idDef block
            ;

idDef       : ID
            ;

execStmt    : EXEC idDef+
            ;

computeStmt : COMPUTE idDef OP_EQUALS varSubExpr
            ;

clause      : clauseKwd textIdNum+
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

condition   : varSubExpr
            | LPAREN varSubExpr RPAREN
            ;

block       : LBRACE stmts RBRACE
            ;

varSubExpr  : textIdNum (operator textIdNum)*
            ;

textIdNum   : TEXT
            | ID
            | ID LPAREN varSubExpr? RPAREN
            | NUMBER
            ;

operator    : COMMA
            | OP_OR
            | OP_OR_OR
            | OP_AND
            | OP_AND_AND
            | OP_EQUIV
            | OP_LE
            | OP_LT
            | OP_GE
            | OP_GT
            | OP_PLUS
            | OP_MINUS
            | OP_MULT
            | OP_DIV
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
OP_EQUALS   : '=' ;
OP_EQUIV    : '==' ;
OP_NE       : '!=' ;
OP_GE       : '>=' ;
OP_LE       : '<=' ;
OP_GT       : '>' ;
OP_LT       : '<' ;
OP_PLUS     : '+' ;
OP_MINUS    : '-' ;
OP_DIV      : '/' ;
OP_MULT     : '*' ;
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


