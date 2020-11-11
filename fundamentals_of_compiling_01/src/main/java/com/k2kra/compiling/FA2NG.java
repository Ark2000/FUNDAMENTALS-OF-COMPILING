package com.k2kra.compiling;

import java.util.ArrayList;

public class FA2NG {
    public static NG transform(FA fa) {
        NG ng = new NG("","", new ArrayList<>());

        //开始符号S为DFA的唯一初态
        ng.setS(fa.getS().get(0));

        //若DFA的初态也是终态，那么S->ε
        if (fa.getF().contains(fa.getS().get(0))) {
            ng.getP().add(new NG.Production(fa.getS().get(0), "", ""));
        }

        //遍历所有的边
        for (int i = 0; i < fa.getEdges().size(); i++) {
            FA.Edge edge = fa.getEdges().get(i);

            //对于f(A, a) = B且B是终态，有A->a|aB
            //对于f(A, a) = B且B不是终态，有A -> aB
            ng.getP().add(new NG.Production(edge.getSrc(), edge.getMatch(), edge.getDst()));
            if (fa.getF().contains(edge.getDst())) {
                ng.getP().add(new NG.Production(edge.getSrc(), edge.getMatch(), ""));
            }

        }
        return ng;
    }
}
