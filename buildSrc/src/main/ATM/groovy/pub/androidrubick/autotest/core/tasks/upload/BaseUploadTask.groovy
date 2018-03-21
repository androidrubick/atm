package pub.androidrubick.autotest.core.tasks.upload

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.core.tasks.TaskGroups
import pub.androidrubick.autotest.core.tasks.app.ArchiveCollector
import pub.androidrubick.autotest.core.tasks.app.BaseCollectDependentTask

@SuppressWarnings("GroovyUnusedDeclaration")
abstract class BaseUploadTask extends BaseCollectDependentTask<ArchiveCollector> {

    BaseUploadTask() {
        group = TaskGroups.GROUP_UPLOAD
    }

    @TaskAction
    public final void uploadAction() {
        def file = archiveCollector.collectAppTask.appFile
        atm.preds.isFile(file, "Task $name: App Archive not set")

        upload([path: file.absolutePath, type: archiveCollector.type])
    }

    protected abstract void upload(archiveParams)

}