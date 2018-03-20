package pub.androidrubick.autotest.android.tasks.app

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.tasks.BaseCollectDependentTask
import pub.androidrubick.autotest.core.tasks.TaskGroups

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class CollectAndroidAppTask extends BaseCollectDependentTask {

    CollectAndroidAppTask() {
        group = TaskGroups.GROUP_COLLECT
    }

    private final List<String> mAppPropertyNames = []
    private File mAppFile

    public void setAppPropertyNames(List<String> names) {
        mAppPropertyNames.clear()
        mAppPropertyNames.addAll(names)
    }

    public void setAppPropertyNames(String... names) {
        mAppPropertyNames.clear()
        mAppPropertyNames.addAll(names)
    }

    public void appPropertyName(String name) {
        mAppPropertyNames.add(name)
    }

    public void appPropertyNames(String ...names) {
        names.each { name ->
            appPropertyName(name)
        }
    }

    public void appPropertyNames(List<String> names) {
        names.each { name ->
            appPropertyName(name)
        }
    }

    public List<String> getAppPropertyNames() {
        return mAppPropertyNames
    }

    public String getAppFilePath() {
        return mAppFile?.absolutePath
    }

    public File getAppFile() {
        return mAppFile
    }

    @TaskAction
    public void collect() {
        def appFilePath = null
        if (isEmpty(mAppPropertyNames)) {
            atm.logW("Task $name: appPropertyNames not set")
        } else {
            // find first
            def found = mAppPropertyNames.collect { name ->
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
    }

}