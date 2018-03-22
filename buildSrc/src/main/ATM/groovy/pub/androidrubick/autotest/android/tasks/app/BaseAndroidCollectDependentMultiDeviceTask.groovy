package pub.androidrubick.autotest.android.tasks.app

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.android.tasks.AndroidMultiDevicesExecutor

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public abstract class BaseAndroidCollectDependentMultiDeviceTask extends BaseAndroidCollectDependentTask {

    public BaseAndroidCollectDependentMultiDeviceTask() {
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