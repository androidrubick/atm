package pub.androidrubick.autotest.core.plugin.tasktree

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskAction
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
@SuppressWarnings("GroovyUnusedDeclaration")
public class TaskTreeTask extends BaseATMTask {

    public TaskTreeTask() {
    }

    @TaskAction
    public void printTaskTree() {
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

        atm.log("allTasks: $allTasks")

        printTasks(allTasks)
    }

    private void printTasks(List<Task> tasks) {
        TextOutput textOutput = new TextOutput()
        tasks.each {
            render(it, new Printer(textOutput), true, true, new HashSet<>())
            textOutput.println()
        }
    }

    protected void render(Object entry, Printer renderer, boolean lastChild, boolean isFirst, Set rendered) {
        final boolean taskSubtreeAlreadyPrinted = rendered.add(entry)
        def displayName = entry instanceof Task ? entry.path : String.valueOf(entry)
        renderer.visit({ textOutput ->
            textOutput.text(displayName)
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

    protected class Printer {
        boolean seenRootChildren
        boolean lastChild = true

        final List<String> mPrefixList = []
        final TextOutput mTextOutput
        public Printer(TextOutput textOutput) {
            mTextOutput = textOutput
        }

        void visit(Action<? extends TextOutput> node, boolean lastChild) {
            if (this.seenRootChildren) {
                mTextOutput.text(this.prefix + (lastChild ? "\\--- " : "+--- "))
            }

            this.lastChild = lastChild
            node.execute(mTextOutput)
            mTextOutput.println()
        }

        public void startChildren() {
            if (this.seenRootChildren) {
                mPrefixList << (this.lastChild ? "     " : "|    ")
            }
            this.seenRootChildren = true;
        }

        public void completeChildren() {
            if (this.prefix.empty) {
                this.seenRootChildren = false;
            } else {
                mPrefixList.pop()
            }
        }

        public String getPrefix() {
            return mPrefixList.join()
        }
    }

    protected class TextOutput {
        final List cachedTexts = []
        public void text(Object text) {
            cachedTexts << text
        }

        public void println() {
            atm.logI(cachedTexts.join())
            cachedTexts.clear()
        }
    }

}