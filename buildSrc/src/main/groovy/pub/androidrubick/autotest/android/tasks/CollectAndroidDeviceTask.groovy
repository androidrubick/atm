package pub.androidrubick.autotest.android.tasks

import org.gradle.api.tasks.TaskAction

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class CollectAndroidDeviceTask extends BaseAndroidTask {

    CollectAndroidDeviceTask() {
        group = TaskGroups.GROUP_COLLECT
    }

    @TaskAction
    public void collect() {
        def devices = androidSdk.cmd.adb.devices()
        atm.preds.nonEmpty(devices, 'android devices')

        androidSdk.configuration.devices = devices
    }

}