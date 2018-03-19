package pub.androidrubick.autotest.android

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment

@SuppressWarnings("GroovyUnusedDeclaration")
class AndroidSdkConfiguration extends BaseAttachment {

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
    }

    public AdbDevice getTargetDevice() {
        return mTargetDevice
    }

    public AndroidSdkConfiguration clear() {
        mSDKDir = ''
        mDevices.clear()
        mTargetDevice = null
        return this
    }

}