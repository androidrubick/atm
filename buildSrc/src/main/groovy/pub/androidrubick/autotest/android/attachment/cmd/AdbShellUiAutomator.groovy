package pub.androidrubick.autotest.android.attachment.cmd

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult
import pub.androidrubick.autotest.core.model.Point
import pub.androidrubick.autotest.core.model.Rect

import static pub.androidrubick.autotest.core.util.FileUtils.ensureDirExists
import static pub.androidrubick.autotest.core.util.FileUtils.ensureFileNotExists

@SuppressWarnings("GroovyUnusedDeclaration")
class AdbShellUiAutomator extends BaseAndroidAttachment {

    AdbShellUiAutomator(ATMContext context) {
        super(context)
    }

    private ExecProcBuilder uiautomator(String command) {
        return androidSdk.adbShell.uiautomator(command)
    }

    public ExecProcBuilder dump() {
        return uiautomator('dump')
    }

    /**
     * 生成设备当前的UI结构
     * @return 设备中的文件路径，String
     */
    public String dumpUIFileInDevice() {
        def dumpViewResult = dump().exec().checkSuccess('dump view hierarchy')

        def resultMatcher = dumpViewResult.text.split('\n').collect { line ->
            return line =~ /.*\s+(\/.+\.xml)/
        }.find { m ->
            // find first
            m.find()
        }

        atm.preds.isTrue(resultMatcher != null, 'dump view hierarchy <resultMatcher>')

        def dumpFile = resultMatcher.group(1)
        atm.log("dumpUIFileInDevice dumpFile: $dumpFile")
        return dumpFile
    }

    /**
     * 生成设备当前的UI结构，并存储到本地
     * @return 本地的文件路径，File
     */
    public File dumpUIFile2Local() {
        def dumpFileInDevice = new File(dumpUIFileInDevice())
        def tmpDir = new File(project.rootProject.buildDir, 'tmp/dump-ui')
        ensureDirExists(tmpDir)

        def dumpFileInLocal = new File(tmpDir, dumpFileInDevice.name)
        ensureFileNotExists(dumpFileInLocal)

        storage.pullFile(dumpFileInDevice, dumpFileInLocal)

        atm.preds.file(dumpFileInLocal, 'dumpUIFile2Local')
        atm.logI("dumpUIFile2Local dumpFile: $dumpFileInLocal")
        return dumpFileInLocal
    }

    public Rect parseNodeRect(node) {
        def rect = new Rect()
        def bounds = node.'@bounds'
        if (!isEmpty(bounds)) {
            // format: [left, top][right, bottom]
            def m = bounds =~ /\[\s*(\d+)[,，\s]+(\d+)\s*\].*\[\s*(\d+)[,，\s]+(\d+)\s*\]/
            if (m.find()) {
                rect.set(m.group(1), m.group(2), m.group(3), m.group(4))
            }
        }
        return rect
    }

    // input text <string>
    // input keyevent <key code number or name>
    // input tap <x> <y>
    // input swipe <x1> <y1> <x2> <y2>
    public ExecResult performClick(node) {
        androidSdk.adbShell.input.click(parseNodeRect(node).center())
    }

    public ExecResult performScrollV(node) {
        def rect = parseNodeRect(node)
        // 我们尽量在区域内滑动
        int top = rect.top + 20
        int bottom = Math.min(Math.max(top, rect.bottom - 20), rect.bottom)
        androidSdk.adbShell.input.scroll(Point.of(rect.centerX(), bottom), Point.of(rect.centerX(), top))
    }
}