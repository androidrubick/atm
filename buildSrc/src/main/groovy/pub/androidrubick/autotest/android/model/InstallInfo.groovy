package pub.androidrubick.autotest.android.model

import android.support.annotation.NonNull

/**
 * simple model with app information
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class InstallInfo implements Cmdable {

//    将软件包（通过 path 指定）安装到系统。
//    选项：
//
//    -l：安装具有转发锁定功能的软件包。
//    -r：重新安装现有应用，保留其数据。
//    -t：允许安装测试 APK。
//    -i installer_package_name：指定安装程序软件包名称。
//    -s：在共享的大容量存储（如 sdcard）上安装软件包。
//    -f：在内部系统内存上安装软件包。
//    -d：允许版本代码降级。
//    -g：授予应用清单中列出的所有权限。// since

    public static InstallInfo fromFile(@NonNull File remoteFile) {
        return new InstallInfo(remoteFile)
    }

    private final File mFile
    private boolean mReplaceExisting = true
    private boolean mTestEnabled = true
    private boolean mDowngradeEnabled = true
    private boolean mGrantPermissions = true

    public InstallInfo(@NonNull File file) {
        mFile = file
    }

    /**
     * 默认为true
     */
    public InstallInfo setReplaceExisting(boolean value) {
        mReplaceExisting = value
        return this
    }

    /**
     * 默认为true
     */
    public InstallInfo setTestEnabled(boolean value) {
        mTestEnabled = value
        return this
    }

    /**
     * 默认为true
     */
    public InstallInfo setDowngradeEnabled(boolean value) {
        mDowngradeEnabled = value
        return this
    }

    /**
     * 默认为true
     */
    public InstallInfo setGrantPermissions(boolean value) {
        mGrantPermissions = value
        return this
    }

    @Override
    public String toCmdString() {
        def cmd = []
        if (mReplaceExisting) {
            cmd << '-r'
        }
        if (mTestEnabled) {
            cmd << '-t'
        }
        if (mDowngradeEnabled) {
            cmd << '-d'
        }
        // adb 25.0.2 尚未支持
//        if (mGrantPermissions) {
//            cmd << '-g'
//        }
        cmd << mFile.absolutePath
        return cmd.join(' ')
    }

    @Override
    public String toString() {
        return "InstallInfo{" +
                "mFile=" + mFile +
                ", mReplaceExisting=" + mReplaceExisting +
                ", mTestEnabled=" + mTestEnabled +
                ", mDowngradeEnabled=" + mDowngradeEnabled +
                ", mGrantPermissions=" + mGrantPermissions +
                '}';
    }
}