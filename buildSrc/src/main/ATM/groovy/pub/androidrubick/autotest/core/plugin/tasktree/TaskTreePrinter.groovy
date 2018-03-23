package pub.androidrubick.autotest.core.plugin.tasktree

import org.gradle.api.Action

/**
 * Base task used in this lib.
 *
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedAssignment"])
public class TaskTreePrinter {

    protected boolean seenRootChildren
    protected boolean lastChild = true

    protected final List<String> mPrefixList = []
    protected final TaskTreeTextOutput mTextOutput
    public TaskTreePrinter(TaskTreeTextOutput textOutput) {
        mTextOutput = textOutput
    }

    public void visit(Action<? extends TaskTreeTextOutput> node, boolean lastChild) {
        if (this.seenRootChildren) {
            mTextOutput.printGraph(this.prefix + (lastChild ? "\\--- " : "+--- "))
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