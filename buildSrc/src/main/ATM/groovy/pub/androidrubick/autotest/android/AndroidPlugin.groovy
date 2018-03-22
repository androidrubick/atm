package pub.androidrubick.autotest.android

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.android.tasks.AndroidTaskGraph
import pub.androidrubick.autotest.android.tasks.other.DumpUITask
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.BaseATMPlugin

/**
 * <p>
 * Created by Yin Yong on 2018/3/8.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GrMethodMayBeStatic")
public class AndroidPlugin extends BaseATMPlugin {

    @Override
    protected void applyMe(@NonNull Project project, @NonNull ATM atm) {
        AndroidSdk.attach(project)
        AndroidTaskGraph.attach(project)

        project.with {
            tasks.create('dumpUI', DumpUITask.class).dependsOn(AndroidTaskGraph.TASK_COLLECT_ANDROID_DEVICE)
        }
    }

}
