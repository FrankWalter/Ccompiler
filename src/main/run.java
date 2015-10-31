package main;
import syntactic.CLexer;
import syntactic.CParser;
import com.google.gson.Gson;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import absyn.*;

import java.util.Arrays;
//import semantic.*;

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
//        ParseTree parsetree = parser.decls();
        Decls tree = parser.decls().r; // begin parsing at init rule
        Gson gson = new Gson();
        System.out.println(gson.toJson(tree));
//        Semantic semant = new Semantic();
//        semant.checkProg(tree);
//        if(semant.hasError()) {
//            semant.printErrors();
//        }
//       TreeViewer viewr = new TreeViewer(Arrays.asList(
 //              parser.getRuleNames()),parser.decls());
 //      viewr.open();
       // System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }
}

