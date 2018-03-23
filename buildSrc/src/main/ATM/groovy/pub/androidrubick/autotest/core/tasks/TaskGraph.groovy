package pub.androidrubick.autotest.core.tasks

import org.gradle.api.Task

@SuppressWarnings("GroovyUnusedDeclaration")
public interface TaskGraph {

    public List<Task> getAllTasks()

}