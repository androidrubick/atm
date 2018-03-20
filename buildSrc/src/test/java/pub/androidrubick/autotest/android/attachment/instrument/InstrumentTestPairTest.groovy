package pub.androidrubick.autotest.android.attachment.instrument

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/20.
 */
class InstrumentTestPairTest extends GroovyTestCase {
    void testToCmdString() {
        println new InstrumentTestPair('debug')
        println new InstrumentTestPair('debug').setValue(false)
        println new InstrumentTestPair('debug', false)
        println new InstrumentTestPair('debug', false).setValue(true)
    }
}
