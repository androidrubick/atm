package pub.androidrubick.autotest.android.model

import android.support.annotation.NonNull

/**
 * simple model with app information
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class UninstallInfo implements Cmdable {
//    从系统中移除软件包。
//    选项：
//
//    -k：移除软件包后保留数据和缓存目录。

    public static UninstallInfo withPkg(@NonNull String pkg) {
        return new UninstallInfo(pkg)
    }

    private final String mPkg
    private boolean mKeepData = false

    public UninstallInfo(@NonNull String pkg) {
        mPkg = pkg
    }

    /**
     * 默认为true
     */
    public UninstallInfo setKeepData(boolean value) {
        mKeepData = value
        return this
    }

    @Override
    public String toCmdString() {
        def cmd = []
        if (mKeepData) {
            cmd << '-k'
        }
        cmd << mPkg
        return cmd.join(' ')
    }

    @Override
    public String toString() {
        return "UninstallInfo{" +
                "mPkg='" + mPkg + '\'' +
                ", mKeepData=" + mKeepData +
                '}';
    }
}