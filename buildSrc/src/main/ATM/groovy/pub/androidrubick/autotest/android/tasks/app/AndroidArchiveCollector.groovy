package pub.androidrubick.autotest.android.tasks.app

import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.tasks.app.ArchiveCollector

/**
 * 定义android_sdk命令行调用UI相关的方法
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidArchiveCollector extends ArchiveCollector<CollectAndroidAppTask> {

    public AndroidArchiveCollector(ATMContext context, AppArchiveType type) {
        super(context, type)
    }

    public final CollectAndroidAppTask createCollectAndroidAppTask() {
        return createCollectAppTask(CollectAndroidAppTask.class)
    }

}