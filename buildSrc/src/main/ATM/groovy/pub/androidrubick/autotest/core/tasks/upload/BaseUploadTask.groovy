package pub.androidrubick.autotest.core.tasks.upload

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.core.attachment.app.AppArchiveInfo
import pub.androidrubick.autotest.core.tasks.BaseATMTask
import pub.androidrubick.autotest.core.tasks.TaskGroups

@SuppressWarnings("GroovyUnusedDeclaration")
abstract class BaseUploadTask extends BaseATMTask {

    BaseUploadTask() {
        group = TaskGroups.GROUP_UPLOAD
    }

    private AppArchiveInfo mAppArchiveInfo
    public AppArchiveInfo getAppArchive() {
        return mAppArchiveInfo
    }

    public void appArchive(AppArchiveInfo appArchiveInfo) {
        mAppArchiveInfo = appArchiveInfo
    }

    @TaskAction
    public void uploadAction() {
        if (null == mAppArchiveInfo) {
            atm.logW("App Archive not set, so upload ignored")
            return
        }

        upload([path: appArchive.filePath.absolutePath, type: appArchive.type])
    }

    protected abstract void upload(archiveParams)

}