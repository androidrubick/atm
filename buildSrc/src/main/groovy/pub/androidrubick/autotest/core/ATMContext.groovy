package pub.androidrubick.autotest.core

import org.gradle.api.Project

@SuppressWarnings("GroovyUnusedDeclaration")
public class ATMContext {

    private final Project project

    ATMContext(Project project) {
        this.project = project
    }

    public final Project getProject() {
        return this.project
    }

}