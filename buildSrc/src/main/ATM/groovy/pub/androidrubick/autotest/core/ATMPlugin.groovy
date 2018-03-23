package pub.androidrubick.autotest.core

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.core.tasks.ATMTaskGraph

/**
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "WeakerAccess"])
public class ATMPlugin extends BaseATMPlugin {

    @Override
    protected void applyMe(@NonNull Project project, @NonNull ATM atm) {
        ATMTaskGraph taskGraph = ATMTaskGraph.attach(project)
        atm.log("ATM task graph: ${taskGraph.allTasks}")
    }

}
