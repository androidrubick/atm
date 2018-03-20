package pub.androidrubick.autotest.android.tasks.install

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.attachment.installer.AndroidInstaller
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.android.tasks.AndroidMultiDevicesExecutor
import pub.androidrubick.autotest.android.tasks.BaseCollectDependentTask
import pub.androidrubick.autotest.core.tasks.TaskGroups

import static pub.androidrubick.autotest.android.property.AndroidGradleProperties.UNINSTALL_OLD

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedAssignment"])
public class UninstallApkTask extends BaseCollectDependentTask {

    public UninstallApkTask() {
        group = TaskGroups.GROUP_INSTALL
    }

    @TaskAction
    public void uninstall() {
        if (!atm.prop.isTrue(UNINSTALL_OLD)) {
            atm.log("Task $name: uninstall skipped")
            return
        }

        def context = androidSdk.context
        def installer = new AndroidInstaller(context)
        def appFile = archiveCollector.collectAppTask.appFile
        def appInfo = archiveCollector.collectAppTask.appInfo

        atm.logI("Task $name: uninstall <${appInfo.pkg}> on device(s)")
        new AndroidMultiDevicesExecutor(context) {
            @Override
            protected void doEachDevice(AdbDevice device, DeviceInfo deviceInfo) {
                // 卸载应用
                installer.uninstall(appInfo.pkg)
            }
        }.execute()
    }

}