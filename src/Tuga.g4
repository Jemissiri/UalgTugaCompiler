grammar Tuga;

// tuga : declare_var* inst+ EOF;
tuga : declare_var* function+ EOF;

declare_var : variable ':' type=(INT_TYPE | DOUBLE_TYPE | STRING_TYPE | BOOLEAN_TYPE) END_INST     # DeclVar
  ;

variable : VAR ',' variable                         # Vars
  | VAR                                             # Var
  ;

function : 'funcao' VAR '(' arg_list? ')'
           (':' type=(INT_TYPE | DOUBLE_TYPE | STRING_TYPE | BOOLEAN_TYPE))? scope      # FunctionDecl
  ;

arg_list : argument ',' arg_list                         # Args
  | argument                                             # Arg
  ;

argument : VAR ':' type=(INT_TYPE | DOUBLE_TYPE | STRING_TYPE | BOOLEAN_TYPE)           # DeclArg
  ;

function_call : VAR '(' expr_list? ')'                                                  # FunctionCall
  ;

expr_list : expr ',' expr_list                          # Expressions
  | expr                                                # Expression
  ;

// instructions
inst : print
  | assign
  | scope
  | if
  | ifelse
  | while
  | function_call_inst
  | return
  | empty
  ;

/*
inst : 'escreve' expr END_INST                                     # InstPrint
  | VAR '<-' expr                                                  # InstAssign
  | 'inicio' inst* 'fim'                                           # InstScope
  | 'se' LPAREN expr RPAREN (inst | 'inicio' inst* 'fim')          # InstIf
  | 'se' LPAREN expr RPAREN (inst | 'inicio' inst* 'fim')
    'senao' (inst | 'inicio' inst* 'fim')                          # InstIfElse
  | 'enquanto' LPAREN expr RPAREN (inst | 'inicio' inst* 'fim')    # InstWhile
  | END_INST                                                       # InstEmpty
  ;
*/

print : 'escreve' expr END_INST                                    # InstPrint
  ;

assign : VAR '<-' expr END_INST                                    # InstAssign
  ;

scope : 'inicio' declare_var* inst* 'fim'                                       # InstScope
  ;

scopeOrInst : scope
  | inst
  ;

if : 'se' '(' expr ')' scopeOrInst                                 # InstIf
  ;

ifelse : 'se' '(' expr ')' scopeOrInst
         'senao' scopeOrInst							           # InstIfElse
  ;

while : 'enquanto' '(' expr ')' scopeOrInst                        # InstWhile
  ;

function_call_inst : function_call END_INST                        # InstFunctionCall
  ;

return : 'retorna' expr? END_INST                                  # InstReturn
  ;

empty : END_INST                                                   # InstEmpty
  ;

// expressions
expr : LPAREN expr RPAREN							# ParenExpr
  // unary operators
  | op=SUB expr 								    # NegateOp
  | op=NOT expr 								    # LogicNegateOp
  // binary operators
  | expr op=(MULT|DIV|MOD) expr	 					# MultDivModOp
  | expr op=(SUM|SUB) expr	 						# SumSubOp
  | expr op=(LESS|GREATER|LESS_EQ|GREATER_EQ) expr	# RelOp
  | expr op=(EQUALS|N_EQUALS) expr	 				# EqualsOp
  | expr op=AND expr       	 						# AndOp
  | expr op=OR expr                                 # OrOp
  // literals
  | literal											# LiteralExpr
  | VAR                                             # VarExpr
  | function_call                                   # FuncExpr
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
