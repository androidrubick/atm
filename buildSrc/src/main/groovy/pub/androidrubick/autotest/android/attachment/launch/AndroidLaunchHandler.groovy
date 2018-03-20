package pub.androidrubick.autotest.android.attachment.launch

import android.content.ComponentName
import android.support.annotation.NonNull

/**
 * Android启动APP的处理接口
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public interface AndroidLaunchHandler {

    /**
     * 根据已有的启动app的信息，启动机器/模拟机上的应用
     *
     * @param component 包含 component或者action等
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public abstract void launch(@NonNull ComponentName component)

}