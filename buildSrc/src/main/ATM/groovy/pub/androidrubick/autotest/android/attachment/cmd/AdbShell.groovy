package pub.androidrubick.autotest.android.attachment.cmd

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult

import static pub.androidrubick.autotest.core.util.CmdResultUtils.filterOneLineValue
import static pub.androidrubick.autotest.core.util.CmdResultUtils.nonEmptyLines
import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings("GroovyUnusedDeclaration")
class AdbShell extends BaseAndroidAttachment {

    public final AdbShellAm am
    public final AdbShellPm pm
    public final AdbShellUiAutomator uiautomator
    public final AdbShellInput input
    AdbShell(ATMContext context) {
        super(context)
        am = new AdbShellAm(context)
        pm = new AdbShellPm(context)
        uiautomator = new AdbShellUiAutomator(context)
        input = new AdbShellInput(context)
    }

    public ExecProcBuilder builder(String command) {
        return androidSdk.adb.builder("shell $command")
    }

    /**
     * @return multi-line result like:
     * [ro.product.board]: [MSM8974]
     * [ro.product.brand]: [Xiaomi]
     *
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    @NonNull
    public Map props(String grep = null) {
        ExecResult devicePropsResult = builder("getprop ${!isEmpty(grep) ? "| grep $grep" : ''}").exec()
        devicePropsResult.checkSuccess('deviceProps')
        if (isEmpty(devicePropsResult.text)) {
            return [:]
        }

        def props = nonEmptyLines(devicePropsResult.text)?.collect { line ->
            // format: [key]: [value]
            return line =~ /\[(.*)\][\s\:：]+\[(.*)\]/
        }?.findAll { m ->
            m.find()
        }?.collectEntries { m ->
            [ (m.group(1)) : m.group(2) ]
        }
        return props ?: [:]
    }

    /**
     * @param 属性名称，比如`ro.product.brand`
     * @return 如果获取失败或者没有属性，返回空字符串；否则返回相应属性的值
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public String prop(String name) {
        def devicePropResult = builder("getprop $name").exec()
        return isEmpty(devicePropResult.text) ? '' : filterOneLineValue(devicePropResult.text)
    }

}