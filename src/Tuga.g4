grammar Tuga;

tuga : inst+ EOF;

// instrucoes
inst : 'escreve' expr END_INST;

// expressoes
expr : LPAREN expr RPAREN							# ParenExpr
  | unary_op expr 									# UnaryOp
  | expr binary_op expr	 							# BinaryOp
  | literal											# LiteralExpr
  ;

unary_op : op=SUB									# NegateOp
  | op=NOT											# LogicNegateOp
  ;

binary_op : op=(MULT|DIV|MOD)						# MultDivOp
  | op=(SUM|SUB)									# SumSubOp
  | op=(LESS|GREATER|LESS_EQ|GREATER_EQ)			# RelOp
  | op=(EQUALS|N_EQUALS)							# EqualsOp
  | op=AND											# AndOp
  | op=OR											# OrOp
  ;

literal : INT								        # Int
  | DOUBLE											# Double
  | STRING											# String
  | TRUE											# True
  | FALSE											# False
  ;

// all operators
LPAREN:		'(' ;
RPAREN:		')' ;
NOT:		'nao' ;
MULT:		'*' ;
DIV:		'/' ;
MOD:		'%' ;
SUM:		'+' ;
SUB:		'-' ;
LESS:		'<' ;
GREATER:	'>' ;
LESS_EQ:	'<=' ;
GREATER_EQ:	'>=' ;
EQUALS:		'igual' ;
N_EQUALS:	'diferente' ;
AND:		'e' ;
OR:			'ou' ;
END_INST:	';' ;

// types or constants (literais)
INT : [0-9]+ ;
DOUBLE: [0-9]+ '.' [0-9]+ ;
STRING: '"' ALL_CHARS+ '"' ;
TRUE : 'verdadeiro' ;
FALSE : 'falso' ;

WS : [ \t\r\n]+ -> skip ;
SL_COMMENT : '//' .*? (EOF|'\n') -> skip ;  // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ;        // multi-line comment

fragment
ALL_CHARS : [ a-zA-Z=] ;