grammar C;
@header {
    package Syntactic;
    import absyn.*;
    import symbol.*;
}
@members {
    static symbol.Symbol symbol(String s) {
        return symbol.Symbol.getSymbol(s);
    }
    private String format = new String("\r\n\t");
}
decls returns [Decls r]
: decl {$r = new Decls($decl.r);}
//    : {$r = null;}
//    | decl decls {
//        if ($decls.r == null ) $r = new Decls($decl.r);
//        else { $decls.r.list.addFirst($decl.r); $r = $decls.r; }
//    }
    ;
decl returns [Decl r]
    : declaration {$r = $declaration.r;}
    | function_definition {$r = $function_definition.r;}
    ;
// don't understand
declaration returns [Declar r]
    : type_specifier { $r = new VarDecl($type_specifier.r, null);} ';'
    | type_specifier init_declarators ';' {$r = new VarDecl($type_specifier.r, $init_declarators.r);}
    ;

function_definition returns [Func r]
    : type_specifier plain_declarator '(' ')' compound_statement
        {$r = new Func($type_specifier.r, $plain_declarator.r, null, false, $compound_statement.r);}
    | type_specifier plain_declarator '(' parameters ')' compound_statement
        {$r = new Func($type_specifier.r, $plain_declarator.r, $parameters.r, false, $compound_statement.r);}
    | type_specifier plain_declarator '(' parameters ',' '...' ')' compound_statement
        {$r = new Func($type_specifier.r, $plain_declarator.r, $parameters.r, true, $compound_statement.r);}
    ;

parameters returns [Para r]
    locals [Para rr]
    @after {$r = $rr;}
    : plain_declaration {$rr = new Para($plain_declaration.r);} (',' plain_declaration{$rr.list.addLast($plain_declaration.r);})*
    ;


declarators returns [Decltors r]
    locals [Decltors rr]
    @after{$r = $rr;}
    : declarator{$rr = new Decltors($declarator.r);} (',' declarator{$rr.list.addLast($declarator.r);})*
    ;
init_declarators returns [InitDecltors r]
    locals [InitDecltors rr]
    @after{$r = $rr;}
    : init_declarator{$rr = new InitDecltors($init_declarator.r);} (',' init_declarator{$rr.list.addLast($init_declarator.r);})*;

init_declarator returns [InitDecltor r]
    : declarator {$r = new InitDecltor($declarator.r, null);}
    | declarator '=' initializer {$r = new InitDecltor($declarator.r, $initializer.r);};

initializer returns [Initer r]
    locals [Initers rr]
    : assignment_expression {$r = new PlIniter($assignment_expression.r);}
    | '{' initializer {$rr = new Initers($initializer.r);} (',' initializer{$rr.list.addLast($initializer.r);})* '}'{$r = new CpdIniter($rr);};
//omit typedef_name, should be implemented with reference to Symbol package
type_specifier returns [Ty r]
    : 'void'{$r = new NameTy(symbol("void"));}
    | 'char'{$r = new NameTy(symbol("char"));}
    | 'int' {$r = new NameTy(symbol("int"));}
    | 'struct' Identifier '{' struct_declarations '}' {$r = new StructTy(symbol($Identifier.text),$struct_declarations.r);}
    | 'union' Identifier '{' struct_declarations '}' {$r = new UnionTy(symbol($Identifier.text),$struct_declarations.r);}
    | 'struct' '{' struct_declarations '}' {$r = new StructTy($struct_declarations.r);}
    | 'union'  '{' struct_declarations '}' {$r = new UnionTy($struct_declarations.r);}
    | 'struct' Identifier {$r = new StructTy(symbol($Identifier.text), null);}
    | 'union' Identifier {$r = new UnionTy(symbol($Identifier.text), null);}
    ;

struct_declarations returns [StructDecls r]
    locals [StructDecls rr]
    @after{$r = $rr;}
    : (type_specifier declarators ';') {$rr = new StructDecls($type_specifier.r, $declarators.r);}
    (type_specifier declarators ';'{$rr.addLast($type_specifier.r, $declarators.r);})*
    ;
