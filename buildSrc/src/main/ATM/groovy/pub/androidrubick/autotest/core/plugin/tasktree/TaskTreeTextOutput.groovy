package pub.androidrubick.autotest.core.plugin.tasktree
/**
 *
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedAssignment"])
interface TaskTreeTextOutput {

    abstract void println()

    abstract void printTaskName(Object text, boolean isFirst)

    abstract void printGraph(Object text)

}