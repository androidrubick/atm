package pub.androidrubick.autotest.android.attachment.cmd

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult

import static pub.androidrubick.autotest.core.util.CmdResultUtils.nonEmptyLines
import static pub.androidrubick.autotest.core.util.CmdResultUtils.nonEmptyValuesOfLine

@SuppressWarnings("GroovyUnusedDeclaration")
class Adb extends BaseAndroidAttachment {

    public final AdbShell shell
    public final AdbUtil util
    Adb(ATMContext context) {
        super(context)
        shell = new AdbShell(context)
        util = new AdbUtil(context)
    }

    private String getAdbCmd() {
        AdbDevice device = androidSdk.configuration.targetDevice
        return device ? "adb -s ${device.serialNumber}" : 'adb'
    }

    public ExecProcBuilder builder(String cmd) {
        return androidSdk.cmd.platform_tools("$adbCmd $cmd")
    }

    @NonNull
    public ExecResult version() {
        return builder('version').exec().checkNonEmptyText('adb version')
    }

    @NonNull
    public ExecResult pullFile(File remote, File local) {
        return builder("pull ${remote.absolutePath} ${local.absolutePath}").exec()
                .checkNonEmptyText('pullFile')
    }

    @NonNull
    public ExecResult pushFile(File local, File remote) {
        return builder("push ${local.absolutePath} ${remote.absolutePath}").exec()
                .checkNonEmptyText('pushFile')
    }

    @NonNull
    public ExecResult startServer() {
        return builder('start-server').exec().checkSuccess('startServer')
    }

    @NonNull
    public ExecResult killServer() {
        return builder('kill-server').exec().checkSuccess('killServer')
    }

    @NonNull
    public List<AdbDevice> devices() {
        def devicesResult = builder('devices').exec().checkSuccess('devices')
        List<String> lines = nonEmptyLines(devicesResult.text)
        if (lines?.size() > 1) {
            def lastIndex = lines.size() - 1
            return lines[1..lastIndex].collect {
                def vals = nonEmptyValuesOfLine(it)
                new AdbDevice(vals[0], vals[1])
            }
        }
        return []
    }

}