plain_declaration returns [PlDecl r]
    : type_specifier declarator{$r = new PlDecl($type_specifier.r, $declarator.r);};

declarator returns [Decltor r]
    : plain_declarator {$r = new SmpDecltor($plain_declarator.r);}
    | plain_declarator '(' ')'{$r = new FuncDecltor($plain_declarator.r ,null, false);}
    | plain_declarator '(' parameters ')' {$r = new FuncDecltor($plain_declarator.r, $parameters.r, false);}
    | plain_declarator '(' parameters ',' '...' ')' {$r = new FuncDecltor($plain_declarator.r, $parameters.r, true);}
    | plain_declarator array_parameters {$r = new ArrDecltor($plain_declarator.r, $array_parameters.r);}
    ;
array_parameters returns [ArrParas r]
    locals [ArrParas rr]
    @after{$r = $rr;}
    : '[' constant_expression ']'{$rr = new ArrParas($constant_expression.r);}('[' constant_expression ']'{$rr.list.addLast($constant_expression.r);})*
    ;
plain_declarator returns [PlDecltor r]
    : Identifier {$r = new SmpPlDecltor(symbol($Identifier.text));}
    | '*' plain_declarator {$r = new PtrPlDecltor($plain_declarator.r);}
    ;

//Statements
statement returns [Stmt r]
    : expression_statement {$r = $expression_statement.r;}
    | compound_statement {$r = $compound_statement.r;}
    | selection_statement {$r = $selection_statement.r;}
    | iteration_statement {$r = $iteration_statement.r;}
    | jump_statement {$r = $jump_statement.r;}
    ;
statements returns [Stmts r]
    locals [Stmts rr]
    @after {$r = $rr;}
    : {$r = null;}
    | statement{$rr = new Stmts($statement.r);} (statement{$rr.list.addLast($statement.r);})*
    ;
expression_statement returns [ExprStmt r]
    : ';' {$r = new ExprStmt(null);}
    | expression ';'{$r = new ExprStmt($expression.r);};
// no ParserTest
compound_statement returns [CpdStmt r]
    : '{' declarations statements '}'{$r = new CpdStmt($declarations.r, $statements.r);}
    ;

declarations returns [Declars r]
    :{$r = null;}
    |declaration declarations{
        if ($declarations.r == null) $r = new Declars($declaration.r);
        else { $declarations.r.list.addFirst($declaration.r); $r = $declarations.r;}
        }
    ;
selection_statement returns [SelStmt r]
    : 'if' '(' expression ')' statement {$r = new SelStmt($expression.r, $statement.r, null);}
    | 'if' '(' expression ')' stmt1 = statement ('else' stmt2 = statement){$r = new SelStmt($expression.r, $stmt1.r, $stmt2.r);};

iteration_statement returns [IterStmt r]
    : 'while' '(' expression ')' statement {$r = new WhileStmt($expression.r, $statement.r);}
    | 'for' '('  ';'  ';'  ')' statement {$r = new ForStmt(null, null, null, $statement.r);}
    | 'for' '('  ';'  ';' e3 = expression ')' statement {$r = new ForStmt(null, null, $e3.r, $statement.r);}
    | 'for' '('  ';' e2 = expression ';'  ')' statement {$r = new ForStmt(null, $e2.r, null, $statement.r);}
    | 'for' '('  ';' e2 = expression ';' e3 = expression ')' statement {$r = new ForStmt(null, $e2.r, $e3.r, $statement.r);}
    | 'for' '(' e1 = expression ';'  ';'  ')' statement {$r = new ForStmt($e1.r, null, null, $statement.r);}
    | 'for' '(' e1 = expression ';'  ';' e3 = expression ')' statement {$r = new ForStmt($e1.r, null, $e3.r, $statement.r);}
    | 'for' '(' e1 = expression ';' e2 = expression ';'  ')' statement {$r = new ForStmt($e1.r, $e2.r, null, $statement.r);}
    | 'for' '(' e1 = expression ';' e2 = expression ';' e3 = expression ')' statement {$r = new ForStmt($e1.r, $e2.r, $e3.r, $statement.r);}
    ;

