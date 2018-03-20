package pub.androidrubick.autotest.android.tasks

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.core.util.Utils

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

    /**
     * serial number of target device
     */
    public static final PROP_TARGET_DEVICE = 'TARGET_DEVICE'

    public BaseAndroidMultiDevicesTask() {

    }

    @TaskAction
    public void doOnMultiDevices() {
        androidSdk.configuration.clearTargetDevice()

        def devices = null
        def targetDeviceSN = atm.prop.value(PROP_TARGET_DEVICE, null)
        if (!Utils.isEmpty(targetDeviceSN)) {
            def targetDevice = androidSdk.configuration.devices.find { device ->
                device.serialNumber == targetDeviceSN
            }
            atm.preds.nonNull(targetDevice, "No device found of serial number `$targetDeviceSN`")
            devices = [targetDevice]
        } else {
            atm.logW("No target device Found by property `$PROP_TARGET_DEVICE`")
            devices = androidSdk.configuration.devices
        }

        def onlineDevices = AdbDevice.filterOnline(devices)
        if (!Utils.isEmpty(onlineDevices)) {
            onlineDevices.each { device ->
                def deviceInfo = androidSdk.adbUtil.deviceInfo
                androidSdk.configuration.targetDevice = device
                androidSdk.configuration.targetDeviceInfo = deviceInfo
                doEachDevice(device, deviceInfo)
            }
        } else {
            atm.logW("No online device Found")
        }
    }

    protected abstract void doEachDevice(AdbDevice device, DeviceInfo deviceInfo)

}