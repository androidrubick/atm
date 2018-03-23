package pub.androidrubick.autotest.android.tasks.other

import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.android.tasks.BaseAndroidMultiDevicesTask

@SuppressWarnings("GroovyUnusedDeclaration")
public class DumpUITask extends BaseAndroidMultiDevicesTask {

    @Override
    protected void doEachDevice(AdbDevice device, DeviceInfo deviceInfo) {
        def dirName = "${deviceInfo.brand}-${deviceInfo.model}-${deviceInfo.osVersion}"
        def file = androidSdk.adbUtil.dumpUIFile2Local(dirName)
        atm.logI("dump ui to file: $file")
    }
}