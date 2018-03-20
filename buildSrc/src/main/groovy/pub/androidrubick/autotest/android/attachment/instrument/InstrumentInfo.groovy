package pub.androidrubick.autotest.android.attachment.instrument

import pub.androidrubick.autotest.android.model.AppInfo

@SuppressWarnings("GroovyUnusedDeclaration")
public class InstrumentInfo {

    public final AppInfo appInfo
    public final String instrumentationClz
    public final String targetPkg

    public InstrumentInfo(AppInfo appInfo, String instrumentationClz, String targetPkg) {
        this.appInfo = appInfo
        this.instrumentationClz = instrumentationClz
        this.targetPkg = targetPkg
    }

    public String getPkg() {
        return appInfo.pkg
    }

    @Override
    public String toString() {
        return "InstrumentInfo{" +
                "appInfo='" + appInfo + '\'' +
                ", instrumentationClz='" + instrumentationClz + '\'' +
                ", targetPkg='" + targetPkg + '\'' +
                '}';
    }
}