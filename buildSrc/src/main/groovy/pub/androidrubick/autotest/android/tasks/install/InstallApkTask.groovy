package pub.androidrubick.autotest.android.tasks.install

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.android.tasks.AndroidMultiDevicesExecutor
import pub.androidrubick.autotest.android.tasks.BaseCollectDependentTask
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
public class InstallApkTask extends BaseCollectDependentTask {

    public InstallApkTask() {
        group = TaskGroups.GROUP_INSTALL
    }

    @TaskAction
    public void install() {
        def context = androidSdk.context
        def appFile = archiveCollector.collectAppTask.appFile
        new AndroidMultiDevicesExecutor(context) {
            @Override
            protected void doEachDevice(AdbDevice device, DeviceInfo deviceInfo) {
                // 安装应用
                new AndroidInstaller(context).installLocal(appFile)
            }
        }.execute()
    }

}