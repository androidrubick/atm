package pub.androidrubick.autotest.android.tasks

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.core.tasks.TaskGroups

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class CollectAndroidDeviceTask extends BaseAndroidTask {

    CollectAndroidDeviceTask() {
        group = TaskGroups.GROUP_COLLECT
    }

    @TaskAction
    public void collect() {
        def devices = atm.preds.nonEmpty(androidSdk.adb.devices(), 'android devices')

        androidSdk.configuration.with {
            // set all
            setDevices(devices)
            // set default
            setTargetDevice(devices[0])
        }
    }

}