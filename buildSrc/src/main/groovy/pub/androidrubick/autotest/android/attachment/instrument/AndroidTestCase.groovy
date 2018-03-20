package pub.androidrubick.autotest.android.attachment.instrument

import android.content.ComponentName
import pub.androidrubick.autotest.android.model.Cmdable

@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidTestCase implements Cmdable {

    public final Map<String, InstrumentTestable> testParams = [:];
    {
        testParams.debug = new InstrumentTestPair('debug', false)
    }

    public final ComponentName instrumentInfo
    /**
     * --no-window-animation
     */
    public boolean notWindowAnim
    public AndroidTestCase(ComponentName instrumentInfo, InstrumentTestClz testClz) {
        this.instrumentInfo = instrumentInfo
        testParams['class'] = new InstrumentTestPair('class', testClz)
    }

    public AndroidTestCase setDebug(boolean debug) {
        testParams.debug.setValue(debug)
        return this
    }

    public AndroidTestCase setNotWindowAnim(boolean val) {
        this.notWindowAnim = val
        return this
    }

    @Override
    public String toString() {
        return "AndroidTestCase{" +
                "testParams=" + testParams +
                ", notWindowAnim=" + notWindowAnim +
                '}'
    }

    @Override
    String toCmdString() {
        def tokens = []
        if (notWindowAnim) {
            tokens << '--no-window-animation'
        }

        testParams.values().each { InstrumentTestable param ->
            tokens << param.toCmdString()
        }

        tokens << instrumentInfo.flattenToString()
        return tokens.join(' ')
    }
}