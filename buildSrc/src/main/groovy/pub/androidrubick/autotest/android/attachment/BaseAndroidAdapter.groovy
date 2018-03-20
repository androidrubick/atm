package pub.androidrubick.autotest.android.attachment

import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.core.ATMContext

@SuppressWarnings("GroovyUnusedDeclaration")
public abstract class BaseAndroidAdapter extends BaseAndroidAttachment {

    public BaseAndroidAdapter(ATMContext context) {
        super(context)
    }

    public boolean isBrand(String brand) {
        DeviceInfo deviceInfo = androidSdk.configuration.targetDeviceInfo
        return deviceInfo.brand.equalsIgnoreCase(brand)
    }

    public boolean isBrandOrManufacturer(String brand) {
        DeviceInfo deviceInfo = androidSdk.configuration.targetDeviceInfo
        return deviceInfo.brand.equalsIgnoreCase(brand) || deviceInfo.manufacturer.equalsIgnoreCase(brand)
    }

    public boolean isXiaomi() {
        return isBrandOrManufacturer('xiaomi') || isBrandOrManufacturer('mi') || isBrandOrManufacturer('xiao mi')
    }

    public boolean isSamsung() {
        return isBrandOrManufacturer('samsung')
    }

    public boolean isHawei() {
        return isBrandOrManufacturer('huawei')
    }

    public boolean isHaweiHonor() {
        return isBrandOrManufacturer('honor')
    }

}