jump_statement returns [JmpStmt r]
    : 'continue' ';'{$r = new ContinueStmt();}
    | 'break' ';' {$r = new BreakStmt();}
    | 'return' ';' {$r = new ReturnStmt(null);}
    | 'return 'expression {$r = new ReturnStmt($expression.r);}';'
    ;

// Expressions
expression returns [Exprs r]
    : assignment_expression {$r = new Exprs($assignment_expression.r);}
    | assignment_expression ',' expression {$expression.r.list.addFirst($assignment_expression.r); $r = $expression.r;};

assignment_expression returns [Expr r]
    : logical_or_expression {$r = $logical_or_expression.r;}
    | ue = unary_expression '=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.ASSIGN);}
    | ue = unary_expression '*=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.MUL_ASSIGN);}
    | ue = unary_expression '/=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.DIV_ASSIGN);}
    | ue = unary_expression '%=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.MOD_ASSIGN);}
    | ue = unary_expression '+=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.ADD_ASSIGN);}
    | ue = unary_expression '-=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.SUB_ASSIGN);}
    | ue = unary_expression '<<=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.SHL_ASSIGN);}
    | ue = unary_expression '>>=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.SHR_ASSIGN);}
    | ue = unary_expression '&=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.AND_ASSIGN);}
    | ue = unary_expression '^=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.XOR_ASSIGN);}
    | ue = unary_expression '|=' ae = assignment_expression {$r = new Op($ue.r, $ae.r, Op.OpType.OR_ASSIGN);}
    ;
// not used

constant_expression returns [Expr r]
    : logical_or_expression{$r = $logical_or_expression.r;};

logical_or_expression returns [Expr r]
    : logical_and_expression {$r = $logical_and_expression.r;}
    | loe = logical_or_expression '||' lae = logical_and_expression {$r = new Op($loe.r, $lae.r, Op.OpType.PARAOR);}
    ;

logical_and_expression returns [Expr r]
    : inclusive_or_expression {$r = $inclusive_or_expression.r;}
    | lae = logical_and_expression'&&' ioe = inclusive_or_expression {$r = new Op($lae.r, $ioe.r, Op.OpType.PARAAND);}
    ;

inclusive_or_expression returns [Expr r]
    : exclusive_or_expression {$r = $exclusive_or_expression.r;}
    | ioe = inclusive_or_expression '|' eoe = exclusive_or_expression {$r = new Op($ioe.r, $eoe.r, Op.OpType.OR);}
    ;

exclusive_or_expression returns [Expr r]
    : and_expression {$r = $and_expression.r;}
    | eoe = exclusive_or_expression '^' ae = and_expression {$r = new Op($eoe.r, $ae.r, Op.OpType.XOR);}
    ;

and_expression returns [Expr r]
    : equality_expression {$r = $equality_expression.r;}
    | ae = and_expression '&' ee = equality_expression {$r = new Op($ae.r, $ee.r, Op.OpType.AND);}
    ;

equality_expression returns [Expr r]
    : relational_expression {$r = $relational_expression.r;}
    | ee = equality_expression '==' re = relational_expression {$r = new Op($ee.r, $re.r, Op.OpType.EQ);}
    | ee = equality_expression '!=' re = relational_expression {$r = new Op($ee.r, $re.r, Op.OpType.NE);}
    ;

relational_expression returns [Expr r]
    : shift_expression {$r = $shift_expression.r;}
    | re = relational_expression '<' se = shift_expression {$r = new Op($re.r, $se.r, Op.OpType.LT);}
    | re = relational_expression '>' se = shift_expression {$r = new Op($re.r, $se.r, Op.OpType.GT);}
    | re = relational_expression '<=' se = shift_expression {$r = new Op($re.r, $se.r, Op.OpType.LE);}
    | re = relational_expression '>=' se = shift_expression {$r = new Op($re.r, $se.r, Op.OpType.GE);}
    ;

