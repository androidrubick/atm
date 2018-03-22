package pub.androidrubick.autotest.android

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.android.attachment.cmd.Adb
import pub.androidrubick.autotest.android.attachment.cmd.AdbShell
import pub.androidrubick.autotest.android.attachment.cmd.AdbUtil
import pub.androidrubick.autotest.android.attachment.cmd.AndroidSDKCmd
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment

@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidSdk extends BaseAttachment {

    public static final String INJECT_NAME = "atm_android_sdk"

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
//
//    public final InstrumentArchiveUtil getInstrumentArchive() {
//        return InstrumentArchiveUtil.instance(context)
//    }

    private AndroidSdkConfiguration mAndroidSdkConfiguration
    public final AndroidSDKCmd cmd
    public final Adb adb
    public final AdbShell adbShell
    public final AdbUtil adbUtil
    public AndroidSdk(ATMContext context) {
        super(context)
        cmd = new AndroidSDKCmd(context)
        adb = cmd.adb
        adbShell = adb.shell
        adbUtil = adb.util
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