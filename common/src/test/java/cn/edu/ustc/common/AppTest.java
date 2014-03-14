package cn.edu.ustc.common;

import java.io.UnsupportedEncodingException;
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
     * @throws UnsupportedEncodingException 
     */
    public void testApp() throws UnsupportedEncodingException
    {
    	//String html=new UrlFetcher().fetch("http://www.chinahr.com/so/%E8%BD%AF%E4%BB%B6/0-0-0-0-7-0-0-0-0-0-0-0-0-0/p0");
        assertTrue( true);
    }
}
