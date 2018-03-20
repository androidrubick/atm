package pub.androidrubick.autotest.android.attachment.cmd

import android.util.Log
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

    /**
     * 检查指定的包名的应用是否已经安装
     *
     * @param pkg 包名
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public boolean checkPkgInstalled(String pkg) {
        try {
            ensurePkgInstalled(pkg)
            return true
        } catch (Throwable e) {
            atm.logE("checkPkgInstalled failed")
            atm.logE(Log.getStackTraceString(e))
            return false
        }
    }

    /**
     * 保证指定的包名的应用已经安装
     *
     * @param pkg 包名
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public void ensurePkgInstalled(String pkg) {
        def filterPkgResult = builder("list packages $pkg").exec()
                .checkNonEmptyText("ensurePkgInstalled <$pkg> not installed")
        atm.preds.isTrue(filterPkgResult.text.indexOf(pkg) >= 0, "ensurePkgInstalled <$pkg> not installed")
    }

    public ExecProcBuilder uninstall(UninstallInfo uninstallInfo) {
        return builder("uninstall ${uninstallInfo.toCmdString()}")
    }

}