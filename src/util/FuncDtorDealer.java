package util;
import symbol.Symbol;
import type.RECORD;
/**
 * Created by liuzhe on 10/19/2015.
 */
public class FuncDtorDealer extends DtorDealer{
    public RECORD agms;
    public boolean extend;
    public FuncDtorDealer(Symbol s, int c, RECORD a, boolean flag) {
        name = s;
        ptrcount = c;
        agms = a;
        extend = flag;
    }
}
