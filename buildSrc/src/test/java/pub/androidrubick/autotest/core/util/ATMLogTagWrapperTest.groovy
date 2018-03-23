package pub.androidrubick.autotest.core.util

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.ATMPlugin

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/22.
 */
class ATMLogTagWrapperTest extends GroovyTestCase {
    void testLog() {
        testPrint1(1, 2)
        testPrint1([1, 2].toArray())

        testPrint(1, 2)
        println testPrint2()

        Project project = ProjectBuilder.builder().withName('yytest').build()
        project.pluginManager.apply(ATMPlugin)

        ATM atm = ATM.fromProject(project)

    }

    void testPrint(Object... messages) {
        def arr = []
        arr.addAll(messages)
        arr << 3
        testPrint1(arr.toArray())
    }

    void testPrint1(Object... messages) {
        messages.each {
            println it
        }
    }

    Object[] testPrint2() {
        return [1]
    }
}
