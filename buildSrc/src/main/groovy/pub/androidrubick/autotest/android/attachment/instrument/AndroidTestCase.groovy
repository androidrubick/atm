package pub.androidrubick.autotest.android.attachment.instrument

import android.content.ComponentName

@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidTestCase {

    public final ComponentName instrumentInfo
    public final InstrumentTestClz testClz
    public boolean debug
    /**
     * --no-window-animation
     */
    public boolean notWindowAnim
    public AndroidTestCase(ComponentName instrumentInfo, InstrumentTestClz testClz) {
        this.instrumentInfo = instrumentInfo
        this.testClz = testClz
    }

    public AndroidTestCase setDebug(boolean debug) {
        this.debug = debug
        return this
    }

    public AndroidTestCase setNotWindowAnim(boolean val) {
        this.notWindowAnim = val
        return this
    }

    @Override
    public String toString() {
        return "AndroidTestCase{" +
                "instrumentInfo=" + instrumentInfo +
                ", testClz=" + testClz +
                ", debug=" + debug +
                ", notWindowAnim=" + notWindowAnim +
                '}';
    }
}