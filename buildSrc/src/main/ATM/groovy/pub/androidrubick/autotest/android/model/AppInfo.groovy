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
    public int versionCode

    public ComponentName launchInfo

    public AppInfo() {}

    public AppInfo(String pkg, ComponentName launchInfo = null, String versionName = '1.0.0', int versionCode = 1) {
        this.pkg = pkg
        this.launchInfo = launchInfo
        this.versionName = versionName
        this.versionCode = versionCode
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