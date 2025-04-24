grammar Tuga;

tuga : declare_var* inst+ EOF;

declare_var : variable ':' type=(INT_TYPE | DOUBLE_TYPE | STRING_TYPE | BOOLEAN_TYPE) END_INST     # DeclVar
  ;

variable : VAR ',' variable                         # Vars
  | VAR                                             # Var
  ;

// instructions
inst : 'escreve' expr END_INST                                     # instPrint
  | VAR '<-' expr                                                  # instAssign
  | 'inicio' inst* 'fim'                                           # instScope
  | 'se' LPAREN expr RPAREN (inst | 'inicio' inst* 'fim')          # instIf
  | 'se' LPAREN expr RPAREN (inst | 'inicio' inst* 'fim')
    'senao' (inst | 'inicio' inst* 'fim')                          # instIfElse
  | 'enquanto' LPAREN expr RPAREN (inst | 'inicio' inst* 'fim')    # instWhile
  | END_INST                                                       # instEmpty
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
  | VAR                                             # VARExpr
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

// constants (literals)
INT : [0-9]+ ;
DOUBLE: [0-9]+ '.' [0-9]+ ;
STRING: '"' ~["]* '"' ; // ~["] significa qualquer caracter exceto '"', e '*' é 0 ou mais repeticoes
TRUE : 'verdadeiro' ;
FALSE : 'falso' ;

// types
INT_TYPE : 'inteiro';
DOUBLE_TYPE: 'real';
STRING_TYPE: 'string';
BOOLEAN_TYPE: 'booleano';

VAR : [a-zA-Z_] [a-zA-Z0-9_]* ;

WS : [ \t\r\n]+ -> skip ;
SL_COMMENT : '//' .*? (EOF|'\n') -> skip ;  // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ;        // multi-line comment
