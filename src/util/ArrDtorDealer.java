package util;
import java.util.LinkedList;
import java.util.List;

import symbol.Symbol;
/**
 * Created by liuzhe on 10/19/2015.
 */
public final class ArrDtorDealer extends DtorDealer {
    public int arrLength;
    public List<Integer> parasaddr = new LinkedList<Integer>();

    public ArrDtorDealer(Symbol s, int c, int l) {
        ptrcount = c;
        name = s;
        arrLength = l;
    }
}
