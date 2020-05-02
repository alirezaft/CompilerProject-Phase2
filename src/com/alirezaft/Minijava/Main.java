package com.alirezaft.Minijava;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.alirezaft.Minijava.gen.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        CharStream Stream = null;
        try {
            Stream = CharStreams.fromFileName("Code.mj");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MiniJavaLexer lexer = new MiniJavaLexer(Stream);
        MiniJavaBaseListener listener = new MiniJavaBaseListener();
        CommonTokenStream TokenStream = new CommonTokenStream(lexer);
        MiniJavaParser parser = new MiniJavaParser(TokenStream);
        ParseTree Ptree = parser.program();
        ParseTreeWalker PtreeWalker = new ParseTreeWalker();
        MiniJavaListener MJListener = new MiniJavaBaseListener();
        PtreeWalker.walk(MJListener, Ptree);

    }
}
