package pub.androidrubick.autotest.android.attachment.cmd

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult

import static pub.androidrubick.autotest.core.util.CmdResultUtils.*
import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings("GroovyUnusedDeclaration")
class AdbShell extends BaseAndroidAttachment {

    public final AdbShellAm am
    public final AdbShellPm pm
    AdbShell(ATMContext context) {
        super(context)
        am = new AdbShellAm(context)
        pm = new AdbShellPm(context)
    }

    private ExecProcBuilder shell(String command) {
        return androidSdk.cmd.adb.shell(command)
    }

    public ExecProcBuilder am(String command) {
        return shell("am $command")
    }

    public ExecProcBuilder pm(String command) {
        return shell("pm $command")
    }

    /**
     * @return multi-line result like:
     * [ro.product.board]: [MSM8974]
     * [ro.product.brand]: [Xiaomi]
     *
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public Map props(String grep = null) {
        ExecResult devicePropsResult = shell("getprop ${!isEmpty(grep) ? "| grep $grep" : ''}").exec()
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
        return props
    }

    /**
     * @param 属性名称，比如`ro.product.brand`
     * @return 如果获取失败或者没有属性，返回空字符串；否则返回相应属性的值
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public String prop(String name) {
        def devicePropResult = shell("getprop $name").exec()
        return isEmpty(devicePropResult.text) ? '' : filterOneLineValue(devicePropResult.text)
    }

}