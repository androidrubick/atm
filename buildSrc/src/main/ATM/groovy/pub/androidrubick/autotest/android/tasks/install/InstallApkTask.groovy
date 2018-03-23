package pub.androidrubick.autotest.android.tasks.install

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.attachment.installer.AndroidInstaller
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.android.tasks.AndroidMultiDevicesExecutor
import pub.androidrubick.autotest.android.tasks.app.BaseAndroidCollectDependentTask
import pub.androidrubick.autotest.core.property.ATMGradleProperties
import pub.androidrubick.autotest.core.tasks.TaskGroups

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class InstallApkTask extends BaseAndroidCollectDependentTask {

    public InstallApkTask() {
        group = TaskGroups.GROUP_INSTALL
    }

    @TaskAction
    public void install() {
        if (atm.prop.has(ATMGradleProperties.SKIP_INSTALL)) {
            atm.logI("Install Skipped")
            return
        }

        def context = androidSdk.context
        def installer = new AndroidInstaller(context)
        def appFile = archiveCollector.collectAppTask.appFile

        atm.logI("Install <${appFile}> on device(s)")
        new AndroidMultiDevicesExecutor(context) {
            @Override
            protected void doEachDevice(AdbDevice device, DeviceInfo deviceInfo) {
                // 安装应用
                installer.installLocal(appFile)
            }
        }.execute()
    }

}