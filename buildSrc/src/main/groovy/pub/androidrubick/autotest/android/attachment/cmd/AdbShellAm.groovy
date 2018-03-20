package pub.androidrubick.autotest.android.attachment.cmd

import android.content.Intent
import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.attachment.instrument.AndroidTestCase
import pub.androidrubick.autotest.android.support.cmd.IntentCmd
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult

@SuppressWarnings("GroovyUnusedDeclaration")
class AdbShellAm extends BaseAndroidAttachment {

    AdbShellAm(ATMContext context) {
        super(context)
    }

    public ExecProcBuilder builder(String command) {
        return androidSdk.adbShell.builder("am $command")
    }

    public ExecResult forceStop(@NonNull String pkg) {
        return builder("force-stop $pkg").exec().checkSuccess('forceStop')
    }

    public ExecResult startActivity(@NonNull Intent intent) {
        return builder("start ${IntentCmd.toCmdString(intent)}").exec().checkSuccess('startActivity')
    }

    public ExecResult startService(@NonNull Intent intent) {
        return builder("startservice ${IntentCmd.toCmdString(intent)}").exec().checkSuccess('startService')
    }

    public ExecResult sendBroadcast(@NonNull Intent intent) {
        return builder("broadcast ${IntentCmd.toCmdString(intent)}").exec()
                .checkSuccess('sendBroadcast')
    }

    public ExecProcBuilder startInstrument(@NonNull AndroidTestCase testCase) {
        return builder("instrument -w -r -e debug ${testCase.debug} " +
                "${testCase.notWindowAnim ? '--no-window-animation' : ''} " +
                "-e class ${testCase.testClz.fullName} " +
                "${testCase.instrumentInfo.flattenToString()}")
    }

}