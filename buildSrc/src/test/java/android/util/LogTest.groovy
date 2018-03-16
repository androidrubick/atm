package android.util

import android.os.Bundle

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/15.
 */
class LogTest extends GroovyTestCase {

    void test() {
        Log.v("yy", "message")
        Log.i("yytest", "message")
        Log.d("yytestyytest", "message")
        Log.w("yytestyytestyytest", "message")
        Log.e("yytestyytestyytestyytest", "message")
        Log.e("yytestyytestyytestyytest", Bundle.forPair('a', "message $LogTest").get('a').class.toString())

    }

}
