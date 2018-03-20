package pub.androidrubick.autotest.android.tasks.app

import pub.androidrubick.autotest.android.AndroidPlugin
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.property.AndroidGradleProperties
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.util.Utils

/**
 * 定义android_sdk命令行调用UI相关的方法
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidArchiveCollector extends BaseAndroidAttachment {

    public static List<String> collectProps(AppArchiveType type) {
        return type == AppArchiveType.AndroidTestApp ? AndroidGradleProperties.testAppPath
                : AndroidGradleProperties.appPath
    }

    private final AppArchiveType mType
    private CollectAndroidAppTask mCollectAppTask
    public AndroidArchiveCollector(ATMContext context, AppArchiveType type) {
        super(context)
        mType = type
    }

    public final AppArchiveType getType() {
        return mType
    }

    public final CollectAndroidAppTask getCollectAppTask() {
        return mCollectAppTask
    }

    public void createCollectAppTask() {
        if (mCollectAppTask != null) {
            return
        }
        String capitalizedTypeName = Utils.capitalize(type.name)
        project.with {
            mCollectAppTask = tasks.create('collect' + capitalizedTypeName, CollectAndroidAppTask.class) { task ->
                task.archiveCollector = this
                task.appPropertyNames = collectProps(type)
            }.dependsOn(AndroidPlugin.TASK_COLLECT_ANDROID_DEVICE)
        }
    }

}