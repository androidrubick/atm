package pub.androidrubick.autotest.android.tasks.launch

import android.support.annotation.NonNull
import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.attachment.instrument.AndroidInstrumentHelper
import pub.androidrubick.autotest.android.attachment.instrument.InstrumentTestClz
import pub.androidrubick.autotest.android.attachment.launch.AndroidLauncher
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.android.tasks.AndroidMultiDevicesExecutor
import pub.androidrubick.autotest.android.tasks.app.BaseAndroidCollectDependentTask
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.tasks.TaskGroups
import pub.androidrubick.autotest.core.util.Utils

import static pub.androidrubick.autotest.android.property.AndroidGradleProperties.TEST_CASE

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class LaunchAndroidAppTask extends BaseAndroidCollectDependentTask {

    LaunchAndroidAppTask() {
        group = TaskGroups.GROUP_LAUNCH
    }

    private final Closure mDefaultTestCase = { AndroidInstrumentHelper helper ->
        def testCaseClzStr = atm.prop.value(TEST_CASE)
        atm.preds.nonEmpty(testCaseClzStr, "Task $name: property <${TEST_CASE}>")
        // 启动测试用例
        InstrumentTestClz testClz = InstrumentTestClz.parse(testCaseClzStr)
        atm.logI("Task $name: start test <$testClz>")
        helper.buildTest(testClz).noTimeout().exec()
    }
    private final List<Closure> mDoTestCases = []
    public void doTestCase(@DelegatesTo(value = AndroidInstrumentHelper.class, strategy = Closure.DELEGATE_FIRST)
                           @NonNull Closure closure) {
        mDoTestCases << closure
    }

    @TaskAction
    public void launch() {
        def context = androidSdk.context
        def appFile = archiveCollector.collectAppTask.appFile

        if (archiveCollector.type == AppArchiveType.AndroidTestApp) {
            def instrumentInfo = androidSdk.cmd.apkAnalyzer.getInstrumentInfo(appFile)
            def helper = new AndroidInstrumentHelper(context, instrumentInfo)
            new AndroidMultiDevicesExecutor(context) {
                @Override
                protected void doEachDevice(AdbDevice device, DeviceInfo deviceInfo) {
                    doInstrumentEachDevice(helper, device, deviceInfo)
                }
            }.execute()
        } else {
            def appInfo = androidSdk.cmd.apkAnalyzer.getAppInfo(appFile)
            def launcher = new AndroidLauncher(context)
            new AndroidMultiDevicesExecutor(context) {
                @Override
                protected void doEachDevice(AdbDevice device, DeviceInfo deviceInfo) {
                    androidSdk.adbShell.with {
                        pm.ensurePkgInstalled(appInfo.pkg)
                        am.forceStop(appInfo.pkg)
                    }
                    // 启动应用
                    launcher.launch(appInfo.launchInfo)
                }
            }.execute()
        }
    }

    @SuppressWarnings("UnnecessaryQualifiedReference")
    private void doInstrumentEachDevice(AndroidInstrumentHelper helper, AdbDevice device, DeviceInfo deviceInfo) {
        def instrumentInfo = helper.instrumentInfo
        def targetPkg = instrumentInfo.targetPkg
        def testAppPkg = instrumentInfo.pkg
        androidSdk.adbShell.with {
            pm.ensurePkgInstalled(targetPkg)
            am.forceStop(targetPkg)

            pm.ensurePkgInstalled(testAppPkg)
            am.forceStop(testAppPkg)
        }

        if (Utils.isEmpty(mDoTestCases)) {
            mDefaultTestCase.call(helper)
        } else {
            mDoTestCases.each { closure ->
                closure.setDelegate(helper)
                closure.setResolveStrategy(Closure.DELEGATE_FIRST)
                if (closure.getMaximumNumberOfParameters() == 0) {
                    closure.call()
                } else {
                    closure.call(helper)
                }
            }
        }
    }

}