package com.k2kra.compiling;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;

//非确定有限自动机
public class FA {
    @JSONField(name = "desc")
    private String desc;
    @JSONField(name = "s")
    private List<String> s;     //初态集合
    @JSONField(name = "edges")
    private List<Edge> edges;   //映射关系集合
    @JSONField(name = "f")
    private List<String> f;     //终态集合

    public FA(String desc, List<String> s, List<Edge> edges, List<String> f) {
        this.desc = desc;
        this.s = s;
        this.edges = edges;
        this.f = f;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getS() {
        return s;
    }

    public void setS(List<String> s) {
        this.s = s;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<String> getF() {
        return f;
    }

    public void setF(List<String> f) {
        this.f = f;
    }

    public static FA loadFA(String path) {
        try {
            File file = new File(path);
            String str = FileUtils.readFileToString(file, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(str);
            String desc = jsonObject.getString("desc");
            List<String> s = jsonObject.getJSONArray("s").toJavaList(String.class);
            List<String> f = jsonObject.getJSONArray("f").toJavaList(String.class);
            List<Edge> edges = jsonObject.getJSONArray("edges").toJavaList(Edge.class);
            return new FA(desc, s, edges, f);
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
        return "FA{" +
                "desc='" + desc + '\'' +
                ", s='" + s + '\'' +
                ", edges=" + edges +
                ", f=" + f +
                '}';
    }

    public static class Edge {
        @JSONField(name = "src")
        private String src;         //源状态
        @JSONField(name = "dst")
        private String dst;         //目标状态
        @JSONField(name = "match")
        private String match;       //匹配字符
        public String getSrc() {
            return src;
        }
        public void setSrc(String src) {
            this.src = src;
        }
        public String getDst() {
            return dst;
        }
        public void setDst(String dst) {
            this.dst = dst;
        }
        public String getMatch() {
            return match;
        }
        public void setMatch(String match) {
            this.match = match;
        }
        public Edge(String src, String dst, String match) {
            this.src = src;
            this.dst = dst;
            this.match = match;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src='" + src + '\'' +
                    ", dst='" + dst + '\'' +
                    ", match='" + match + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(src, edge.src) &&
                    Objects.equals(dst, edge.dst) &&
                    Objects.equals(match, edge.match);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FA fa = (FA) o;
        return  Objects.equals(s, fa.s) &&
                Objects.equals(edges, fa.edges) &&
                Objects.equals(f, fa.f);
    }
}
