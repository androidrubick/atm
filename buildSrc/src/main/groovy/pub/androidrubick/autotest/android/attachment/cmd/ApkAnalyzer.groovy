package pub.androidrubick.autotest.android.attachment.cmd

import android.content.ComponentName
import android.content.Intent
import groovy.xml.Namespace
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.attachment.instrument.InstrumentInfo
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder

@SuppressWarnings("GroovyUnusedDeclaration")
class ApkAnalyzer extends BaseAndroidAttachment {

    public static final Namespace AndroidNS = new Namespace('http://schemas.android.com/apk/res/android', 'android')

    ApkAnalyzer(ATMContext context) {
        super(context)
    }

    public ExecProcBuilder builder(String cmd) {
        return androidSdk.cmd.tools_bin("apkanalyzer $command")
    }

    public printManifest(File installApkFile) {
        def manifestResult = builder("manifest print ${installApkFile.absolutePath}").exec()
                .checkNonEmptyText('printManifest')

        def manifestNode = new XmlParser().parseText(manifestResult.text)

        atm.preds.nonEmpty(manifestNode, 'printManifest manifestNode')

        def pkg = manifestNode.'@package'
        atm.log("getInstrumentInfo package name: $pkg")
        return manifestNode
    }

    /**
     * 根据apk文件，解析得到启动app的信息
     *
     * @param installApkFile apk文件路径
     * @return 解析得到启动app的信息
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public ComponentName getLaunchInfo(File installApkFile) {
        def manifestNode = printManifest(installApkFile)
        atm.log("getLaunchInfo package name: ${manifestNode.'@package'}")

        def applicationNode = manifestNode.'**'.find { node ->
            node.name() == 'application'
        }
        atm.preds.nonEmpty(applicationNode, 'getLaunchInfo applicationNode')

        def launcherActivityNode = applicationNode.'**'.find { activityNode ->
            activityNode.name() == 'activity' && null != activityNode.'**'.find { intentFilterNode ->
                intentFilterNode.name() == 'intent-filter' && null != intentFilterNode.'**'.find { actionNode ->
                    actionNode.name() == 'action' && actionNode.attributes()[AndroidNS.name] == Intent.ACTION_MAIN
                } && null != intentFilterNode.'**'.find { categoryNode ->
                    categoryNode.name() == 'category' && categoryNode.attributes()[AndroidNS.name] == Intent.CATEGORY_LAUNCHER
                }
            }
        }
        atm.preds.nonEmpty(launcherActivityNode, 'getLaunchInfo launcherActivityNode not found')
        atm.log("launcherActivityNode: ${launcherActivityNode}")

        return ComponentName.createRelative(manifestNode.'@package', launcherActivityNode.attributes()[AndroidNS.name])
    }

    public InstrumentInfo getInstrumentInfo(File testApkFile) {
        def manifestNode = printManifest(testApkFile)

        def instrumentationNode = manifestNode.'**'.find { node ->
            node.name() == 'instrumentation'
        }
        atm.preds.nonEmpty(instrumentationNode, 'getInstrumentInfo instrumentationNode')

        def instrumentationClz = instrumentationNode.attributes()[AndroidNS.name]
        def targetPkg = instrumentationNode.attributes()[AndroidNS.targetPackage]

        atm.preds.nonEmpty(instrumentationClz, 'getInstrumentInfo instrumentationClz')
        atm.preds.nonEmpty(targetPkg, 'getInstrumentInfo targetPkg')
        return new InstrumentInfo(pkg, instrumentationClz, targetPkg)
    }
}