package cn.edu.ustc.analyzer;

import java.util.Map;

import cn.edu.ustc.analyzer.rank.PageRankHelper;
import cn.edu.ustc.analyzer.tpl.AnalyseHelper;
import cn.edu.ustc.common.info.JobInfo1;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	long rank=PageRankHelper.getRank("百度");
        assertTrue( rank ==100000000 );
		JobInfo1 info1 = new JobInfo1();
		info1.setJob("http://jobs.zhaopin.com/000574012251556.htm?ssidkey=y&ss=201&ff=03");
		info1.setCompany("");
		Map<String, ?> result = AnalyseHelper.getResult(info1);
		assertTrue(result.get("cpscale").toString().indexOf("公司性质")==-1);
    }
}
