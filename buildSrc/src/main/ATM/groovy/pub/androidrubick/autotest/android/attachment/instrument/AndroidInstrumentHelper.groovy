package pub.androidrubick.autotest.android.attachment.instrument

import android.content.ComponentName
import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder;

/**
 * <p>
 * Created by Yin Yong on 2018/3/21.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidInstrumentHelper extends BaseAndroidAttachment {

    private final InstrumentInfo mInstrumentInfo
    AndroidInstrumentHelper(ATMContext context, InstrumentInfo instrumentInfo) {
        super(context)
        mInstrumentInfo = instrumentInfo
    }

    public ExecProcBuilder buildTest(String testClzPattern) {
        this.buildTest(InstrumentTestClz.parse(testClzPattern))
    }

    public ExecProcBuilder buildTest(Map map) {
        this.buildTest(InstrumentTestClz.fromMap(map))
    }

    public ExecProcBuilder buildTest(@NonNull InstrumentTestClz testClz) {
        androidSdk.adbShell.am.startInstrument(new AndroidTestCase(
                mInstrumentInfo.asComponent(),
                testClz
        ))
    }

    public final InstrumentInfo getInstrumentInfo() {
        return mInstrumentInfo
    }

    public final ComponentName getInstrumentComponent() {
        return mInstrumentInfo.asComponent()
    }

}
