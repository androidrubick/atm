package android.net

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/15.
 */
class UriTest extends GroovyTestCase {

    void test() {
        println Uri.fromFile(new File('.'))
        println Uri.parse('http://www.baidu.com/home')
        println Uri.parse('http://www.baidu.com/home').scheme
        println Uri.parse('http://www.baidu.com/home').authority
        println Uri.parse('http://www.baidu.com/home?q=%20%3C#anchor').path
        println Uri.parse('http://www.baidu.com/home?q=%20%3C#anchor').query
        println Uri.parse('http://www.baidu.com/home?q=%20%3C#anchor').queryParameterNames
        println Uri.parse('http://www.baidu.com/home?q=%20%3C#anchor').fragment
    }

}
