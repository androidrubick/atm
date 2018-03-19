package pub.androidrubick.autotest.android.attachment.cmd

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder

@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidSDKCmd extends BaseAndroidAttachment {

    public final Adb adb
    public AndroidSDKCmd(ATMContext context) {
        super(context)
        adb = new Adb(context)
    }

    private String getAndroidSDKPathPrefix(String subDir = '') {
        String dir = androidSdk.configuration.SDKDir
        if (dir) {
            def dirArr = [dir]
            if (subDir) {
                dirArr << File.separator << subDir
            }
            dirArr << File.separator
            return dirArr.join()
        }
        return ''
    }

    private ExecProcBuilder builder(@NonNull String command, String subDir = '') {
        return atm.cmd.builder([getAndroidSDKPathPrefix(subDir), command].join())
    }

    public ExecProcBuilder tools(@NonNull String command) {
        return builder(command, 'tools')
    }

    public ExecProcBuilder tools_bin(@NonNull String command) {
        return builder(command, 'tools/bin')
    }

    public ExecProcBuilder apkanalyzer(@NonNull String command) {
        return tools_bin("apkanalyzer $command")
    }

    public ExecProcBuilder platform_tools(@NonNull String command) {
        return builder(command, 'platform-tools')
    }

    private String getAdbCmd() {
        AdbDevice device = androidSdk.configuration.targetDevice
        return device ? "adb -s ${device.serialNumber}" : 'adb'
    }

    public ExecProcBuilder adb(@NonNull String command) {
        return platform_tools([adbCmd, command].join(' '))
    }

//    public final AndroidDeviceUtil getDevice() {
//        return this.android_sdk.device
//    }
//
//    public final AndroidStorageUtil getStorage() {
//        return this.android_sdk.storage
//    }

}