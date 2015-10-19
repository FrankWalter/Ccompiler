package semantic;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import absyn.*;
import addr.Label;
import env.*;
import symbol.Symbol;
//import translate.Translate;
import type.*;
import util.*;
import util.Error;
/**
 * Created by liuzhe on 8/19/2015.
 */
public class Semantic {
    private Env env = null;
    private List<Error> errors = new LinkedList<Error>();
    static  enum RecordMark { STRUCT, UNION }
    public Type returnType;
    private int loopCount = 0;

    private static Symbol symbol( String s ) {
        return Symbol.getSymbol(s);
    }

    private void putType( Symbol name, Type t ) {
        TableDealer tmp = env.types.get(name);
        if(!(tmp == null) && (tmp.level == env.types.level))
            error("Redeclaration on type symbol: " + name);
        else env.types.put(name, t);
    }

    private void putVar( Symbol name, VarEntry v) {
        TableDealer tmp = env.types.get(name);
        if(!(tmp == null) && (tmp.level == env.vars.level))
            error("Redeclaration on var symbol: " + name);
        else env.vars.put(name, v);
    }

    private Type checkNameType(NAME t) {
        TableDealer now = env.types.get(t.name);
        if(now == null || t.level < now.level) return null;
        return (Type)now.value;
    }

    public Semantic() {
        this(new Env(), Type.VOID);
    }

    public Semantic(Env e, Type t) {
        env = e;
        returnType = t;
    }

    public void error(String message) {
        errors.add(new Error(message, true));
    }

    private void fatalError(String message) {
        error(message);
        printErrors();
        new Exception().printStackTrace();
    }

    public boolean hasError() {
        return errors.size() > 0;
    }

    public void printErrors() {
        for(Error e : errors) {
            System.err.println(e);
        }
    }

    private void flushErrorsTo(Semantic another) {
        for(Error e: errors) {
            another.errors.add(e);
        }
    }

    public void checkProg(Decls d) {
        loopCount = 0;
        checkDecls(d);
    }

    private void checkDecls(Decls d) {
        for(Decl now: d.list) {
            checkDecl(now);
        }
    }

    private void checkDecl(Decl d) {
        if (d instanceof  VarDecl) checkVarDecl((VarDecl)d);
        else if (d instanceof Func) checkFunc((Func)d);
        else fatalError("transDecl");
    }

    private Type checkTy(Ty t) {
        if (t instanceof NameTy) {
            return (Type) env.types.get(((NameTy)t).TySymbol).value;
        }
        else if (t instanceof StructTy) {

        }
    }
    private void checkVarDecl(VarDecl td) {
        Type t = checkTy(td.type);
        if(t == null) error("Type not found in vardecl");
        else {
            if (td.initdecltors != null) {
                for (InitDecltor id: td.initdecltors.list) {
                    DtorDealer now = checkDecltor(id.decltor);
                }
            }
        }
    }
}
