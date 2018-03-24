package pub.androidrubick.autotest.core.plugin.tasktree

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.diagnostics.AbstractReportTask
import org.gradle.api.tasks.diagnostics.internal.ReportRenderer
import org.gradle.api.tasks.diagnostics.internal.TextReportRenderer
import org.gradle.internal.logging.text.StyledTextOutput
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.BaseATMPlugin
import pub.androidrubick.autotest.core.tasks.BaseATMTask

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedAssignment"])
public class TaskTreeTask extends AbstractReportTask {

    private final ATM mATM
    private TextReportRenderer mRenderer = new TextReportRenderer()
    public TaskTreeTask() {
        mATM = ATM.wrapped(project, "Task $name:")
    }

    /**
     * provide `atm` for tasks
     */
    public final ATM getAtm() {
        return mATM
    }

    @Override
    protected ReportRenderer getRenderer() {
        return mRenderer
    }

    @Override
    protected void generate(Project project) throws IOException {
        // textOutput is injected and set into renderer by the parent abstract class before this method is called
        // define textOutput as a dynamic type because it resides in different packages in different gradle versions
        def textOutput = mRenderer.textOutput

        Project rootProject = BaseATMPlugin.getRootProject(project)

        // 如果执行的所有任务中除了taskTree，还有其他的task，则只打印指定的任务的依赖；
        // 如果执行的所有任务中除了taskTree，没有其他的task，则打印所有的ATM plugin相关的任务的依赖
        def allTasks = rootProject.gradle.taskGraph.allTasks
        if (allTasks.size() == 1) { // 只有taskTree
            def allNonTaskTreeTaskSet = new HashSet()
            rootProject.allprojects { p ->
                allNonTaskTreeTaskSet.addAll(p.tasks.findAll {
                    Task task -> task instanceof BaseATMTask
                })
            }
            allTasks = allNonTaskTreeTaskSet.asList()
        }
        allTasks = allTasks.findAll {
            Task task -> !(task instanceof TaskTreeTask)
        }.sort { l, r ->
            l.path <=> r.path
        }

        printTasks(allTasks, textOutput)

        atm.log("Prepare print task tree of: $allTasks")
    }

    private void printTasks(List<Task> tasks, StyledTextOutput textOutput) {
        printTasks(tasks, new GradleReportTextOutput(atm.context, textOutput))
    }

    /**
     * use atm log
     */
    private void printTasks(List<Task> tasks) {
        printTasks(tasks, new ATMTaskTreeTextOutput(atm.context))
    }

    private void printTasks(List<Task> tasks, TaskTreeTextOutput textOutput) {
        tasks.each {
            render(it, new TaskTreePrinter(textOutput), true, true, new HashSet<>())
            textOutput.println()
        }
    }

    protected void render(Object entry, TaskTreePrinter renderer, boolean lastChild, boolean isFirst, Set rendered) {
        final boolean taskSubtreeAlreadyPrinted = rendered.add(entry)
        def displayName = entry instanceof Task ? entry.path : String.valueOf(entry)
        renderer.visit({ textOutput ->
            textOutput.printTaskName(displayName, isFirst)
        }, lastChild)

        if (entry instanceof Task) {
            def entryTask = entry as Task
            renderer.startChildren()
            Set<Object> children = entryTask.dependsOn
            children.eachWithIndex { Object child, int i ->
                render(child, renderer, i == children.size() - 1, false, rendered)
            }
            renderer.completeChildren()
        }
    }

}