package pub.androidrubick.autotest.core.tasks.app

import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType

/**
 * 定义android_sdk命令行调用UI相关的方法
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class DefaultArchiveCollector extends ArchiveCollector<CollectAppTask> {

    public DefaultArchiveCollector(ATMContext context, AppArchiveType type) {
        super(context)
    }

    public final CollectAppTask createDefaultCollectAppTask() {
        return createCollectAppTask(CollectAppTask.class)
    }

}