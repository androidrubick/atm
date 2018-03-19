package pub.androidrubick.autotest.android.attachment.cmd

import android.content.Intent
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.attachment.instrument.AndroidTestCase
import pub.androidrubick.autotest.android.support.cmd.IntentCmd
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder

@SuppressWarnings("GroovyUnusedDeclaration")
class AdbShellAm extends BaseAndroidAttachment {

    AdbShellAm(ATMContext context) {
        super(context)
    }

    public ExecProcBuilder forceStop(String pkg) {
        return androidSdk.cmd.adb.shell.am("force-stop $pkg")
    }

    public ExecProcBuilder broadcast(Intent intent) {
        IntentCmd intentCmd = new IntentCmd(intent)
        return androidSdk.cmd.adb.shell.am("broadcast ${intentCmd.toCmdString()}")
    }

    public ExecProcBuilder instrument(AndroidTestCase testCase) {

    }

}