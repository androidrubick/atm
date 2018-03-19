package pub.androidrubick.autotest.android.attachment.instrument

@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidTestCase {

    public final InstrumentInfo instrumentInfo
    public final InstrumentTestClz testClz
    public boolean debug
    /**
     * --no-window-animation
     */
    public boolean notWindowAnim
    public AndroidTestCase(InstrumentInfo instrumentInfo, InstrumentTestClz testClz) {
        this.instrumentInfo = instrumentInfo
        this.testClz = testClz
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