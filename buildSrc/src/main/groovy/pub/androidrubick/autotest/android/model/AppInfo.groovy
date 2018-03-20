package pub.androidrubick.autotest.android.model

import android.content.ComponentName

/**
 * simple model with app information
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AppInfo {

    public String pkg
    public String versionName
    public String versionCode

    public ComponentName launchInfo

    public AppInfo() {}

    public AppInfo(String pkg, String versionName, String versionCode, ComponentName launchInfo = null) {
        this.pkg = pkg
        this.versionName = versionName
        this.versionCode = versionCode
        this.launchInfo = launchInfo
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "pkg='" + pkg + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", launchInfo=" + launchInfo +
                '}';
    }
}