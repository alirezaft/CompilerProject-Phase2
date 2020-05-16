// Generated from E:/Projects/CompilerProject-Phase2/grammar\MiniJava.g4 by ANTLR 4.8
package com.alirezaft.Minijava.gen;

import com.alirezaft.Minijava.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class provides an empty implementation of {@link MiniJavaListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
public class MiniJavaBaseListener implements MiniJavaListener {
	Stack<ScopeNodeGraph> CurrParents = new Stack<>();
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterProgram(MiniJavaParser.ProgramContext ctx) {
		SymbolTable st = new SymbolTable();

		MiniJavaParser.ClassDeclarationContext Class;
		for(int i = 0; ctx.classDeclaration(i) != null; i++){
			Class = ctx.classDeclaration(i);
			ArrayList<String> Interfaces = new ArrayList<>();

			if(Class.getText().contains("implements")){
				for(int j = Class.parentName == null ? 1 : 2; Class.Identifier(j) != null; j++){
					Interfaces.add(Class.Identifier(i).getText());
				}
			}
			String[] s = new String[1];
			st.insert("class_" + Class.className.getText(), new ClassToken(Class.className.getText(),
					Class.parentName == null ? "Object" : Class.parentName.getText(),
					Interfaces.size() != 0 ? Interfaces.toArray(s) : null));
		}

		MiniJavaParser.InterfaceDeclarationContext Interface;
		for(int i = 0; ctx.interfaceDeclaration(i) != null; i++){
			Interface = ctx.interfaceDeclaration(i);
			st.insert("interface_" + Interface.Identifier().getText(),
					new InterfaceToken("interface_" + Interface.Identifier().getText(), Interface.Identifier().getText()));
		}

		MiniJavaParser.MainClassContext Main = ctx.mainClass();
		st.insert("mainClass_" + Main.className.getText(), new MainClassToken(Main.className.getText()));

		ScopeNodeGraph node = new ScopeNodeGraph("program", st, ctx.getStart().getLine());
		ScopeNodeGraph.setRoot(node);
		CurrParents.push(node);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitProgram(MiniJavaParser.ProgramContext ctx) {
		ScopeNodeGraph.printSymbolTables();
		CurrParents.pop();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMainClass(MiniJavaParser.MainClassContext ctx) {

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMainClass(MiniJavaParser.MainClassContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMainMethod(MiniJavaParser.MainMethodContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMainMethod(MiniJavaParser.MainMethodContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
		SymbolTable st = new SymbolTable();

		MiniJavaParser.FieldDeclarationContext Field;
		for(int i = 0; ctx.fieldDeclaration(i) != null; i++){
			Field = ctx.fieldDeclaration(i);
			boolean Array, Class;
			Array = Field.type().LSB() != null;
			Class = Field.type().javaType() == null;
			if(Field.type().Identifier() == null){
				st.insert("var_" + Field.Identifier().getText(), new FieldToken(Field.Identifier().getText(), Field.Identifier().getText(),
						(Field.accessModifier() == null || Field.accessModifier().getText().equals("public")) ?
						"public" : "private", Class, Field.type().javaType().getText(), false, Array));
			}else{
				st.insert("var_" + Field.Identifier().getText(), new FieldToken(Field.Identifier().getText(), Field.Identifier().getText(),
						(Field.accessModifier() == null || Field.accessModifier().getText().equals("public")) ?
						"public" : "private", Class, Field.type().Identifier().getText(),
						ScopeNodeGraph.getRoot().getSymbolTable().lookup("class_" + Field.type().Identifier().getText()) != null, Array));
			}

		}

		MiniJavaParser.MethodDeclarationContext Method;
		for(int i = 0; ctx.methodDeclaration(i) != null; i++){
			Method = ctx.methodDeclaration(i);
			ArrayList<ParameterDeclaration> params = new ArrayList<>();
			MiniJavaParser.ParameterListContext plist = Method.parameterList();
			if(plist != null){
				for(int j = 0; plist.parameter(j) != null; j++){
					MiniJavaParser.ParameterContext pa = plist.parameter(j);
					ParameterDeclaration p = new ParameterDeclaration(pa.type().getText(), pa.Identifier().getText(), pa.type().LSB() != null,
							pa.type().javaType() == null, false, j + 1);
					params.add(p);
				}
			}
			if(Method.returnType().getText().equals("void")){
				st.insert("method_" + Method.Identifier().getText(), new MethodToken(Method.Identifier().getText(),
						Method.Identifier().getText(), Method.accessModifier() == null || Method.accessModifier().getText().equals("private") ?
						"private" : "public",
						"void", params, false, false, false));

			}else{
				if(Method.returnType().type().Identifier() != null){
					st.insert("method_" + Method.Identifier().getText(), new MethodToken(Method.Identifier().getText(),
							Method.Identifier().getText(), Method.accessModifier() == null || Method.accessModifier().getText().equals("private") ?
							"private" : "public",
							Method.returnType().type().Identifier().getText(),
							params,
							Method.returnType().type().LSB() != null,
							true,
							ScopeNodeGraph.getRoot().getSymbolTable().lookup("class_" + Method.returnType().type().Identifier().getText()) != null));
				}else{
					st.insert("method_" + Method.Identifier().getText(), new MethodToken(Method.Identifier().getText(),
							Method.Identifier().getText(), Method.accessModifier() == null || Method.accessModifier().getText().equals("private") ?
							"private" : "public",
							Method.returnType().type().javaType().getText(),
							params,
							Method.returnType().type().LSB() != null,
							false, false));
				}
			}
		}
		ScopeNodeGraph node = new ScopeNodeGraph(ctx.Identifier(0).getText(), st, ctx.getStart().getLine());
		CurrParents.peek().getChildren().add(node);
		CurrParents.push(node);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
		CurrParents.pop();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterInterfaceDeclaration(MiniJavaParser.InterfaceDeclarationContext ctx) {
		SymbolTable st = new SymbolTable();

		MiniJavaParser.FieldDeclarationContext Field;
		for(int i = 0; ctx.fieldDeclaration(i) != null; i++){
			Field = ctx.fieldDeclaration(i);
			boolean Array, Class;
			Array = Field.type().LSB() != null;
			Class = Field.type().javaType() == null;
			if(Field.type().Identifier() != null){
				st.insert("var_" + Field.Identifier(), new FieldToken(Field.fieldName.getText(), Field.fieldName.getText(),
						Field.accessModifier() == null || Field.accessModifier().getText().equals("public") ?
						"public" : "private", Class, Field.type().Identifier().getText()
						, ScopeNodeGraph.getRoot().getSymbolTable().lookup("class_" + Field.type().Identifier().getText()) != null , Array));
			}else{
				st.insert("var_" + Field.Identifier(), new FieldToken(Field.fieldName.getText(), Field.fieldName.getText(),
						Field.accessModifier() == null || Field.accessModifier().getText().equals("public") ?
						"public" : "private", Class, Field.type().javaType().getText(),false, Array));
			}
		}

		MiniJavaParser.InterfaceMethodDeclarationContext intmethod;
		for(int i = 0; ctx.interfaceMethodDeclaration(i) != null; i++){
			intmethod = ctx.interfaceMethodDeclaration(i);
			ArrayList<ParameterDeclaration> params = new ArrayList<>();
			MiniJavaParser.ParameterListContext plist = intmethod.parameterList();
			if(plist != null){
				for(int j = 0; plist.parameter(j) != null; j++){
					MiniJavaParser.ParameterContext pa = plist.parameter(j);
					ParameterDeclaration p = new ParameterDeclaration(pa.type().Identifier() != null ? pa.type().Identifier().getText() :
							pa.type().javaType().getText()
							, pa.Identifier().getText(), pa.type().LSB() != null,
							pa.type().javaType() == null, false, j + 1);
					params.add(p);
				}
			}
			if(intmethod.returnType().getText().equals("void")){
				st.insert("method_" + intmethod.Identifier().getText(), new MethodToken(intmethod.Identifier().getText(),
						intmethod.Identifier().getText(), intmethod.accessModifier() == null || intmethod.accessModifier().getText().equals("private") ?
						"private" : "public",
						"void",
						params, false, false, false));
			}else{
				if(intmethod.returnType().type().Identifier() != null){
					st.insert("method_" + intmethod.Identifier().getText(), new MethodToken(intmethod.Identifier().getText(),
							intmethod.Identifier().getText(), intmethod.accessModifier() == null || intmethod.accessModifier().getText().equals("private") ?
							"private" : "public",
							intmethod.returnType().type().Identifier().getText(),
							params,
							intmethod.returnType().type().LSB() != null,
							true,
							ScopeNodeGraph.getRoot().getSymbolTable().lookup("class_" + intmethod.returnType().type().Identifier().getText()) != null));
				}else{
					st.insert("method_" + intmethod.Identifier().getText(), new MethodToken(intmethod.Identifier().getText(),
							intmethod.Identifier().getText(), intmethod.accessModifier() == null || intmethod.accessModifier().getText().equals("private") ?
							"private" : "public",
							intmethod.returnType().type().javaType().getText(),
							params,
							intmethod.returnType().type().LSB() != null,
							false, false));
				}
			}
		}


		ScopeNodeGraph node = new ScopeNodeGraph(ctx.interfaceName.getText(), st, ctx.getStart().getLine());
		CurrParents.peek().getChildren().add(node);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitInterfaceDeclaration(MiniJavaParser.InterfaceDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterInterfaceMethodDeclaration(MiniJavaParser.InterfaceMethodDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitInterfaceMethodDeclaration(MiniJavaParser.InterfaceMethodDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFieldDeclaration(MiniJavaParser.FieldDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFieldDeclaration(MiniJavaParser.FieldDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterLocalDeclaration(MiniJavaParser.LocalDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitLocalDeclaration(MiniJavaParser.LocalDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
		SymbolTable st = new SymbolTable();

		MiniJavaParser.StatementContext Statement;

		for(int i = 0; ctx.methodBody().statement(i) != null; i++){
			Statement = ctx.methodBody().statement(i);
			if(Statement instanceof MiniJavaParser.LocalVarDeclarationContext){
				MiniJavaParser.LocalVarDeclarationContext l = (MiniJavaParser.LocalVarDeclarationContext) Statement;
				LocalVarToken var = new LocalVarToken(l.localDeclaration().verName.getText(), l.localDeclaration().type().getText(),
						l.localDeclaration().type().LSB() != null, l.localDeclaration().type().javaType() == null,
						true);
				st.insert("var_" + l.localDeclaration().verName.getText(), var);
			}
		}

		ArrayList<ParameterToken> params = new ArrayList<>();
		MiniJavaParser.ParameterListContext plist = ctx.parameterList();
		if(plist != null){
			for(int j = 0; plist.parameter(j) != null; j++){
				MiniJavaParser.ParameterContext pa = plist.parameter(j);
				ParameterToken p = new ParameterToken(pa.type().getText(), pa.Identifier().getText(), pa.type().LSB() != null,
						pa.type().javaType() == null, false, j + 1);
				params.add(p);
				st.insert("var_" + pa.Identifier(), p);
			}
		}

		ScopeNodeGraph node = new ScopeNodeGraph(ctx.methodName.getText(), st, ctx.getStart().getLine());
		CurrParents.peek().getChildren().add(node);
		CurrParents.push(node);

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
		CurrParents.pop();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterParameterList(MiniJavaParser.ParameterListContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitParameterList(MiniJavaParser.ParameterListContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterParameter(MiniJavaParser.ParameterContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitParameter(MiniJavaParser.ParameterContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMethodBody(MiniJavaParser.MethodBodyContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMethodBody(MiniJavaParser.MethodBodyContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterType(MiniJavaParser.TypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitType(MiniJavaParser.TypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterBooleanType(MiniJavaParser.BooleanTypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitBooleanType(MiniJavaParser.BooleanTypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIntType(MiniJavaParser.IntTypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIntType(MiniJavaParser.IntTypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterReturnType(MiniJavaParser.ReturnTypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitReturnType(MiniJavaParser.ReturnTypeContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAccessModifier(MiniJavaParser.AccessModifierContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitAccessModifier(MiniJavaParser.AccessModifierContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterNestedStatement(MiniJavaParser.NestedStatementContext ctx) {
		if(!(ctx.getParent() instanceof MiniJavaParser.MethodBodyContext ||
				ctx.getParent() instanceof MiniJavaParser.MainMethodContext)){
			return;
		}

		SymbolTable st = new SymbolTable();

		MiniJavaParser.StatementContext Statement;
		for(int j = 0; ctx.statement(j) != null; j++){
			Statement = ctx.statement(j);
			MiniJavaParser.LocalDeclarationContext loc;
			for(int i = 0; Statement.getChild(MiniJavaParser.LocalDeclarationContext.class, i) != null; i++){
				loc = Statement.getChild(MiniJavaParser.LocalDeclarationContext.class, i);

				if(loc.type().javaType() == null){
					st.insert("var_" + loc.verName.getText(), new LocalVarToken(loc.verName.getText(),
							loc.type().Identifier().getText(),
							loc.type().LSB() != null, true,
							ScopeNodeGraph.getRoot().getSymbolTable().lookup("class_" + loc.type().Identifier().getText()) != null));
				}else{
					st.insert("var_" + loc.verName.getText(), new LocalVarToken(loc.verName.getText(),
							loc.type().javaType().getText(),
							loc.type().LSB() != null, false, false));
				}

			}
		}

		ScopeNodeGraph node = new ScopeNodeGraph("nested", st, ctx.getStart().getLine());
		CurrParents.peek().getChildren().add(node);
		CurrParents.push(node);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitNestedStatement(MiniJavaParser.NestedStatementContext ctx) {
		if((ctx.getParent() instanceof MiniJavaParser.MethodBodyContext ||
				ctx.getParent() instanceof MiniJavaParser.MainMethodContext)){
			CurrParents.pop();
		}

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIfElseStatement(MiniJavaParser.IfElseStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIfElseStatement(MiniJavaParser.IfElseStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterWhileStatement(MiniJavaParser.WhileStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitWhileStatement(MiniJavaParser.WhileStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterPrintStatement(MiniJavaParser.PrintStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitPrintStatement(MiniJavaParser.PrintStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterVariableAssignmentStatement(MiniJavaParser.VariableAssignmentStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitVariableAssignmentStatement(MiniJavaParser.VariableAssignmentStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterArrayAssignmentStatement(MiniJavaParser.ArrayAssignmentStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitArrayAssignmentStatement(MiniJavaParser.ArrayAssignmentStatementContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterLocalVarDeclaration(MiniJavaParser.LocalVarDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitLocalVarDeclaration(MiniJavaParser.LocalVarDeclarationContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterExpressioncall(MiniJavaParser.ExpressioncallContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitExpressioncall(MiniJavaParser.ExpressioncallContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIfBlock(MiniJavaParser.IfBlockContext ctx) {
		SymbolTable st = new SymbolTable();

		MiniJavaParser.StatementContext Statement = ctx.statement();
		MiniJavaParser.LocalDeclarationContext loc;
		for(int i = 0; Statement.getChild(MiniJavaParser.LocalDeclarationContext.class, i) != null; i++){
			loc = Statement.getChild(MiniJavaParser.LocalDeclarationContext.class, i);
			if(loc.type().javaType() == null){
				st.insert("var_" + loc.verName.getText(), new LocalVarToken(loc.verName.getText(),
						loc.type().Identifier().getText(),
						loc.type().LSB() != null, true,
						ScopeNodeGraph.getRoot().getSymbolTable().lookup("class_" + loc.type().Identifier().getText()) != null));
			}else{
				st.insert("var_" + loc.verName.getText(), new LocalVarToken(loc.verName.getText(),
						loc.type().javaType().getText(),
						loc.type().LSB() != null, false, false));
			}
		}

		ScopeNodeGraph node = new ScopeNodeGraph("if", st, ctx.getStart().getLine());
		CurrParents.peek().getChildren().add(node);
		CurrParents.push(node);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIfBlock(MiniJavaParser.IfBlockContext ctx) {
		CurrParents.pop();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterElseBlock(MiniJavaParser.ElseBlockContext ctx) {
		SymbolTable st = new SymbolTable();

		MiniJavaParser.StatementContext Statement = ctx.statement();
		MiniJavaParser.LocalDeclarationContext loc;
		for(int i = 0; Statement.getChild(MiniJavaParser.LocalDeclarationContext.class, i) != null; i++){
			loc = Statement.getChild(MiniJavaParser.LocalDeclarationContext.class, i);
			if(loc.type().javaType() == null){
				st.insert("var_" + loc.verName.getText(), new LocalVarToken(loc.verName.getText(),
						loc.type().Identifier().getText(),
						loc.type().LSB() != null, true,
						ScopeNodeGraph.getRoot().getSymbolTable().lookup("class_" + loc.type().Identifier().getText()) != null));
			}else{
				st.insert("var_" + loc.verName.getText(), new LocalVarToken(loc.verName.getText(),
						loc.type().javaType().getText(),
						loc.type().LSB() != null, false, false));
			}
		}

		ScopeNodeGraph node = new ScopeNodeGraph("else", st, ctx.getStart().getLine());
		CurrParents.peek().getChildren().add(node);
		CurrParents.push(node);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitElseBlock(MiniJavaParser.ElseBlockContext ctx) {
		CurrParents.pop();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterWhileBlock(MiniJavaParser.WhileBlockContext ctx) {
		SymbolTable st = new SymbolTable();

		MiniJavaParser.StatementContext Statement = ctx.statement();
		MiniJavaParser.LocalDeclarationContext loc;
		for(int i = 0; Statement.getChild(MiniJavaParser.LocalDeclarationContext.class, i) != null; i++){
			loc = Statement.getChild(MiniJavaParser.LocalDeclarationContext.class, i);

			if(loc.type().javaType() == null){
				st.insert("var_" + loc.verName.getText(), new LocalVarToken(loc.verName.getText(),
						loc.type().Identifier().getText(),
						loc.type().LSB() != null, true,
						ScopeNodeGraph.getRoot().getSymbolTable().lookup("class_" + loc.type().Identifier().getText()) != null));
			}else{
				st.insert("var_" + loc.verName.getText(), new LocalVarToken(loc.verName.getText(),
						loc.type().javaType().getText(),
						loc.type().LSB() != null, false, false));
			}
		}

		ScopeNodeGraph node = new ScopeNodeGraph("while", st, ctx.getStart().getLine());
		CurrParents.peek().getChildren().add(node);
		CurrParents.push(node);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitWhileBlock(MiniJavaParser.WhileBlockContext ctx) {
		CurrParents.pop();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterLtExpression(MiniJavaParser.LtExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitLtExpression(MiniJavaParser.LtExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterObjectInstantiationExpression(MiniJavaParser.ObjectInstantiationExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitObjectInstantiationExpression(MiniJavaParser.ObjectInstantiationExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterArrayInstantiationExpression(MiniJavaParser.ArrayInstantiationExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitArrayInstantiationExpression(MiniJavaParser.ArrayInstantiationExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterPowExpression(MiniJavaParser.PowExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitPowExpression(MiniJavaParser.PowExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIdentifierExpression(MiniJavaParser.IdentifierExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIdentifierExpression(MiniJavaParser.IdentifierExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMethodCallExpression(MiniJavaParser.MethodCallExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMethodCallExpression(MiniJavaParser.MethodCallExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterNotExpression(MiniJavaParser.NotExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitNotExpression(MiniJavaParser.NotExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterBooleanLitExpression(MiniJavaParser.BooleanLitExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitBooleanLitExpression(MiniJavaParser.BooleanLitExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterParenExpression(MiniJavaParser.ParenExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitParenExpression(MiniJavaParser.ParenExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIntLitExpression(MiniJavaParser.IntLitExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIntLitExpression(MiniJavaParser.IntLitExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterStringLitExpression(MiniJavaParser.StringLitExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitStringLitExpression(MiniJavaParser.StringLitExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterNullLitExpression(MiniJavaParser.NullLitExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitNullLitExpression(MiniJavaParser.NullLitExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAndExpression(MiniJavaParser.AndExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitAndExpression(MiniJavaParser.AndExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterArrayAccessExpression(MiniJavaParser.ArrayAccessExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitArrayAccessExpression(MiniJavaParser.ArrayAccessExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAddExpression(MiniJavaParser.AddExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitAddExpression(MiniJavaParser.AddExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterThisExpression(MiniJavaParser.ThisExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitThisExpression(MiniJavaParser.ThisExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFieldCallExpression(MiniJavaParser.FieldCallExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitFieldCallExpression(MiniJavaParser.FieldCallExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterArrayLengthExpression(MiniJavaParser.ArrayLengthExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitArrayLengthExpression(MiniJavaParser.ArrayLengthExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIntarrayInstantiationExpression(MiniJavaParser.IntarrayInstantiationExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIntarrayInstantiationExpression(MiniJavaParser.IntarrayInstantiationExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterSubExpression(MiniJavaParser.SubExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitSubExpression(MiniJavaParser.SubExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMulExpression(MiniJavaParser.MulExpressionContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMulExpression(MiniJavaParser.MulExpressionContext ctx) { }

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitTerminal(TerminalNode node) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void visitErrorNode(ErrorNode node) { }
}