shift_expression returns [Expr r]
    : additive_expression {$r = $additive_expression.r;}
    | se = shift_expression '<<' ae = additive_expression {$r = new Op($se.r, $ae.r, Op.OpType.SHL);}
    | se = shift_expression '>>' ae = additive_expression {$r = new Op($se.r, $ae.r, Op.OpType.SHR);}
    ;

additive_expression returns [Expr r]
    : multiplicative_expression {$r = $multiplicative_expression.r;}
    | ae = additive_expression '+' me = multiplicative_expression {$r = new Op($ae.r, $me.r, Op.OpType.PLUS);}
    | ae = additive_expression '-' me = multiplicative_expression {$r = new Op($ae.r, $me.r, Op.OpType.MINUS);}
    ;

multiplicative_expression returns [Expr r]
    : cast_expression {$r = $cast_expression.r;}
    | me = multiplicative_expression '*' ce = cast_expression {$r = new Op($me.r, $ce.r, Op.OpType.TIMES);}
    | me = multiplicative_expression '/' ce = cast_expression {$r = new Op($me.r, $ce.r, Op.OpType.DIVIDE);}
    | me = multiplicative_expression '%' ce = cast_expression {$r = new Op($me.r, $ce.r, Op.OpType.MOD);}
    ;

cast_expression returns [Expr r]
    : unary_expression {$r = $unary_expression.r;}
    | '(' type_name ')' cast_expression {$r = new CastExpr($type_name.r, $cast_expression.r);}
    ;

type_name returns [TypeName r]
    : type_specifier {$r = new SmpTypeName($type_specifier.r);}
    | type_name '*' {$r = new PtrTypeName($type_name.r);}
    ;

unary_expression returns [Expr r]
    : postfix_expression {$r = $postfix_expression.r;}
    | '++' ue = unary_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.INC, $ue.r);}
    | '--' ue = unary_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.DEC, $ue.r);}
    | '&' ce = cast_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.AND, $ce.r);}
    | '*' ce = cast_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.TIMES, $ce.r);}
    | '+' ce = cast_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.PLUS, $ce.r);}
    | '-' ce = cast_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.MINUS, $ce.r);}
    | '~' ce = cast_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.TIDLE, $ce.r);}
    | '!' ce = cast_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.NOT, $ce.r);}
    | 'sizeof' ue = unary_expression {$r = new UnaryExpr(UnaryExpr.UnaryType.SIZEOF, $ue.r);}
    | 'sizeof' '(' tn = type_name ')'{$r = new TypeSizeExpr($tn.r);}
    ;

postfix_expression returns [Expr r]
    : primary_expression {$r = $primary_expression.r;}
    | pe = postfix_expression '++' {$r = new AccExpr(AccExpr.AccType.INC, $pe.r);}
    | pe = postfix_expression '--' {$r = new AccExpr(AccExpr.AccType.DEC, $pe.r);}
    | pe = postfix_expression '.' id = Identifier {$r = new PostfixExpr($pe.r, symbol($id.text),PostfixExpr.PfType.DOT);}
    | pe = postfix_expression '->' id = Identifier {$r = new PostfixExpr($pe.r, symbol($id.text),PostfixExpr.PfType.PTR);}
    | pe = postfix_expression '[' e = expression ']' {$r = new AdrsExpr($pe.r, $e.r);}
    | pe = postfix_expression '(' a = arguments ')' {$r = new CallExpr($pe.r, $a.r);}
    ;

arguments returns [Arguments r]
    : {$r = null;}
    | ae = assignment_expression {$r = new Arguments($ae.r);}
    | ae = assignment_expression ',' a = arguments {
            if($a.r == null) $r = new Arguments($ae.r);
            else {$a.r.list.addFirst($ae.r); $r = $a.r;}
        }
    ;

