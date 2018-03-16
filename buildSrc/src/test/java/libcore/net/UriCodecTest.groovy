package libcore.net

import java.nio.charset.StandardCharsets

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/15.
 */
class UriCodecTest extends GroovyTestCase {

    void test() {
        println UriCodec.decode('%3C')
        println new UriCodec() {
            @Override
            protected boolean isRetained(char c) {
                return true
            }
        }.encode(' ', StandardCharsets.UTF_8)

        println new UriCodec() {
            @Override
            protected boolean isRetained(char c) {
                return false
            }
        }.encode(' ', StandardCharsets.UTF_8)
    }

}
