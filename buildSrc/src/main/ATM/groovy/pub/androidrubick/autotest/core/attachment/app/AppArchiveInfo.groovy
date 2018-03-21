package pub.androidrubick.autotest.core.attachment.app

import android.support.annotation.NonNull

/**
 * 定义应用包类型信息；
 *
 * 如安卓，包含应用程序包和测试程序包；
 *
 * iOS，包含ipa应用程序包
 *
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AppArchiveInfo {

    private final AppArchiveType mType
    private final File mFilePath
    public AppArchiveInfo(@NonNull AppArchiveType type, @NonNull File filePath) {
        mType = type
        mFilePath = filePath
    }

    @NonNull
    public String getType() {
        return mType
    }

    @NonNull
    public File getFilePath() {
        return mFilePath
    }

    @Override
    public String toString() {
        return "AppArchiveInfo{" +
                "mType=" + mType +
                ", mFilePath='" + mFilePath + '\'' +
                '}';
    }
}