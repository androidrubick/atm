package pub.androidrubick.autotest.android.tasks.app

import pub.androidrubick.autotest.android.tasks.BaseAndroidTask
import pub.androidrubick.autotest.core.tasks.app.IBaseCollectDependentTask

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public abstract class BaseAndroidCollectDependentTask extends BaseAndroidTask
        implements IBaseCollectDependentTask<AndroidArchiveCollector> {

    public BaseAndroidCollectDependentTask() {
    }

    private AndroidArchiveCollector mArchiveCollector

    public void setArchiveCollector(AndroidArchiveCollector archiveCollector) {
        mArchiveCollector = archiveCollector
    }

    @Override
    public AndroidArchiveCollector getArchiveCollector() {
        return mArchiveCollector
    }

}