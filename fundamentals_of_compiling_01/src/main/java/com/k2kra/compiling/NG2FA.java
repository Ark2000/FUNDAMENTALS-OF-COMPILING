package com.k2kra.compiling;
import java.util.ArrayList;

public class NG2FA {

    public static String terminalState = "X";

    public static FA transform(NG ng) {
        FA fa = new FA("", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        //添加一个新的终态符号
        fa.getF().add(terminalState);

        //NG的开始符号就是FA的初态
        fa.getS().add(ng.getS());

        //遍历NG的产生式
        for (int i = 0; i < ng.getP().size(); i++) {
            NG.Production p = ng.getP().get(i);

            //非终结符不能和新加的终态重复
            assert(!p.getLeft().equals(terminalState));

            //A -> ε 或 A -> a
            if (p.getRight2().isEmpty()) {
                fa.getEdges().add(new FA.Edge(p.getLeft(), terminalState, p.getRight1()));
            } else {
            //A -> B 或 A -> aB
                fa.getEdges().add(new FA.Edge(p.getLeft(), p.getRight2(), p.getRight1()));
            }
        }

        return fa;
    }
}
