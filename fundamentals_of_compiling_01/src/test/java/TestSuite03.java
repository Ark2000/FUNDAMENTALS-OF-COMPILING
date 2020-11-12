import com.k2kra.compiling.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.util.Pair;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestSuite03 {

    private final String ng_filepath;
    private final String fa_filepath;

    public TestSuite03(Pair<String, String> pair) {
        this.ng_filepath = pair.getKey();
        this.fa_filepath = pair.getValue();
    }

    //测试右线性正规文法到非确定有限自动机的转换
    @Test
    public void ng2fa() {
        NG ng = NG.loadNG(ng_filepath);
        FA actualFA = NG2FA.transform(ng);
        FA expectedFA = FA.loadFA(fa_filepath);
        assertEquals(expectedFA, actualFA);
        System.out.println(ng.getDesc());
    }

    //测试集
    @Parameterized.Parameters
    public static Collection testPlan1() {
        List<Pair<String, String>> testPlan = new ArrayList<>();
        testPlan.add(new Pair<>("src/testCases/TestSuite03/NGTestCase01.json", "src/testCases/TestSuite03/FATestCase01.json"));
        testPlan.add(new Pair<>("src/testCases/TestSuite03/NGTestCase02.json", "src/testCases/TestSuite03/FATestCase02.json"));
        testPlan.add(new Pair<>("src/testCases/TestSuite03/NGTestCase03.json", "src/testCases/TestSuite03/FATestCase03.json"));
        testPlan.add(new Pair<>("src/testCases/TestSuite03/NGTestCase04.json", "src/testCases/TestSuite03/FATestCase04.json"));
        testPlan.add(new Pair<>("src/testCases/TestSuite03/NGTestCase05.json", "src/testCases/TestSuite03/FATestCase05.json"));
        return testPlan;
    }
}
