package pub.androidrubick.autotest.android

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.android.attachment.cmd.AndroidSDKCmd
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidSdk extends BaseAttachment {

    public static final String INJECT_NAME = "android_sdk"

    public static AndroidSdk attach(@NonNull Project myProject) {
        if (myProject.extensions.findByName(INJECT_NAME) == null) {
            myProject.extensions.create(INJECT_NAME, AndroidSdk, ATM.fromProject(myProject).context)
        }
        return fromProject(myProject)
    }

    /**
     * get the {@link AndroidSdk} implementation from target project
     * @param project target project
     * @return the {@link AndroidSdk} implementation
     * @since 1.0.0
     */
    public static AndroidSdk fromProject(@NonNull Project project) {
        return project."$INJECT_NAME"
    }

//    public final AndroidDeviceUtil device
//    public final AndroidStorageUtil storage
//    public final AndroidUIUtil ui
//    public final AndroidPerformActionUtil actionPerform
//    public final AndroidInstaller installer
//    public final AndroidLaunchUtil launcher
//    public final AndroidAppUtil app
//    public final AndroidInstrumentUtil instrument
//
//    AndroidSdk(ATMContext context) {
//        super(context)
//        device = new AndroidDeviceUtil(context)
//        storage = new AndroidStorageUtil(context)
//        ui = new AndroidUIUtil(context)
//        actionPerform = new AndroidPerformActionUtil(context)
//        installer = new AndroidInstaller(context)
//        launcher = new AndroidLaunchUtil(context)
//        app = new AndroidAppUtil(context)
//        instrument = new AndroidInstrumentUtil(context)
//
//        vsri.log(project.ext.properties)
//    }

    /**
     * clear all android sdk or cmd line configurations
     *
     * @since 1.0.0
     */
    public void clearConfigurations() {
        ANDROID_SDK_CMD_PREFIX = ''
        ANDROID_SDK_TOOLS_CMD_PREFIX = ''
        ANDROID_SDK_TOOLS_BIN_CMD_PREFIX = ''
        ANDROID_SDK_PLATFORM_TOOLS_CMD_PREFIX = ''
        ADB_COMMAND = 'adb'
        mTargetDevice = null
    }

    // ===========================================================================
    // 定义android_sdk 命令行调用的一些基础方法
    // vsri_cmd_builder
    // ===========================================================================

//    public final ExecProcBuilder platform_tools(String command) {
//        return vsri.cmd.builder("${ANDROID_SDK_PLATFORM_TOOLS_CMD_PREFIX}$command")
//    }
//
//    public final ExecProcBuilder adb(String command) {
//        return this.platform_tools("${ADB_COMMAND}${command}")
//    }
//
//    public final ExecProcBuilder adb_shell(String command) {
//        return this.adb("shell $command")
//    }
//
//    // `$ANDROID_HOME/tools/`路径下的命令、工具等
//    /**
//     * 会前置`$ANDROID_HOME/tools/`路径，这样可以执行路径下的命令；
//     *
//     * 比如：
//     * android
//     * emulator
//     * emulator-check
//     * monitor等
//     */
//    public final ExecProcBuilder tools(String command) {
//        return vsri.cmd.builder("${ANDROID_SDK_TOOLS_CMD_PREFIX}$command")
//    }
//
//    public final ExecProcBuilder android(String command) {
//        return this.tools("android $command")
//    }
//
//    // `$ANDROID_HOME/tools/bin`路径下的命令、工具等
//    /**
//     * 会前置`$ANDROID_HOME/tools/bin`路径，这样可以执行路径下的命令；
//     *
//     * 比如：
//     * apkanalyzer
//     * lint
//     * monkeyrunner
//     * screenshot2
//     * sdkmanager
//     * uiautomatorviewer等
//     */
//    public final ExecProcBuilder tools_bin(String command) {
//        return vsri.cmd.builder("${ANDROID_SDK_TOOLS_BIN_CMD_PREFIX}$command")
//    }
//
//    public final ExecProcBuilder apkanalyzer(String command) {
//        return this.tools_bin("apkanalyzer $command")
//    }
//
//    public final InstrumentArchiveUtil getInstrumentArchive() {
//        return InstrumentArchiveUtil.instance(context)
//    }

    private AndroidSdkConfiguration mAndroidSdkConfiguration
    public final AndroidSDKCmd cmd
    public AndroidSdk(ATMContext context) {
        super(context)
        cmd = new AndroidSDKCmd(context)
    }

    public AndroidSdkConfiguration getConfiguration() {
        if (null == mAndroidSdkConfiguration) {
            synchronized (this) {
                if (null == mAndroidSdkConfiguration) {
                    mAndroidSdkConfiguration = new AndroidSdkConfiguration(context)
                }
            }
        }
        return mAndroidSdkConfiguration
    }
}