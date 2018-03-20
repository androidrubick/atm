package pub.androidrubick.autotest.core.tasks

import org.gradle.api.Project
import org.gradle.api.Task

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
abstract class TaskHelper {

    public static Task getOrCreate(Project project, String name) {
        def task = project.tasks.findByName(name)
        if (task == null) {
            task = project.tasks.create(name)
        }
        return task
    }

}