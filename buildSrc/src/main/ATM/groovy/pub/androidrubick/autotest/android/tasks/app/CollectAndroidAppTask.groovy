package pub.androidrubick.autotest.android.tasks.app

import pub.androidrubick.autotest.android.AndroidSdk
import pub.androidrubick.autotest.android.model.AppInfo
import pub.androidrubick.autotest.core.tasks.app.CollectAppTask

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class CollectAndroidAppTask extends CollectAppTask {

    CollectAndroidAppTask() {
    }

    private AppInfo mAppInfo
    public AppInfo getAppInfo() {
        return mAppInfo
    }

    @Override
    protected void doAfterArchiveCollected(File appFile) {
        super.doAfterArchiveCollected(appFile)
        mAppInfo = androidSdk.cmd.apkAnalyzer.getAppInfo(appFile)
        atm.logI("app info: $appInfo")
    }

    /**
     * provide `androidSdk` for tasks
     */
    public final AndroidSdk getAndroidSdk() {
        return AndroidSdk.fromProject(project)
    }
}