package pub.androidrubick.autotest.android.tasks

import pub.androidrubick.autotest.android.tasks.app.AndroidArchiveCollector

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public abstract class BaseCollectDependentTask extends BaseAndroidTask {

    public BaseCollectDependentTask() {
    }

    private AndroidArchiveCollector mArchiveCollector

    public void setArchiveCollector(AndroidArchiveCollector archiveCollector) {
        mArchiveCollector = archiveCollector
    }

    public AndroidArchiveCollector getArchiveCollector() {
        return mArchiveCollector
    }

}