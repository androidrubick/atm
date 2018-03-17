package pub.androidrubick.autotest.core.tasks.install

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.core.BaseATMTask

@SuppressWarnings("GroovyUnusedDeclaration")
class InstallTask extends BaseATMTask {

    static final String GROUP_INSTALL = 'install ATM'

    InstallTask() {
        group = GROUP_INSTALL
    }

    @TaskAction
    void install() {

    }
}