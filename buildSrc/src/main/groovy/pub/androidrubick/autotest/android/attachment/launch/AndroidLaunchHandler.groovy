package pub.androidrubick.autotest.android.attachment.launch

import android.content.Intent

/**
 * Android启动APP的处理接口
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public interface AndroidLaunchHandler {

    /**
     * 根据已有的启动app的信息，启动机器/模拟机上的应用
     *
     * @param intent 包含 `pkg` 和 `mainActivity`字段
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public abstract void launch(Intent intent)

}