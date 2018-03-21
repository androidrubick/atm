package pub.androidrubick.autotest.android.attachment.cmd

import android.content.ComponentName
import android.content.Intent
import groovy.xml.Namespace
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.attachment.instrument.InstrumentInfo
import pub.androidrubick.autotest.android.model.AppInfo
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.util.Utils

@SuppressWarnings("GroovyUnusedDeclaration")
class ApkAnalyzer extends BaseAndroidAttachment {

    public static final Namespace AndroidNS = new Namespace('http://schemas.android.com/apk/res/android', 'android')

    ApkAnalyzer(ATMContext context) {
        super(context)
    }

    public ExecProcBuilder builder(String command) {
        return androidSdk.cmd.tools_bin("apkanalyzer $command")
    }

    public printManifest(File installApkFile) {
        def manifestResult = builder("manifest print ${installApkFile.absolutePath}").exec()
                .checkNonEmptyText('printManifest')

        def manifestNode = new XmlParser().parseText(manifestResult.text)

        atm.preds.nonEmpty(manifestNode, 'printManifest manifestNode')

        def pkg = manifestNode.'@package'
        atm.log("printManifest: $installApkFile package name: $pkg")
        return manifestNode
    }

    /**
     * 根据apk文件，解析得到app的信息
     *
     * @param installApkFile apk文件路径
     * @return 解析得到启动app的信息
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public AppInfo getAppInfo(File installApkFile) {
        def manifestNode = printManifest(installApkFile)
        return getAppInfoByNode(manifestNode)
    }

    public AppInfo getAppInfoByNode(manifestNode) {
        def pkg = manifestNode.'@package'

        def versionName = manifestNode.attributes()[AndroidNS.versionName] ?: '1.0.0'
        def versionCode = manifestNode.attributes()[AndroidNS.versionCode] ?: 1

        def launchInfo = null
        def applicationNode = manifestNode.'**'.find { node ->
            node.name() == 'application'
        }
        if (Utils.isEmpty(applicationNode)) {
            atm.log("getAppInfoByNode: no application node")
        } else {
            def launcherActivityNode = applicationNode.'**'.find { activityNode ->
                activityNode.name() == 'activity' && null != activityNode.'**'.find { intentFilterNode ->
                    intentFilterNode.name() == 'intent-filter' && null != intentFilterNode.'**'.find { actionNode ->
                        actionNode.name() == 'action' && actionNode.attributes()[AndroidNS.name] == Intent.ACTION_MAIN
                    } && null != intentFilterNode.'**'.find { categoryNode ->
                        categoryNode.name() == 'category' && categoryNode.attributes()[AndroidNS.name] == Intent.CATEGORY_LAUNCHER
                    }
                }
            }

            atm.log("getAppInfoByNode: launcherActivityNode: ${launcherActivityNode}")
            if (Utils.isEmpty(launcherActivityNode)) {
                atm.logW("getAppInfoByNode: launcherActivityNode not found")
            } else {
                launchInfo = ComponentName.createRelative(pkg, launcherActivityNode.attributes()[AndroidNS.name])
            }
        }
        return new AppInfo(pkg, launchInfo, versionName as String, versionCode as int)
    }

    public InstrumentInfo getInstrumentInfo(File testApkFile) {
        def manifestNode = printManifest(testApkFile)
        def appInfo = getAppInfoByNode(manifestNode)

        def instrumentationNode = manifestNode.'**'.find { node ->
            node.name() == 'instrumentation'
        }
        atm.preds.nonEmpty(instrumentationNode, 'getInstrumentInfo instrumentationNode')

        def instrumentationClz = instrumentationNode.attributes()[AndroidNS.name]
        def targetPkg = instrumentationNode.attributes()[AndroidNS.targetPackage]

        atm.preds.nonEmpty(instrumentationClz, 'getInstrumentInfo instrumentationClz')
        atm.preds.nonEmpty(targetPkg, 'getInstrumentInfo targetPkg')
        return new InstrumentInfo(appInfo, instrumentationClz, targetPkg)
    }
}