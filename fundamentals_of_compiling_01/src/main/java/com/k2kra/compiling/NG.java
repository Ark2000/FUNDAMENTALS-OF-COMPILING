package com.k2kra.compiling;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;

//正规文法
public class NG {
    @JSONField(name = "desc")
    private String desc;
    @JSONField(name = "s")
    private String s;               //开始符号
    @JSONField(name = "p")
    private List<Production> p;     //产生式集合

    public NG(String desc, String s, List<Production> p) {
        this.desc = desc;
        this.s = s;
        this.p = p;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<Production> getP() {
        return p;
    }

    public void setP(List<Production> p) {
        this.p = p;
    }

    public static NG loadNG(String path) {
        try {
            File f = new File(path);
            String str = FileUtils.readFileToString(f, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(str);
            String s = jsonObject.getString("s");
            String desc = jsonObject.getString("desc");
            List<Production> p = jsonObject.getJSONArray("p").toJavaList(Production.class);
            return new NG(desc, s, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toJSON() {
        return JSON.toJSONString(this, true);
    }

    @Override
    public String toString() {
        return "NG{" +
                "desc='" + desc + '\'' +
                ", s='" + s + '\'' +
                ", p=" + p +
                '}';
    }

    public static class Production {
        @JSONField(name = "left")
        private String left;            //非终结符
        @JSONField(name = "right1")
        private String right1;          //终结符 或 非终结符(取决于是左线性正规文法还是右线性正规文法)
        @JSONField(name = "right2")
        private String right2;          //非终结符 或 终结符(取决于是左线性正规文法还是右线性正规文法)

        public Production(String left, String right1, String right2) {
            this.left = left;
            this.right1 = right1;
            this.right2 = right2;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public String getRight1() {
            return right1;
        }

        public void setRight1(String right1) {
            this.right1 = right1;
        }

        public String getRight2() {
            return right2;
        }

        public void setRight2(String right2) {
            this.right2 = right2;
        }

        @Override
        public String toString() {
            return "NG.Production{" +
                    "left='" + left + '\'' +
                    ", right1='" + right1 + '\'' +
                    ", right2='" + right2 + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Production that = (Production) o;
            return Objects.equals(left, that.left) &&
                    Objects.equals(right1, that.right1) &&
                    Objects.equals(right2, that.right2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NG ng = (NG) o;
        return Objects.equals(s, ng.s) && Objects.equals(p, ng.p);
    }
}
