package cn.edu.ustc.spider;

import cn.edu.ustc.spider.patch.ChinaHrFilter;
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
    	String re="C/C++";
        re=ChinaHrFilter.filter(re);
    	System.out.println(re);
    	re=ChinaHrFilter.filter("C#");
    	System.out.println(re);
    	re=ChinaHrFilter.filter(".NET");
    	System.out.println(re);
        assertTrue( true );
    }
}
