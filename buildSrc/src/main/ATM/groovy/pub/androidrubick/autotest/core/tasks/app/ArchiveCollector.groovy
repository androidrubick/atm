package pub.androidrubick.autotest.core.tasks.app

import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.util.Utils

/**
 * 定义android_sdk命令行调用UI相关的方法
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class ArchiveCollector<Task extends CollectAppTask> extends BaseAttachment {

    private final AppArchiveType mType
    private Task mCollectAppTask = null
    public ArchiveCollector(ATMContext context, AppArchiveType type) {
        super(context)
        mType = type
    }

    public final AppArchiveType getType() {
        return mType
    }

    public final Task getCollectAppTask() {
        return mCollectAppTask
    }

    protected final Task createCollectAppTask(Class<? extends Task> clz) {
        if (mCollectAppTask != null) {
            return mCollectAppTask
        }
        String capitalizedTypeName = Utils.capitalize(type.name)
        mCollectAppTask = project.tasks.create('collect' + capitalizedTypeName, clz) { Task task ->
            task.appArchiveType = this.type
        }
        return mCollectAppTask
    }

}