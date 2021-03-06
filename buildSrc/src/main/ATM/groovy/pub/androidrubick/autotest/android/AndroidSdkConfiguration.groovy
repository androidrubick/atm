package pub.androidrubick.autotest.android

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.core.ATMContext

@SuppressWarnings("GroovyUnusedDeclaration")
class AndroidSdkConfiguration extends BaseAndroidAttachment {

    private String mSDKDir = ''

    public AndroidSdkConfiguration(ATMContext context) {
        super(context)
    }

    public AndroidSdkConfiguration setSDKDir(@NonNull String path) {
        return setSDKDir(new File(path))
    }

    public AndroidSdkConfiguration setSDKDir(@NonNull File path) {
        mSDKDir = atm.preds.file(path, 'setSDKDir').absolutePath
        return this
    }

    public String getSDKDir() {
        return mSDKDir
    }

    private final List<AdbDevice> mDevices = []
    public AndroidSdkConfiguration setDevices(List<AdbDevice> devices) {
        mDevices.clear()
        if (devices) {
            mDevices.addAll(devices)
        }
        return this
    }

    @NonNull
    public List<AdbDevice> getDevices() {
        return mDevices.collect()
    }

    private AdbDevice mTargetDevice
    public AndroidSdkConfiguration setTargetDevice(AdbDevice device) {
        mTargetDevice = device
        return this
    }

    public AdbDevice getTargetDevice() {
        return mTargetDevice
    }

    private DeviceInfo mTargetDeviceInfo
    public AndroidSdkConfiguration setTargetDeviceInfo(DeviceInfo deviceInfo) {
        mTargetDeviceInfo = deviceInfo
        return this
    }

    public DeviceInfo getTargetDeviceInfo() {
        return mTargetDeviceInfo
    }

    public AndroidSdkConfiguration clear() {
        mSDKDir = ''
        mDevices.clear()
        return clearTargetDevice()
    }

    public AndroidSdkConfiguration clearTargetDevice() {
        mTargetDevice = null
        mTargetDeviceInfo = null
        return this
    }

}