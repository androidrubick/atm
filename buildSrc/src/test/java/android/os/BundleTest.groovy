package android.os

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/15.
 */
class BundleTest extends GroovyTestCase {

    void testForPair() {
        assertToString(Bundle.forPair('a', 1), 'Bundle[{a=1}]')
    }

    void testWrite() {
        assertToString(new Bundle()
                .put('a', 1)
                .put('b', '2')
                .putAll(Bundle.forPair('a', 'a')), 'Bundle[{a=a, b=2}]')

        assertToString(new Bundle()
                .put('a', 1)
                .put('b', '2')
                .putAll(Bundle.forPair('c', 'c'))
                .remove('c'), 'Bundle[{a=1, b=2}]')

        assertToString(new Bundle()
                .put('a', 1)
                .put('b', '2')
                .putAll(Bundle.forPair('a', 'a'))
                .clear(), 'Bundle[{}]')
    }

    void testGet() {
        assertToString(new Bundle().put('a', 1)
                .put('b', '2')
                .putAll(Bundle.forPair('a', 'a'))
                .get('a'), 'a')

        assertToString(new Bundle().put('a', 1)
                .put('b', '2')
                .putAll(Bundle.forPair('a', 'a'))
                .getInt('c', 1), '1')

        assertToString(new Bundle().put('a', 1)
                .put('b', '2')
                .putAll(Bundle.forPair('c', 1))
                .getInt('c', 2), '1')
    }
}
