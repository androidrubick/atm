package pub.androidrubick.autotest.android.attachment.cmd

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult

import static pub.androidrubick.autotest.core.util.FileUtils.ensureDirExists
import static pub.androidrubick.autotest.core.util.FileUtils.ensureFileNotExists
import static pub.androidrubick.autotest.core.util.Utils.isEmpty
import static pub.androidrubick.autotest.core.util.Utils.randomStr

/**
 * 封装一些工具方法，
 */
@SuppressWarnings("GroovyUnusedDeclaration")
class AdbUtil extends BaseAndroidAttachment {

    private final File REMOTE_TMP_DIR = new File('/data/local/tmp')
    private final String REMOTE_TMP_FILE_PRE = 'vsri_tmp_apk_'

    AdbUtil(ATMContext context) {
        super(context)
    }

    /**
     * @return 随机生成临时文件名称
     */
    public String remoteTmpFileName() {
        return REMOTE_TMP_FILE_PRE + randomStr(16)
    }

    public File remoteTmpFile() {
        return new File(REMOTE_TMP_DIR, remoteTmpFileName())
    }

    public ExecResult rmRemoteTmpFiles() {
        return androidSdk.adbShell.builder("rm -rf ${REMOTE_TMP_DIR.absolutePath}/$REMOTE_TMP_FILE_PRE*")
                .exec().checkSuccess('rmRemoteTmpFiles')
    }

    /**
     * 生成设备当前的UI结构，并存储到本地
     * @return 本地的文件路径，File
     */
    public File dumpUIFile2Local() {
        def dumpFileInDevice = new File(androidSdk.adbShell.uiautomator.dumpUIFileInDevice())
        def tmpDir = new File(project.rootProject.buildDir, 'tmp/dump-ui')
        ensureDirExists(tmpDir)

        def dumpFileInLocal = new File(tmpDir, dumpFileInDevice.name)
        ensureFileNotExists(dumpFileInLocal)

        androidSdk.adb.pullFile(dumpFileInDevice, dumpFileInLocal)

        atm.preds.isFile(dumpFileInLocal, 'dumpUIFile2Local')
        atm.logI("dumpUIFile2Local dumpFile: $dumpFileInLocal")
        return dumpFileInLocal
    }

    /**
     * @return 机型信息
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public DeviceInfo getDeviceInfo() {
        def brand = this.prop('ro.product.brand')
        def manufacturer = this.prop('ro.product.manufacturer')
        def model = this.prop('ro.product.model')
        def osName = 'Android' // deviceProp('ro.build.version.base_os')
        if (isEmpty(osName)) {
            osName = 'Android'
        }
        def osVersion = this.prop('ro.build.version.release')
        def apiLevel = this.prop('ro.build.version.sdk')
        atm.preds.isTrue(!isEmpty(brand) || !isEmpty(model) || !isEmpty(osVersion) || !isEmpty(apiLevel), 'deviceFullName')
        def fullName = "${brand} ${model}, ${osName} ${osVersion}, API ${apiLevel}"

        def deviceInfo = new DeviceInfo()
        deviceInfo.fullName = fullName
        deviceInfo.brand = brand
        deviceInfo.manufacturer = manufacturer
        deviceInfo.model = model
        deviceInfo.osName = osName
        deviceInfo.osVersion = osVersion
        deviceInfo.extras.apiLevel = apiLevel
        def device = deviceInfo.extras.device = androidSdk.configuration.targetDevice
        deviceInfo.extras.serialNumber = device?.serialNumber
        return deviceInfo
    }

    private String prop(String name) {
        return androidSdk.adbShell.prop(name)
    }

}