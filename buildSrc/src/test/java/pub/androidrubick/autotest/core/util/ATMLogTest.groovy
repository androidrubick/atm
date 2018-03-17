package pub.androidrubick.autotest.core.util

import android.util.Log
import org.gradle.testfixtures.ProjectBuilder

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/17.
 */
class ATMLogTest extends GroovyTestCase {

    void test() {
        ATMLog.init(ProjectBuilder.builder().withName('test')
                .build())

        Log.e("yytest", "hahaha")
        Log.e("yytest", '1 2\n3\n4\n5 6')
        Log.e("yytest", '1 2\n3\n4\n5 6', new Throwable())
        Log.v("yytest", '1 2\n3\n4\n5 6', new Throwable())
    }

}
