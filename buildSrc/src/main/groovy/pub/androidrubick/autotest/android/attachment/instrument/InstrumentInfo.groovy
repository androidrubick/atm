package pub.androidrubick.autotest.android.attachment.instrument

@SuppressWarnings("GroovyUnusedDeclaration")
public class InstrumentInfo {

    public final String pkg
    public final String instrumentationClz
    public final String targetPkg

    public InstrumentInfo(String pkg, String instrumentationClz, String targetPkg) {
        this.pkg = pkg
        this.instrumentationClz = instrumentationClz
        this.targetPkg = targetPkg
    }

    @Override
    public String toString() {
        return "InstrumentInfo{" +
                "pkg='" + pkg + '\'' +
                ", instrumentationClz='" + instrumentationClz + '\'' +
                ", targetPkg='" + targetPkg + '\'' +
                '}';
    }
}