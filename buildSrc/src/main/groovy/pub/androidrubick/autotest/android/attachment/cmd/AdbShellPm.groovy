package pub.androidrubick.autotest.android.attachment.cmd

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.InstallInfo
import pub.androidrubick.autotest.android.model.UninstallInfo
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder

@SuppressWarnings("GroovyUnusedDeclaration")
class AdbShellPm extends BaseAndroidAttachment {

    AdbShellPm(ATMContext context) {
        super(context)
    }

    public ExecProcBuilder install(InstallInfo installInfo) {
        return androidSdk.adbShell.pm("install ${installInfo.toCmdString()}")
    }

    public ExecProcBuilder uninstall(UninstallInfo uninstallInfo) {
        return androidSdk.adbShell.pm("uninstall ${uninstallInfo.toCmdString()}")
    }

}