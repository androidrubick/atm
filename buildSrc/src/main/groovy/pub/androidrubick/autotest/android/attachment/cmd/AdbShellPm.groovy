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

    public ExecProcBuilder builder(String command) {
        return androidSdk.adbShell.builder("pm $command")
    }

    public ExecProcBuilder install(InstallInfo installInfo) {
        return builder("install ${installInfo.toCmdString()}")
    }

    public ExecProcBuilder uninstall(UninstallInfo uninstallInfo) {
        return builder("uninstall ${uninstallInfo.toCmdString()}")
    }

}