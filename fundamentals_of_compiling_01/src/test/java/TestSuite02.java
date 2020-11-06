import com.k2kra.compiling.FA;
import com.k2kra.compiling.FA2NG;
import com.k2kra.compiling.NG;
import com.k2kra.compiling.NG2FA;
import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestSuite02 {

    private final String ng_filepath;
    private final String fa_filepath;

    public TestSuite02(Pair<String, String> pair) {
        this.ng_filepath = pair.getKey();
        this.fa_filepath = pair.getValue();
    }

    //测试确定有限自动机到右线性正规文法的转换
    @Test
    public void ng2fa() {
        FA fa = FA.loadFA(fa_filepath);
        NG actualNG = FA2NG.transform(fa);
        NG expectedNG = NG.loadNG(ng_filepath);
        assertEquals(expectedNG, actualNG);
        System.out.println(fa.getDesc());
    }

    //测试集
    @Parameterized.Parameters
    public static Collection testPlan1() {
        List<Pair<String, String>> testPlan = new ArrayList<>();
        testPlan.add(new Pair<>("src/testCases/TestSuite02/NGTestCase01.json", "src/testCases/TestSuite02/FATestCase01.json"));
        testPlan.add(new Pair<>("src/testCases/TestSuite02/NGTestCase02.json", "src/testCases/TestSuite02/FATestCase02.json"));
        testPlan.add(new Pair<>("src/testCases/TestSuite02/NGTestCase03.json", "src/testCases/TestSuite02/FATestCase03.json"));
        return testPlan;
    }
}
