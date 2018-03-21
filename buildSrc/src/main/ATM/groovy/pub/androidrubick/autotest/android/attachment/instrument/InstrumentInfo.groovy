package pub.androidrubick.autotest.android.attachment.instrument

import android.content.ComponentName
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

    public ComponentName asComponent() {
        return ComponentName.createRelative(pkg, instrumentationClz)
    }

    @Override
    public String toString() {
        return "InstrumentInfo{" +
                "appInfo='" + appInfo + '\'' +
                ", instrumentationClz='" + instrumentationClz + '\'' +
                ", targetPkg='" + targetPkg + '\'' +
                '}'
    }
}