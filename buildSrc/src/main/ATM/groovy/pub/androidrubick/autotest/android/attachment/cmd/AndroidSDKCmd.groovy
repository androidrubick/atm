package pub.androidrubick.autotest.android.attachment.cmd

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.AndroidSdkConfiguration
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder

/**
 * 在运行命令时，会使用到{@link AndroidSdkConfiguration}中的设置——比如，SDKDir、当前的device等
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidSDKCmd extends BaseAndroidAttachment {

    public final Adb adb
    public final ApkAnalyzer apkAnalyzer
    public AndroidSDKCmd(ATMContext context) {
        super(context)
        adb = new Adb(context)
        apkAnalyzer = new ApkAnalyzer(context)
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

    /**
     * 会前置`$ANDROID_HOME/tools/`路径，这样可以执行路径下的命令；
     *
     * 比如：
     * android
     * emulator
     * emulator-check
     * monitor等
     */
    public ExecProcBuilder tools(@NonNull String command) {
        return builder(command, 'tools')
    }

    // `$ANDROID_HOME/tools/bin`路径下的命令、工具等
    /**
     * 会前置`$ANDROID_HOME/tools/bin`路径，这样可以执行路径下的命令；
     *
     * 比如：
     * apkanalyzer
     * lint
     * monkeyrunner
     * screenshot2
     * sdkmanager
     * uiautomatorviewer等
     */
    public ExecProcBuilder tools_bin(@NonNull String command) {
        return builder(command, 'tools/bin')
    }

    public ExecProcBuilder platform_tools(@NonNull String command) {
        return builder(command, 'platform-tools')
    }

//    public final AndroidDeviceUtil getDevice() {
//        return this.android_sdk.device
//    }

}