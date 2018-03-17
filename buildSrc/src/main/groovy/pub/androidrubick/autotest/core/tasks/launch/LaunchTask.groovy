package pub.androidrubick.autotest.core.tasks.launch

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.core.BaseATMTask

@SuppressWarnings("GroovyUnusedDeclaration")
class LaunchTask extends BaseATMTask {
    static final String GROUP_LAUNCH = 'launch ATM'

    LaunchTask() {
        group = GROUP_LAUNCH
    }

    @TaskAction
    void launch() {

    }
}