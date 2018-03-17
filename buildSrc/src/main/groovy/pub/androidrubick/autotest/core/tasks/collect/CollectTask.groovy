package pub.androidrubick.autotest.core.tasks.collect

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.core.BaseATMTask

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class CollectTask extends BaseATMTask {

    static final String GROUP_COLLECT = 'collect ATM'

    CollectTask() {
        group = GROUP_COLLECT
    }

    @TaskAction
    void collect() {

    }
}