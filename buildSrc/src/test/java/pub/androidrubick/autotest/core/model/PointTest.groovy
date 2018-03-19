package pub.androidrubick.autotest.core.model

import android.util.Log

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/19.
 */
class PointTest extends GroovyTestCase {

    void test() {
        Point p1 = new Point(100, 100)
        Point p2 = new Point(50, 50)

        Log.d("yytest", String.valueOf(p1 - p2))
        Log.d("yytest", String.valueOf(p1 + p2))
        Log.d("yytest", String.valueOf(-p1))
    }
}
