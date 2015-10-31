package util;

import symbol.Symbol;
/**
 * Created by liuzhe on 10/19/2015.
 */
public class SmpDtorDealer extends DtorDealer {
    public SmpDtorDealer(Symbol s, int c) {
        ptrcount = c;
        name = s;
    }
}
