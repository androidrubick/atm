package pub.androidrubick.autotest.android.attachment.instrument

import pub.androidrubick.autotest.core.util.Utils

@SuppressWarnings("GroovyUnusedDeclaration")
public class InstrumentTestClz {

    public final String clzName
    public final String method
    public InstrumentTestClz(String clzName, String method = null) {
        this.clzName = clzName
        this.method = method
    }

    public final String getFullName() {
        def nameArr = []
        nameArr << this.clzName
        if (!Utils.isEmpty(this.method)) {
            nameArr << this.method
        }
        return nameArr.join('#')
    }

    @Override
    public String toString() {
        return fullName
    }

    /**
     * @param name 形如 'your.class' 或者 'your.class#your_method'
     */
    public static InstrumentTestClz parse(String name) {
        if (Utils.isEmpty(name)) {
            return new InstrumentTestClz('')
        }
        def clz = name
        def method = null
        int index = name.lastIndexOf('#')
        if (index >= 0) {
            clz = name.substring(0, index)
            method = name.substring(index + 1)
        }
        return new InstrumentTestClz(clz, method)
    }

}