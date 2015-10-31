// Generated from C.g4 by ANTLR 4.2

    package syntactic;
    import absyn.*;
    import symbol.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CParser}.
 */
public interface CListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CParser#array_parameters}.
	 * @param ctx the parse tree
	 */
	void enterArray_parameters(@NotNull CParser.Array_parametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#array_parameters}.
	 * @param ctx the parse tree
	 */
	void exitArray_parameters(@NotNull CParser.Array_parametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#compound_statement}.
	 * @param ctx the parse tree
	 */
	void enterCompound_statement(@NotNull CParser.Compound_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#compound_statement}.
	 * @param ctx the parse tree
	 */
	void exitCompound_statement(@NotNull CParser.Compound_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#equality_expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality_expression(@NotNull CParser.Equality_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#equality_expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality_expression(@NotNull CParser.Equality_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#cast_expression}.
	 * @param ctx the parse tree
	 */
	void enterCast_expression(@NotNull CParser.Cast_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#cast_expression}.
	 * @param ctx the parse tree
	 */
	void exitCast_expression(@NotNull CParser.Cast_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#type_name}.
	 * @param ctx the parse tree
	 */
	void enterType_name(@NotNull CParser.Type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#type_name}.
	 * @param ctx the parse tree
	 */
	void exitType_name(@NotNull CParser.Type_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#plain_declaration}.
	 * @param ctx the parse tree
	 */
	void enterPlain_declaration(@NotNull CParser.Plain_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#plain_declaration}.
	 * @param ctx the parse tree
	 */
	void exitPlain_declaration(@NotNull CParser.Plain_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(@NotNull CParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(@NotNull CParser.DeclContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#init_declarators}.
	 * @param ctx the parse tree
	 */
	void enterInit_declarators(@NotNull CParser.Init_declaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#init_declarators}.
	 * @param ctx the parse tree
	 */
	void exitInit_declarators(@NotNull CParser.Init_declaratorsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#init_declarator}.
	 * @param ctx the parse tree
	 */
	void enterInit_declarator(@NotNull CParser.Init_declaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#init_declarator}.
	 * @param ctx the parse tree
	 */
	void exitInit_declarator(@NotNull CParser.Init_declaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(@NotNull CParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(@NotNull CParser.StatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void enterPostfix_expression(@NotNull CParser.Postfix_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#postfix_expression}.
	 * @param ctx the parse tree
	 */
	void exitPostfix_expression(@NotNull CParser.Postfix_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void enterUnary_expression(@NotNull CParser.Unary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#unary_expression}.
	 * @param ctx the parse tree
	 */
	void exitUnary_expression(@NotNull CParser.Unary_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#logical_or_expression}.
	 * @param ctx the parse tree
	 */
	void enterLogical_or_expression(@NotNull CParser.Logical_or_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#logical_or_expression}.
	 * @param ctx the parse tree
	 */
	void exitLogical_or_expression(@NotNull CParser.Logical_or_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#inclusive_or_expression}.
	 * @param ctx the parse tree
	 */
	void enterInclusive_or_expression(@NotNull CParser.Inclusive_or_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#inclusive_or_expression}.
	 * @param ctx the parse tree
	 */
	void exitInclusive_or_expression(@NotNull CParser.Inclusive_or_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#iteration_statement}.
	 * @param ctx the parse tree
	 */
	void enterIteration_statement(@NotNull CParser.Iteration_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#iteration_statement}.
	 * @param ctx the parse tree
	 */
	void exitIteration_statement(@NotNull CParser.Iteration_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#and_expression}.
	 * @param ctx the parse tree
	 */
	void enterAnd_expression(@NotNull CParser.And_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#and_expression}.
	 * @param ctx the parse tree
	 */
	void exitAnd_expression(@NotNull CParser.And_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#selection_statement}.
	 * @param ctx the parse tree
	 */
	void enterSelection_statement(@NotNull CParser.Selection_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#selection_statement}.
	 * @param ctx the parse tree
	 */
	void exitSelection_statement(@NotNull CParser.Selection_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#constant_expression}.
	 * @param ctx the parse tree
	 */
	void enterConstant_expression(@NotNull CParser.Constant_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#constant_expression}.
	 * @param ctx the parse tree
	 */
	void exitConstant_expression(@NotNull CParser.Constant_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull CParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull CParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditive_expression(@NotNull CParser.Additive_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#additive_expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditive_expression(@NotNull CParser.Additive_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#shift_expression}.
	 * @param ctx the parse tree
	 */
	void enterShift_expression(@NotNull CParser.Shift_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#shift_expression}.
	 * @param ctx the parse tree
	 */
	void exitShift_expression(@NotNull CParser.Shift_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative_expression(@NotNull CParser.Multiplicative_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#multiplicative_expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative_expression(@NotNull CParser.Multiplicative_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_expression(@NotNull CParser.Primary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_expression(@NotNull CParser.Primary_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#struct_declarations}.
	 * @param ctx the parse tree
	 */
	void enterStruct_declarations(@NotNull CParser.Struct_declarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#struct_declarations}.
	 * @param ctx the parse tree
	 */
	void exitStruct_declarations(@NotNull CParser.Struct_declarationsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull CParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull CParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#type_specifier}.
	 * @param ctx the parse tree
	 */
	void enterType_specifier(@NotNull CParser.Type_specifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#type_specifier}.
	 * @param ctx the parse tree
	 */
	void exitType_specifier(@NotNull CParser.Type_specifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#expression_statement}.
	 * @param ctx the parse tree
	 */
	void enterExpression_statement(@NotNull CParser.Expression_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#expression_statement}.
	 * @param ctx the parse tree
	 */
	void exitExpression_statement(@NotNull CParser.Expression_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#assignment_expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_expression(@NotNull CParser.Assignment_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#assignment_expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_expression(@NotNull CParser.Assignment_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull CParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull CParser.DeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(@NotNull CParser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(@NotNull CParser.DeclaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(@NotNull CParser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(@NotNull CParser.DeclarationsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(@NotNull CParser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(@NotNull CParser.InitializerContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#exclusive_or_expression}.
	 * @param ctx the parse tree
	 */
	void enterExclusive_or_expression(@NotNull CParser.Exclusive_or_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#exclusive_or_expression}.
	 * @param ctx the parse tree
	 */
	void exitExclusive_or_expression(@NotNull CParser.Exclusive_or_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#declarators}.
	 * @param ctx the parse tree
	 */
	void enterDeclarators(@NotNull CParser.DeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#declarators}.
	 * @param ctx the parse tree
	 */
	void exitDeclarators(@NotNull CParser.DeclaratorsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#logical_and_expression}.
	 * @param ctx the parse tree
	 */
	void enterLogical_and_expression(@NotNull CParser.Logical_and_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#logical_and_expression}.
	 * @param ctx the parse tree
	 */
	void exitLogical_and_expression(@NotNull CParser.Logical_and_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#decls}.
	 * @param ctx the parse tree
	 */
	void enterDecls(@NotNull CParser.DeclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#decls}.
	 * @param ctx the parse tree
	 */
	void exitDecls(@NotNull CParser.DeclsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#plain_declarator}.
	 * @param ctx the parse tree
	 */
	void enterPlain_declarator(@NotNull CParser.Plain_declaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#plain_declarator}.
	 * @param ctx the parse tree
	 */
	void exitPlain_declarator(@NotNull CParser.Plain_declaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull CParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull CParser.ArgumentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#relational_expression}.
	 * @param ctx the parse tree
	 */
	void enterRelational_expression(@NotNull CParser.Relational_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#relational_expression}.
	 * @param ctx the parse tree
	 */
	void exitRelational_expression(@NotNull CParser.Relational_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(@NotNull CParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(@NotNull CParser.ParametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void enterFunction_definition(@NotNull CParser.Function_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void exitFunction_definition(@NotNull CParser.Function_definitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link CParser#jump_statement}.
	 * @param ctx the parse tree
	 */
	void enterJump_statement(@NotNull CParser.Jump_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#jump_statement}.
	 * @param ctx the parse tree
	 */
	void exitJump_statement(@NotNull CParser.Jump_statementContext ctx);
}