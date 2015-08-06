package Syntactic;
import com.google.gson.Gson;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.*;
import absyn.*;
import org.antlr.v4.runtime.tree.gui.TreeViewer;

import javax.print.PrintException;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * Created by liuzhe on 8/3/2015.
 */

public class run {
    public String startRuleName = "decls";
    public static void main(String[] args) throws Exception{
        ANTLRInputStream input = new ANTLRInputStream(System.in);
// create a lexer that feeds off of input CharStream
        CLexer lexer = new CLexer(input);
// create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
// create a parser that feeds off the tokens buffer
        CParser parser = new CParser(tokens);
        ParseTree parsetree = parser.decls();
        Decls tree = parser.decls().r; // begin parsing at init rule
        Gson gson = new Gson();
        System.out.println(gson.toJson(tree));
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),parsetree);
        viewr.open();
       // System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}

