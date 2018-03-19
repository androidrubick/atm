package pub.androidrubick.autotest.android.attachment.cmd

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder

@SuppressWarnings("GroovyUnusedDeclaration")
class AdbShellPm extends BaseAndroidAttachment {

    AdbShellPm(ATMContext context) {
        super(context)
    }

    public ExecProcBuilder install(String pkg) {
        return androidSdk.cmd.adb.shell.pm("install $pkg")
    }

}