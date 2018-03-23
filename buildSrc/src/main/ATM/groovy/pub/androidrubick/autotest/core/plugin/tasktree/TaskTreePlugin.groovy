package pub.androidrubick.autotest.core.plugin.tasktree

import android.support.annotation.NonNull
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.tooling.UnsupportedVersionException
import org.gradle.util.GradleVersion
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.BaseATMPlugin

/**
 * Base task used in this lib.
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "WeakerAccess", "GrMethodMayBeStatic"])
public class TaskTreePlugin extends BaseATMPlugin {

    public static final String TASK_TREE_TASK_NAME = 'taskTree'
    public static String GRADLE_MINIMUM_SUPPORTED_VERSION = '2.3'
    public static String UNSUPPORTED_GRADLE_VERSION_MESSAGE =
            "The ATM plugin cannot be run on a gradle version older than ${GRADLE_MINIMUM_SUPPORTED_VERSION}"

    @Override
    protected void applyMe(@NonNull Project project, @NonNull ATM atm) {
        if (!isRootProject(project)) {
            return
        }
        Project rootProject = project

        if (rootProject.tasks.findByName(TASK_TREE_TASK_NAME)) {
            // Skip if this sub-project already has our task. This can happen for example if the plugin is applied on allProjects.
            return
        }

        rootProject.task(TASK_TREE_TASK_NAME, type: TaskTreeTask)

        rootProject.gradle.taskGraph.whenReady {
            def allTasks = rootProject.gradle.taskGraph.allTasks
            if (allTasks.any { Task task -> task instanceof TaskTreeTask }) {
                atm.logI("Task <$TASK_TREE_TASK_NAME> exists")
                if (allTasks.size() > 1) {
                    allTasks.findAll {
                        Task task -> !(task instanceof TaskTreeTask)
                    }?.each { Task task ->
                        task.setEnabled(false)
                    }
                }
            }
        }
    }

    @Override
    protected void beforeApply(@NonNull Project project, @NonNull ATM atm) {
        super.beforeApply(project, atm)
        validateGradleVersion()
    }

    private void validateGradleVersion() {
        if (GradleVersion.current() < GradleVersion.version(GRADLE_MINIMUM_SUPPORTED_VERSION)) {
            throw new UnsupportedVersionException(UNSUPPORTED_GRADLE_VERSION_MESSAGE)
        }
    }
}
