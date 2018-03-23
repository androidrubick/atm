package pub.androidrubick.autotest.core

import android.support.annotation.CallSuper
import android.support.annotation.NonNull
import org.gradle.api.Plugin
import org.gradle.api.Project
import pub.androidrubick.autotest.core.plugin.tasktree.TaskTreePlugin

/**
 * Base task used in this lib.
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "WeakerAccess", "GrMethodMayBeStatic"])
public abstract class BaseATMPlugin implements Plugin<Project> {

    @Override
    public void apply(@NonNull Project project) {
        ATM atm = ATM.init(project)

        project.apply(plugin: TaskTreePlugin)

        beforeApply(project, atm)
        applyMe(project, atm)
        afterApplied(project, atm)
    }

    @CallSuper
    protected void beforeApply(@NonNull Project project, @NonNull ATM atm) {
        atm.logI("${this.class.simpleName} Applying for <${project.name}>")
    }

    protected abstract void applyMe(@NonNull Project project, @NonNull ATM atm)

    @CallSuper
    protected void afterApplied(@NonNull Project project, @NonNull ATM atm) {
        atm.logI("${this.class.simpleName} Applied for <${project.name}>")
    }

    public static boolean isRootProject(@NonNull Project project) {
        return project.rootProject == null || project.rootProject == project
    }

    public static Project getRootProject(@NonNull Project project) {
        return project.rootProject ?: project
    }
}
