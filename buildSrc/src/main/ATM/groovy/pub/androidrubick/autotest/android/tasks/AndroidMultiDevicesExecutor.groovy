package pub.androidrubick.autotest.android.tasks

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.util.Utils

import static pub.androidrubick.autotest.android.property.AndroidGradleProperties.TARGET_DEVICE

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedAssignment"])
public abstract class AndroidMultiDevicesExecutor extends BaseAndroidAttachment {

    public AndroidMultiDevicesExecutor(ATMContext context) {
        super(context)
    }

    public final void execute() {
        androidSdk.configuration.clearTargetDevice()

        def devices = null
        def targetDeviceSN = atm.prop.value(TARGET_DEVICE, null)
        if (!Utils.isEmpty(targetDeviceSN)) {
            def targetDevice = androidSdk.configuration.devices.find { device ->
                device.serialNumber == targetDeviceSN
            }
            atm.preds.nonNull(targetDevice, "No device found of serial number <${targetDeviceSN}>")
            devices = [targetDevice]
        } else {
            atm.logW("No target device Found by property <${TARGET_DEVICE}>")
            devices = androidSdk.configuration.devices
            atm.logW("So we use devices $devices")
        }

        def onlineDevices = AdbDevice.filterOnline(devices)
        atm.preds.nonEmpty(onlineDevices, "No online device Found")

        onlineDevices.each { device ->
            androidSdk.configuration.targetDevice = device
            def deviceInfo = androidSdk.adbUtil.deviceInfo
            androidSdk.configuration.targetDeviceInfo = deviceInfo
            doEachDevice(device, deviceInfo)
        }
    }

    protected abstract void doEachDevice(AdbDevice device, DeviceInfo deviceInfo)

}