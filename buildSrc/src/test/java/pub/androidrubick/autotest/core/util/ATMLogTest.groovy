package pub.androidrubick.autotest.core.util

import android.util.Log
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import pub.androidrubick.autotest.core.ATMPlugin

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/17.
 */
class ATMLogTest extends GroovyTestCase {

    private static String aaa() {
        return 'aaa'
    }

    private static aaa = [aaa:'1']

    void test() {
        Project project = ProjectBuilder.builder().withName('test')
                .build()

        project.pluginManager.apply(ATMPlugin)
//        ATMLog.init(project)

        Log.e("yytest", "hahaha")
        Log.e("yytest", '1 2\n3\n4\n5 6')
        Log.e("yytest", '1 2\n3\n4\n5 6', new Throwable())
        Log.v("yytest", '1 2\n3\n4\n5 6')

        Log.i("yytest", aaa())
        Log.i("yytest", aaa.aaa)

        println new File('./').absolutePath
    }

}
