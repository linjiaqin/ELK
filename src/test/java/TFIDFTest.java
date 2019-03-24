

import org.junit.Test;
import org.junit.Before;
import org.junit.After;


/**
* TFIDF Tester.
*
* @author nb
* @since <pre>03/24/2019</pre>
*/
/*
@Test：把一个方法标记为测试方法
@Before：每一个测试方法执行前自动调用一次
@After：每一个测试方法执行完自动调用一次
@BeforeClass：所有测试方法执行前执行一次，在测试类还没有实例化就已经被加载，所以用static修饰
@AfterClass：所有测试方法执行完执行一次，在测试类还没有实例化就已经被加载，所以用static修饰
@Ignore：暂不执行该测试方法
 */
public class TFIDFTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

        /**
    *
    * Method: tf(List<String> doc, String term)
    *
    */
    @Test
    public void testTf() throws Exception {
    //TODO: Test goes here...
    }

        /**
    *
    * Method: df(List<List<String>> docs, String term)
    *
    */
    @Test
    public void testDf() throws Exception {
    //TODO: Test goes here...
    }

        /**
    *
    * Method: idf(List<List<String>> docs, String term)
    *
    */
    @Test
    public void testIdf() throws Exception {
    //TODO: Test goes here...
    }

        /**
    *
    * Method: tfidf(List<String> doc, List<List<String>> docs, String term)
    *
    */
    @Test
    public void testTfidf() throws Exception {
    //TODO: Test goes here...
    }

        /**
    *
    * Method: main(String[] args)
    *
    */
    @Test
    public void testMain() throws Exception {
    //TODO: Test goes here...
    }

    
    }
