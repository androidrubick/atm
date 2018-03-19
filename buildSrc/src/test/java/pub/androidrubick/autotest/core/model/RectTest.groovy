package pub.androidrubick.autotest.core.model

import android.util.Log

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/19.
 */
class RectTest extends GroovyTestCase {
    void testContains() {

    }

    void testPlus() {
        Rect rect1 = new Rect(0, 0, 100, 100)
        Rect rect2 = new Rect(50, 50, 100, 100)

        assertEquals((rect1 + rect2), rect1)
    }

    void testNegative() {
        Rect rect = new Rect(50, 50, 100, 100)

        Log.d("yytest", rect.toString())
        Log.d("yytest", (-rect).toString())
    }

    void testIntersects() {
        Rect rect1 = new Rect(0, 0, 100, 100)
        Rect rect2 = new Rect(50, 50, 100, 100)
        Rect rect3 = new Rect(0, 0, 50, 50)

        assertTrue(rect1.intersects(rect2))
        assertTrue(rect1.intersects(rect3))
    }
}
