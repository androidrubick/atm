package pub.androidrubick.autotest.android.attachment.cmd

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

/**
 * 封装一些工具方法，
 */
@SuppressWarnings("GroovyUnusedDeclaration")
class AdbUtil extends BaseAndroidAttachment {

    AdbUtil(ATMContext context) {
        super(context)
        REMOTE_TMP_DIR = new File('/data/local/tmp')
        REMOTE_TMP_FILE_PRE = 'vsri_tmp_apk_'
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
        return shell("rm -rf ${REMOTE_TMP_DIR.absolutePath}/$REMOTE_TMP_FILE_PRE*").exec()
                .checkSuccess('rmRemoteTmpFiles')
    }

    /**
     * @return 机型信息
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public DeviceInfo getInfo() {
        def brand = this.prop('ro.product.brand')
        def manufacturer = this.prop('ro.product.manufacturer')
        def model = this.prop('ro.product.model')
        def osName = 'Android' // deviceProp('ro.build.version.base_os')
        if (isEmpty(osName)) {
            osName = 'Android'
        }
        def osVersion = this.prop('ro.build.version.release')
        def apiLevel = this.prop('ro.build.version.sdk')
        atm.preds.isTrue(!isEmpty(brand) || !isEmpty(model) || !isEmpty(osName) || !isEmpty(osVersion) || !isEmpty(apiLevel), 'deviceFullName')
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

    private ExecProcBuilder shell(String command) {
        return androidSdk.adbShell.builder(command)
    }

    private String prop(String name) {
        return androidSdk.adbShell.prop(name)
    }

}