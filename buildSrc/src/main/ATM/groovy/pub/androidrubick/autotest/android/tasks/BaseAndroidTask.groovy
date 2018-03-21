package pub.androidrubick.autotest.android.tasks

import pub.androidrubick.autotest.android.AndroidSdk
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
public abstract class BaseAndroidTask extends BaseATMTask {

    public BaseAndroidTask() {
    }

    /**
     * provide `androidSdk` for tasks
     */
    public final AndroidSdk getAndroidSdk() {
        return AndroidSdk.fromProject(project)
    }

}