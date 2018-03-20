package pub.androidrubick.autotest.android.attachment.instrument

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/20.
 */
class InstrumentTestClzTest extends GroovyTestCase {
    void testParse() {

        println InstrumentTestClz.parse(InstrumentTestClzTest.class.name)
        println InstrumentTestClz.parse(InstrumentTestClzTest.class.name + "#test")

    }
}
