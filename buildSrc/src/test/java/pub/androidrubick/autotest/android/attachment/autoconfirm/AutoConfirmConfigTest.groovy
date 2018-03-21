package pub.androidrubick.autotest.android.attachment.autoconfirm

import org.gradle.util.ConfigureUtil

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/21.
 */
class AutoConfirmConfigTest extends GroovyTestCase {
    void testNewConfig() {

        Map map = [test: 'a', yy: 1]

        A a = ConfigureUtil.configureByMap(map, new A())
        println a
    }

    class A {
        String test
        int yy

        @Override
        public String toString() {
            return "A{" +
                    "test='" + test + '\'' +
                    ", yy=" + yy +
                    '}';
        }
    }
}
