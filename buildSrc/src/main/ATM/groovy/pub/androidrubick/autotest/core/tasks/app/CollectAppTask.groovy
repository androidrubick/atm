package pub.androidrubick.autotest.core.tasks.app

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.property.ATMGradleProperties
import pub.androidrubick.autotest.core.tasks.BaseATMTask
import pub.androidrubick.autotest.core.tasks.TaskGroups

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration", "GrMethodMayBeStatic"])
class CollectAppTask extends BaseATMTask {

    CollectAppTask() {
        group = TaskGroups.GROUP_COLLECT
    }

    private AppArchiveType mAppArchiveType
    public void setAppArchiveType(AppArchiveType type) {
        mAppArchiveType = type
    }

    private File mAppFile

    public List<String> getAppPropertyNames() {
        return ATMGradleProperties.collectAppPathProps(mAppArchiveType)
    }

    public String getAppFilePath() {
        return mAppFile?.absolutePath
    }

    public File getAppFile() {
        return mAppFile
    }

    @TaskAction
    public final void collect() {
        def propertyNames = appPropertyNames
        def appFilePath = null
        if (isEmpty(propertyNames)) {
            atm.logW("Task $name: appPropertyNames not set")
        } else {
            // find first
            def found = propertyNames.collect { name ->
                atm.prop.value(name, null)
            }?.find { path ->
                !isEmpty(path)
            }

            if (found == null) {
                atm.logW("Task $name: App file not set")
            } else {
                appFilePath = found
            }
        }
        mAppFile = atm.preds.isFile(new File(appFilePath), "Task $name")

        doAfterArchiveCollected(mAppFile)
    }

    protected void doAfterArchiveCollected(File appFile) {

    }

}