primary_expression returns [Expr r]
    : id = Identifier {$r = new Var(symbol($id.text));}
    | IntegerConstant {$r = new Num($IntegerConstant.int);}
    | CharacterConstant {char temp = $CharacterConstant.text.charAt(0); $r = new CharConst(temp);}
    | StringConstant {$r = new StringConst($StringConstant.text);}
    | '(' expression ')' {$r = $expression.r;}
    ;
//Constant
//    :   IntegerConstant
//    |   CharacterConstant
//    ;
WS: [ \t\r\n]+ -> channel(HIDDEN);

IntegerConstant
    :   DecimalConstant IntegerSuffix?
    |   OctalConstant IntegerSuffix?
    |   HexadecimalConstant IntegerSuffix?
    ;


DecimalConstant
    :   NonzeroDigit Digit* | '0'
    ;

fragment
OctalConstant
    :   '0' OctalDigit*
    ;

fragment
HexadecimalConstant
    :   HexadecimalPrefix HexadecimalDigit+
    ;

fragment
HexadecimalPrefix
    :   '0' [xX]
    ;

fragment
NonzeroDigit
    :   [1-9]
    ;

fragment
OctalDigit
    :   [0-7]
    ;

fragment
HexadecimalDigit
    :   [0-9a-fA-F]
    ;
fragment
IntegerSuffix
    :   UnsignedSuffix LongSuffix?
    |   UnsignedSuffix LongLongSuffix
    |   LongSuffix UnsignedSuffix?
    |   LongLongSuffix UnsignedSuffix?
    ;

fragment
UnsignedSuffix
    :   [uU]
    ;

fragment
LongSuffix
    :   [lL]
    ;

fragment
LongLongSuffix
    :   'll' | 'LL'
    ;
CharacterConstant
    :   '\'' CChar '\''
    |   'L\'' CChar '\''
    |   'u\'' CChar '\''
    |   'U\'' CChar '\''
    ;
fragment
CChar
    :   (EscapeSequence | ~['\\])
    ;
fragment
EscapeSequence
    :   SimpleEscapeSequence
    |   OctalEscapeSequence
    |   HexadecimalEscapeSequence
    |   UniversalCharacterName
    ;
fragment
SimpleEscapeSequence
    :   '\\' ['"?abfnrtv\\]
    ;
fragment
OctalEscapeSequence
    :   '\\' [0-7]
    |   '\\' [0-7] [0-7]
    |   '\\' [0-7] [0-7] [0-7]
    ;
fragment
HexadecimalEscapeSequence
    :   '\\x' [0-9a-fA-F]+
    ;
UniversalCharacterName
    :   '\\u' HexQuad
    |   '\\U' HexQuad HexQuad
    ;
fragment
HexQuad
    :  [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F]
    ;
//lexical
//symbols
COMMA: ',';
COLON: ':';
SEMI_COLON: ';';
OPEN_PAREN: '(';
CLOSE_PAREN: ')';
OPEN_BRACK: '[';
CLOSE_BRACK: ']';
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
//whitespace

LineTerm
    : ('\n' | '\r' | '\n\r' | '\r\n');
// #
PreProcessor
    : '#' (~('\n' | '\r'))* LineTerm ->channel(HIDDEN);
//comment, not completely comprehended
COMMENT
    : (TraditionalComment | SingleLineComment | DocumentationComment )
    ->channel(HIDDEN);
fragment TraditionalComment
    : '/*' [^*]* '*/';
fragment SingleLineComment
    : '//' (InputCharacter)* LineTerm;
fragment DocumentationComment
    : '/**' CommentContent '*'+ '/';
fragment InputCharacter
    : [^\r\n];
fragment CommentContent
    : ( [^*] | '*'+ [^/*] )*;
// String
StringConstant: '"' ScharSequence? '"';
fragment ScharSequence: SChar+;
// can be modified?
fragment SChar: ~["\\\r\n] | EscapeSequence;

//Identifiers
Identifier:
    (Letter | '_' | '$' ) ( '_' | '$' | Letter | Digit )*;
fragment Letter: [a-zA-Z];
fragment Digit: [0-9];
// Top level
