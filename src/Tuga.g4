grammar Tuga;

tuga : inst+ EOF;

// instructions
inst : 'escreve' expr END_INST                      #instPrint
  ;

// expressions
expr : LPAREN expr RPAREN							# ParenExpr
  // unary operators
  | SUB expr 									    # NegateOp
  | NOT expr 									    # LogicNegateOp
  // binary operators
  | expr op=(MULT|DIV|MOD) expr	 					# MultDivModOp
  | expr op=(SUM|SUB) expr	 						# SumSubOp
  | expr op=(LESS|GREATER|LESS_EQ|GREATER_EQ) expr	# RelOp
  | expr op=(EQUALS|N_EQUALS) expr	 				# EqualsOp
  | expr AND expr       	 						# AndOp
  | expr OR expr                                    # OrOp
  // literals
  | literal											# LiteralExpr
  ;

literal : INT										# Int
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

// types or constants (literals)
INT : [0-9]+ ;
DOUBLE: [0-9]+ '.' [0-9]+ ;
STRING: '"' ~["]* '"' ; // ~["] significa qualquer caracter exceto '"', e '*' é 0 ou mais repeticoes
TRUE : 'verdadeiro' ;
FALSE : 'falso' ;

WS : [ \t\r\n]+ -> skip ;
SL_COMMENT : '//' .*? (EOF|'\n') -> skip ;  // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ;        // multi-line comment

// fragment
// ALL_CHARS : [ a-zA-Z=] ;