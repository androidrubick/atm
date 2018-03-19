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
        return "InstrumentTestClz{" +
                "clzName='" + clzName + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}