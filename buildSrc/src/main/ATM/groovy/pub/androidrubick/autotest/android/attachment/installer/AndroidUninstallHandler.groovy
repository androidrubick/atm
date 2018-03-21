package pub.androidrubick.autotest.android.attachment.installer

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.model.UninstallInfo

/**
 * Android卸载处理接口
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public interface AndroidUninstallHandler {

    /**
     * 执行命令，卸载应用
     *
     * @param pkg 应用包名
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     *
     * @since 1.0.0
     */
    public abstract void uninstall(@NonNull UninstallInfo uninstallInfo)

}