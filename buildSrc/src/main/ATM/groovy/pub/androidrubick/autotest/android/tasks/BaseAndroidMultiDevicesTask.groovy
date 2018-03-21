package pub.androidrubick.autotest.android.tasks

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedAssignment"])
public abstract class BaseAndroidMultiDevicesTask extends BaseAndroidTask {

    public BaseAndroidMultiDevicesTask() {

    }

    @TaskAction
    public void doOnMultiDevices() {
        new AndroidMultiDevicesExecutor(androidSdk.context) {
            @Override
            protected void doEachDevice(AdbDevice device, DeviceInfo deviceInfo) {
                BaseAndroidMultiDevicesTask.this.doEachDevice(device, deviceInfo)
            }
        }.execute()
    }

    protected abstract void doEachDevice(AdbDevice device, DeviceInfo deviceInfo)

}