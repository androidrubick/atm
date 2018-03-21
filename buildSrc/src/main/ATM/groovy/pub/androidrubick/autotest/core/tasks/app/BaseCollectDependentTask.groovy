package pub.androidrubick.autotest.core.tasks.app

import pub.androidrubick.autotest.core.tasks.BaseATMTask

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public abstract class BaseCollectDependentTask<AC extends ArchiveCollector> extends BaseATMTask implements IBaseCollectDependentTask<AC> {

    public BaseCollectDependentTask() {
    }

    private AC mArchiveCollector

    @Override
    public void setArchiveCollector(AC archiveCollector) {
        mArchiveCollector = archiveCollector
    }

    @Override
    public AC getArchiveCollector() {
        return mArchiveCollector
    }

}