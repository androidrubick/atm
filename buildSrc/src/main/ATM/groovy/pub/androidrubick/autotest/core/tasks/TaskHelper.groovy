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

    /**
     * find task by name from <code>tasks</code>
     * @param tasks task collection
     * @param name name to search
     * @return task fist found from <code>tasks</code>, or null if not found
     */
    public static Task findByName(Iterable<Task> tasks, String name) {
        return tasks?.find { Task task ->
            Objects.equals(task.name, name)
        }
    }

    /**
     * find task by name / path from <code>tasks</code>
     * @param tasks task collection
     * @param path path or name to search
     * @return task fist found from <code>tasks</code>, or null if not found
     */
    public static Task findByPath(Iterable<Task> tasks, String path) {
        if (!path.contains(":")) {
            return findByName(tasks, path)
        } else {
            return tasks?.find { Task task ->
                Objects.equals(task.path, path)
            }
        }
    }

}