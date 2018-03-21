package pub.androidrubick.autotest.android.tasks.launch

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.attachment.instrument.AndroidTestCase
import pub.androidrubick.autotest.android.attachment.instrument.InstrumentTestClz
import pub.androidrubick.autotest.android.attachment.launch.AndroidLauncher
import pub.androidrubick.autotest.android.model.AdbDevice
import pub.androidrubick.autotest.android.model.DeviceInfo
import pub.androidrubick.autotest.android.tasks.AndroidMultiDevicesExecutor
import pub.androidrubick.autotest.android.tasks.BaseCollectDependentTask
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.tasks.TaskGroups

import static pub.androidrubick.autotest.android.property.AndroidGradleProperties.TEST_CASE

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class LaunchAndroidAppTask extends BaseCollectDependentTask {

    LaunchAndroidAppTask() {
        group = TaskGroups.GROUP_LAUNCH
    }

    @TaskAction
    public void launch() {
        def context = androidSdk.context
        def appFile = archiveCollector.collectAppTask.appFile

        if (archiveCollector.type == AppArchiveType.AndroidTestApp) {
            def instrumentInfo = androidSdk.cmd.apkAnalyzer.getInstrumentInfo(appFile)
            def testCaseClzStr = atm.prop.value(TEST_CASE)
            atm.preds.nonEmpty(testCaseClzStr, "Task $name: property <${TEST_CASE}>")
            new AndroidMultiDevicesExecutor(context) {
                @Override
                protected void doEachDevice(AdbDevice device, DeviceInfo deviceInfo) {
                    androidSdk.adbShell.with {
                        pm.ensurePkgInstalled(instrumentInfo.pkg)
                        am.forceStop(instrumentInfo.pkg)

                        // 启动测试用例
                        InstrumentTestClz testClz = InstrumentTestClz.parse(testCaseClzStr)
                        am.startInstrument(new AndroidTestCase(instrumentInfo.asComponent(), testClz))
                    }
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
                        // 启动应用
                        launcher.launch(appInfo.launchInfo)
                    }
                }
            }.execute()
        }
    }

}