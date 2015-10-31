//package semantic;
//import java.sql.Struct;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
//
//import absyn.*;
//import addr.Label;
//import env.*;
//import symbol.Symbol;
////import translate.Translate;
//import symbol.Table;
//import type.*;
//import util.*;
//import util.Error;
//
//import javax.crypto.spec.OAEPParameterSpec;
//
///**
// * Created by liuzhe on 8/19/2015.
// */
//public class Semantic {
//    private Env env = null;
//    private List<Error> errors = new LinkedList<Error>();
//    static  enum RecordMark { STRUCT, UNION }
//    public Type returnType;
//    private int loopCount = 0;
//
//    private static Symbol symbol( String s ) {
//        return Symbol.getSymbol(s);
//    }
//
//    private void putType( Symbol name, Type t ) {
//        TableDealer tmp = env.types.get(name);
//        if(!(tmp == null) && (tmp.level == env.types.level))
//            error("Redeclaration on type symbol: " + name);
//        else env.types.put(name, t);
//    }
//
//    private void putVar( Symbol name, VarEntry v) {
//        TableDealer tmp = env.types.get(name);
//        if(!(tmp == null) && (tmp.level == env.vars.level))
//            error("Redeclaration on var symbol: " + name);
//        else env.vars.put(name, v);
//    }
//
//    private Type checkNameType(NAME t) {
//        TableDealer now = env.types.get(t.name);
//        if(now == null || t.level < now.level) return null;
//        return (Type)now.value;
//    }
//
//    public Semantic() {
//        this(new Env(), Type.VOID);
//    }
//
//    public Semantic(Env e, Type t) {
//        env = e;
//        returnType = t;
//    }
//
//    public void error(String message) {
//        errors.add(new Error(message, true));
//    }
//
//    private void fatalError(String message) {
//        error(message);
//        printErrors();
//        new Exception().printStackTrace();
//    }
//
//    public boolean hasError() {
//        return errors.size() > 0;
//    }
//
//    public void printErrors() {
//        for(Error e : errors) {
//            System.err.println(e);
//        }
//    }
//
//    private void flushErrorsTo(Semantic another) {
//        for(Error e: errors) {
//            another.errors.add(e);
//        }
//    }
//
//    public void checkProg(Decls d) {
//        loopCount = 0;
//        checkDecls(d);
//    }
//
//    private void checkDecls(Decls d) {
//        for(Decl now: d.list) {
//            checkDecl(now);
//        }
//    }
//
//    private void checkDecl(Decl d) {
//        if (d instanceof  VarDecl) checkVarDecl((VarDecl)d);
//        else if (d instanceof Func) checkFunc((Func)d);
//        else fatalError("transDecl");
//    }
//
//    private Type checkTy(Ty t) {
//        if (t instanceof NameTy) {
//            return (Type) env.types.get(((NameTy)t).TySymbol).value;
//        } else if (t instanceof StructTy) {
//            if(((StructTy)t).structdecls == null) {
//                TableDealer last = env.types.get(t.TySymbol);
//                if (last == null) {
//                    TableDealer ntype = env.types.get(symbol("name " + t.TySymbol.toString()));
//                    if (ntype == null) {
//                        NAME tmp = new NAME(t.TySymbol, env.types.level);
//                        putType(symbol("name " + t.TySymbol.toString()), tmp);
//                        return tmp;
//                    } else return (Type)ntype.value;
//                } else return (Type)last.value;
//            } else {
//                STRUCT tmp = (STRUCT)buildStructDecls(((StructTy)t).structdecls,RecordMark.STRUCT);
//                putType(t.TySymbol, tmp);
//                return tmp;
//            }
//
//        } else if( t instanceof UnionTy) {
//            if(((UnionTy)t).structdecls == null) {
//                TableDealer last = env.types.get(t.TySymbol);
//                if(last == null) {
//                    TableDealer ntype = env.types.get(symbol("name " + t.TySymbol.toString()));
//                    if(ntype == null) {
//                        NAME tmp = new NAME(t.TySymbol, env.types.level);
//                        putType(symbol("name " + t.TySymbol.toString()), tmp);
//                        return tmp;
//                    } else return (Type)ntype.value;
//                } else return (Type)last.value;
//            } else {
//                UNION tmp = (UNION)buildStructDecls(((UnionTy)t).structdecls, RecordMark.UNION);
//                putType(t.TySymbol, tmp);
//                return tmp;
//            }
//        } else fatalError("transTypeSpec");
//        return null;
//    }
//
//    private DtorDealer checkDecltor(Decltor d) {
//        // now is a DtorDealer, so it can point to a SmpDtorDealer or ArrDtorDealer or FuncDecltor
//        DtorDealer now = checkPlDecltor(d.pldecltor);
//        if(d instanceof ArrDecltor) {
//            checkArrParas(((ArrDecltor)d).arrparas);
//            int length = ((ArrDecltor)d).arrparas.list.size();
//            now = new ArrDtorDealer(now.name, now.ptrcount, length);
//            for (int i = 0; i < length; ++i) {
//                int cap = ((Num)((ArrDecltor)d).arrparas.list.get(i)).value;
//                ((ArrDtorDealer)now).parasaddr.add(cap);
//            }
//        } else if(d instanceof FuncDecltor) {
//            RECORD agm = checkPara(((FuncDecltor)d).para);
//            now = new FuncDtorDealer(now.name, now.ptrcount, agm, ((FuncDecltor)d).extend);
//        }
//        return now;
//    }
//
//    private SmpDtorDealer checkPlDecltor(PlDecltor pdtor) {
//        PlDecltor tmp = pdtor;
//        int count = 0;
//        while(tmp instanceof PtrPlDecltor) {
//            ++count;
//            tmp = ((PtrPlDecltor)tmp).pldecltor;
//        }
//        return new SmpDtorDealer(((SmpPlDecltor)tmp).symbol, count);
//    }
//
//    private void checkArrParas(ArrParas arrparas) {
//        for(int i = 0; i < arrparas.list.size(); i++) {
//            Integer value = checkArrExpr(arrparas.list.get(i));
//            if(value == null)
//                error("Error in checkArrExpr");
//            arrparas.list.set(i, new Num(value));
//        }
//    }
//
//    private Integer checkArrExpr(Expr e) {
//        if(e instanceof Num) {
//            e.type = Type.INT;
//            return ((Num) e).value;
//        } else if (e instanceof CharConst) {
//            e.type = Type.CHAR;
//            return (int)((CharConst) e).value;
//        } else if (e instanceof CastExpr) {
//            Type t = checkTypeName(((CastExpr) e).tn);
//            if( t == null ) {
//                error("Type not found in CastExpr");
//                return null;
//            } else if (!(Type.INT.eq(t) || Type.CHAR.eq(t))) {
//                error("Type must be INT or CHAR in CastExpr in ArrDeclar");
//                return null;
//            }
//            e.type = t;
//            return checkArrExpr(((CastExpr) e).expr);
//        } else if (e instanceof AccExpr) {
//            checkAccExpr((AccExpr) e);
//            return checkArrExpr(((AccExpr) e).expr);
//        } else if (e instanceof Exprs) {
//            int i;
//            for(i = 0; i < ((Exprs) e).list.size(); i++ ) {
//                checkExpr(((Exprs) e).list.get(i));
//            }
//            e.type = Type.INT;
//            return checkArrExpr(((Exprs) e).list.get(i));
//        } else if (e instanceof Op) {
//            if(((Op) e).opType == Op.OpType.PLUS) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) + checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.MINUS) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) - checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.TIMES) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) * checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.DIVIDE) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) / checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.MOD) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) % checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.AND) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) & checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.OR) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) | checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.XOR) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) ^ checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.SHL) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) << checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.SHR) {
//                e.type = Type.INT;
//                return checkArrExpr(((Op) e).left) >> checkArrExpr(((Op) e).right);
//            } else if (((Op) e).opType == Op.OpType.PARAAND) {
//                e.type = Type.INT;
//                return (checkArrExpr(((Op) e).left) !=0 && checkArrExpr(((Op) e).right) != 0)? 1 : 0;
//            } else if (((Op) e).opType == Op.OpType.PARAOR) {
//                e.type = Type.INT;
//                return (checkArrExpr(((Op) e).left) != 0 || checkArrExpr(((Op) e).right) != 0)? 1 : 0;
//            } else if (((Op) e).opType == Op.OpType.LT) {
//                e.type = Type.INT;
//                return (checkArrExpr(((Op) e).left) < checkArrExpr(((Op) e).right))? 1 : 0;
//            } else if (((Op) e).opType == Op.OpType.GT) {
//                e.type = Type.INT;
//                return (checkArrExpr(((Op) e).left) > checkArrExpr(((Op) e).right))? 1 : 0;
//            } else if (((Op) e).opType == Op.OpType.LE) {
//                e.type = Type.INT;
//                return (checkArrExpr(((Op) e).left) <= checkArrExpr(((Op) e).right))? 1 : 0;
//            } else if (((Op) e).opType == Op.OpType.GE) {
//                e.type = Type.INT;
//                return (checkArrExpr(((Op) e).left) >= checkArrExpr(((Op) e).right))? 1 : 0;
//            } else if (((Op) e).opType == Op.OpType.EQ) {
//                e.type = Type.INT;
//                return (checkArrExpr(((Op) e).left) == checkArrExpr(((Op) e).right))? 1 : 0;
//            } else if (((Op) e).opType == Op.OpType.NE) {
//                e.type = Type.INT;
//                return (checkArrExpr(((Op) e).left) != checkArrExpr(((Op) e).right))? 1 : 0;
//            } else error("Error in checkArrExpr");
//        } else if (e instanceof TypeSizeExpr) {
//            Type t = checkTypeName(((TypeSizeExpr) e).ty);
//            if (t == null)
//                error("Type not found in TypeSizeExpr");
//            e.type = Type.INT;
//            return t.size();
//
//        } else if (e instanceof UnaryExpr) {
//            if (((UnaryExpr) e).utype == UnaryExpr.UnaryType.SIZEOF) {
//                Type t = checkExpr(((UnaryExpr) e).expr);
//                e.type = Type.INT;
//                return t.size();
//            } else if (((UnaryExpr) e).utype == UnaryExpr.UnaryType.MINUS) {
//                e.type = Type.INT;
//                return -checkArrExpr(((UnaryExpr) e).expr);
//            } else if (((UnaryExpr) e).utype == UnaryExpr.UnaryType.TIDLE) {
//                e.type = Type.INT;
//                return ~checkArrExpr(((UnaryExpr) e).expr);
//            } else if (((UnaryExpr) e).utype == UnaryExpr.UnaryType.PLUS) {
//                e.type = Type.INT;
//                return +checkArrExpr(((UnaryExpr) e).expr);
//            } else if (((UnaryExpr) e).utype == UnaryExpr.UnaryType.NOT) {
//                e.type = Type.INT;
//                return (checkArrExpr(((UnaryExpr) e).expr) == 0? 1:0);
//            } else error("Error in checkArrExpr");
//        }
//        else error("Error in checkArrExpr");
//        return null;
//    }
//
//    private Type checkTypeName(TypeName ty) {
//        int ptrcount = 0;
//        while (ty instanceof PtrTypeName) {
//            ty = ((PtrTypeName) ty).typename;
//            ptrcount++;
//        }
//        Type t = checkTy(((SmpTypeName) ty).type);
//        if (t == null) return null;
//        // dont get it
//        else if ( t instanceof NAME && ptrcount == 0 ) return null;
//        return fetchPtrType(t, ptrcount);
//    }
//
//    // code for expression checking
//    private Type checkExpr(Expr e) {
//        if (e instanceof AccExpr) return checkAccExpr((AccExpr) e);
//        else if (e instanceof AdrsExpr) return checkAdrsExpr((AdrsExpr) e);
//        else if (e instanceof CallExpr) return checkCallExpr((AccExpr) e);
//        else if (e instanceof CastExpr) return checkCastExpr((AccExpr) e);
//        else if (e instanceof CharConst) return checkCharConst((AccExpr) e);
//        else if (e instanceof Exprs) return checkExprs((AccExpr) e);
//        else if (e instanceof Num) return checkAccNum((AccExpr) e);
//        else if (e instanceof Op) return checkAccOp((AccExpr) e);
//        else if (e instanceof PostfixExpr) return checkPostfixExpr((AccExpr) e);
//        else if (e instanceof StringConst) return checkStringConst((AccExpr) e);
//        else if (e instanceof TypeSizeExpr) return checkTypeSizeExpr((AccExpr) e);
//        else if (e instanceof UnaryExpr) return checkUnaryExpr((AccExpr) e);
//        else if (e instanceof Var) return checkVar((AccExpr) e);
//        else {
//            fatalError("translateExpr");
//            return null;
//        }
//    }
//
//    private Type checkAccExpr(AccExpr e) {
//        if (!checkLeftOprand(e.expr)) {
//            error("accumulator must be used on a leftoprand");
//            //why void but not null?
//            return Type.VOID;
//        }
//        Type t = checkExpr(e.expr);
//        if (!checkActualInt(t)) {
//            error("Type error in accexpr: must in char pointer");
//            return Type.VOID;
//        }
//        return e.type = t;
//    }
//
//    private boolean checkLeftOprand(Expr e) {
//        if (e instanceof Exprs) {
//            if (((Exprs) e).list.size() == 1 ) e = ((Exprs) e).list.getFirst();
//            else return false;
//        }
//
//        if (e instanceof Var || e instanceof AdrsExpr || e instanceof PostfixExpr ||
//                (e instanceof UnaryExpr && ((UnaryExpr) e).utype == UnaryExpr.UnaryType.TIMES)) {
//            Type t = checkExpr(e);
//            if (t instanceof ARRAY) return false;
//            else return true;
//        }
//        return false;
//    }
//    private Type fetchPtrType(Type t, int ptrcount) {
//        while( ptrcount != 0 ) {
//            t = new POINTER(t);
//        }
//        return t;
//    }
//    private void checkVarDecl(VarDecl td) {
//        Type t = checkTy(td.type);
//        if(t == null) error("Type not found in vardecl");
//        else {
//            if (td.initdecltors != null) {
//                for (InitDecltor id: td.initdecltors.list) {
//                    DtorDealer now = checkDecltor(id.decltor);
//                    Symbol name = null;
//                    Type type = null;
//                    if (now instanceof SmpDtorDealer) {
//                        name = now.name;
//                        type = fetchPtrType(t, now.ptrcount);
//                    } else if (now instanceof ArrDtorDealer) {
//                        name = now.name;
//                        Type tmp = fetchPtrType(t, now.ptrcount);
//                        type = fetchArrType(tmp, ((ArrDtorDealer)now).arrLength, ((ArrDtorDealer)now).parasaddr);
//                    } else if (now instanceof FuncDtorDealer) {
//                        name = now.name;
//                        Type tmp = fetchPtrType(t, now.ptrcount);
//                        type = new FUNCTION(((FuncDtorDealer) now).agms, tmp, ((FuncDtorDealer)now).extend);
//                    }
//                    if (type instanceof NAME) {
//                        error("Name type definition imcomplete");
//                        return;
//                    }
//                    VarEntry var = new VarEntry(type);
//                    putVar(name, var);
//                    checkIniter(id.initer, type);
//                }
//            }
//        }
//    }
//
//    private void checkIniter(Initer initer, Type t) {
//        if(initer != null) {
//            if( t instanceof FUNCTION) error("Cannot initialize function like this");
//            else if (initer instanceof PlIniter) {
//                Type now = checkExpr(((PlIniter)initer).aexpr);
//            }
//            else if (initer instanceof CpdIniter) {
//                for (Initer i: ((CpdIniter)initer).initers.list) checkIniter(i, t);
//            }
//        }
//    }
//
//    private void checkFunc(Func f) {
//        Type t = checkTy(f.typespec);
//        SmpDtorDealer now = checkPlDecltor(f.pldector);
//        RECORD agm = checkPara(f.para);
//        Type rt = fetchPtrType(t, now.ptrcount);
//        if (rt instanceof NAME) {
//            error("Function return type definition imcomplete");
//            return;
//        }
//        Label l = null;
//        if(now.name == symbol("name")) {
// //           l = Translate.runtime;
//        }
//        else l = Label.forFunction(now.name);
//        Type type = new FUNCTION(agm, rt, f.extend, l);
//        putVar(now.name, new VarEntry(type));
//        checkCpdStmt(f.body, agm, rt);
//    }
//}
