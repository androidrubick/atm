package pub.androidrubick.autotest.android.attachment.instrument

import android.content.ComponentName

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/20.
 */
class AndroidTestCaseTest extends GroovyTestCase {
    void testToCmdString() {
        ComponentName instrumentInfo = new ComponentName('my.test.pkg', 'my.test.class')
        InstrumentTestClz testClz = InstrumentTestClz.parse('my.class#method')
        AndroidTestCase testCase = new AndroidTestCase(instrumentInfo, testClz)
        testCase.setDebug(false)
        testCase.setNotWindowAnim(true)

        println testCase
        println testCase.toCmdString()
    }
}
