package pub.androidrubick.autotest.android.attachment.cmd

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult
import pub.androidrubick.autotest.core.util.CmdResultUtils

@SuppressWarnings("GroovyUnusedDeclaration")
class Adb extends BaseAndroidAttachment {

    public final AdbShell shell
    public final AdbUtil util
    Adb(ATMContext context) {
        super(context)
        shell = new AdbShell(context)
        util = new AdbUtil(context)
    }

    private ExecProcBuilder adb(String cmd) {
        return androidSdk.cmd.adb(cmd)
    }

    @NonNull
    public ExecResult version() {
        return adb('version').exec().checkNonEmptyText('adb version')
    }

    @NonNull
    public ExecProcBuilder shell(String command) {
        return adb("shell $command")
    }

    @NonNull
    public ExecResult pullFile(File remote, File local) {
        return adb("pull ${remote.absolutePath} ${local.absolutePath}").exec()
                .checkNonEmptyText('pullFile')
    }

    @NonNull
    public ExecResult pushFile(File local, File remote) {
        return adb("push ${local.absolutePath} ${remote.absolutePath}").exec()
                .checkNonEmptyText('pushFile')
    }

    @NonNull
    public ExecResult startServer() {
        return adb('start-server').exec().checkSuccess('startServer')
    }

    @NonNull
    public ExecResult killServer() {
        return adb('kill-server').exec().checkSuccess('killServer')
    }

    @NonNull
    public List<AdbDevice> devices() {
        def devicesResult = adb('devices').exec().checkSuccess('devices')
        List<String> lines = CmdResultUtils.nonEmptyLines(devicesResult.text)
        if (lines?.size() > 1) {
            def lastIndex = lines.size() - 1
            return lines[1..lastIndex].collect {
                def vals = CmdResultUtils.nonEmptyValuesOfLine(it)
                new AdbDevice(vals[0], vals[1])
            }
        }
        return []
    }

}