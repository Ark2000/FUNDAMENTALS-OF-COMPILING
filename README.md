# 正规文法 <-> FA 程序设计实现

## 数据定义
### 正规文法NormalGrammar

G = (V<sub>T</sub>, V<sub>N</sub>, S, P), 

V<sub>T</sub>是一组终结符号；

V<sub>N</sub>是一组非终结符号；

S是一个开始符号；

P是一组产生式；

G的任何产生式为`A→αB`或`A→α`, 其中α∈V<sub>t</sub><sup>*</sup>，A, B ∈ V<sub>N</sub>。

### 有限自动机FiniteAutomation

一个NFA表示为M = (S, Σ, δ, S<sub>0</sub>, F)

其中，S是状态集；

Σ是字母表集；

δ是从S×Σ<sup>*</sup>到S的子集映射；

S<sub>0</sub> 是非空初态集；

F是终态集；

## 算法描述

### 右线性正规文法 → 非确定有限自动机

1. 将Vn中的每一个非终结符作为M中的一个状态，并增加一个终止状态D，D不属于Vn
2. 对于 A -> a，有f(A, a) = D
3. 对于 A -> aB，有f(A, a) = B
4. 对于 A -> ε;, 有f(A, ε) = D

### 确定有限自动机 -> 右线性正规文法

1. 开始符号S为DFA的唯一初态
2. 对于f(A, a) = B且B不是终态，有A -> aB
3. 对于f(A, a) = B且B是终态，有A->aB和B->ε或A->a|aB
4. 若DFA的初态也是终态，那么S->ε

## 项目结构

### FA.java （定义NFA）

```java
    private String desc;        //描述
    private List<String> s;     //初态集合
    private List<Edge> edges;   //映射关系集合
    private List<String> f;     //终态集合
```

```java
    //从json文件加载java对象
    public static FA loadFA(String path);
    //将java对象转化为json文件
    public String toJSON();
```

#### FA.Edge

```java
        private String src;         //源状态
        private String dst;         //目标状态
        private String match;       //匹配字符
```

### NG.java （定义正规文法Normal Grammar）

```java
    private String desc;
    private String s;               //开始符号
    private List<Production> p;     //产生式集合
```

```java
    //从json文件加载java对象
    public static NG loadNG(String path);
    //将java对象转化为json文件
    public String toJSON();
```

#### NG.Production

```java
    private String left;            //非终结符
    private String right1;          //终结符 或 非终结符(取决于是左线性正规文法还是右线性正规文法)
    private String right2;          //非终结符 或 终结符(取决于是左线性正规文法还是右线性正规文法)
```

### FA2NG.java

```java
//确定有限自动机到右线性正规文法的转换
public static NG transform(FA ng);
```

### NG2FA.java

```java
//右线性正规文法到非确定有限自动机的转换
public static FA transform(NG ng);
```

### TestSuite01.java

测试右线性正规文法到非确定有限自动机的转换。对应的测试用例路径为`testCases/TestSuite01/`。

### TestSuite02.java

测试确定有限自动机到右线性正规文法的转换。对应的测试用例路径为`testCases/TestSuite02/`。

### 测试用例格式

下面的json文件描述了一个NFA，其中的键和FA.java一一对应，可以相互转换。
```js
{
  "desc": "ab*",
  "s": ["S"],
  "f": ["X"],
  "edges": [
    {
      "src": "S",
      "match": "a",
      "dst": "B"
    },
    {
      "src": "B",
      "match": "b",
      "dst": "B"
    },    {
      "src": "B",
      "match": "",
      "dst": "X"
    }
  ]
}
```
下面的json文件描述了一个正规文法，其中的键和NG.java一一对应，可以相互转换。
```js
{
  "desc":"ab*",
  "s": "S",
  "p":[
    {
      "left": "S",
      "right1": "a",
      "right2": "B"
    },
    {
      "left": "B",
      "right1": "b",
      "right2": "B"
    },
    {
      "left": "B",
      "right1": "",
      "right2": ""
    }
  ],
}
```