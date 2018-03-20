package pub.androidrubick.autotest.android.attachment.installer

/**
 * Android安装处理接口
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public interface AndroidInstallHandler {

    /**
     * 执行命令，安装本地的源文件
     *
     * @param localFile 本地的源文件
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public abstract void installLocal(File localFile)

}