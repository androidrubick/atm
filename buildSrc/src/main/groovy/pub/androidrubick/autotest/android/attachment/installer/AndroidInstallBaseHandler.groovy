package pub.androidrubick.autotest.android.attachment.installer

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.InstallInfo
import pub.androidrubick.autotest.core.ATMContext

/**
 * 定义android_sdk命令行调用UI相关的方法
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GrMethodMayBeStatic"])
public class AndroidInstallBaseHandler extends BaseAndroidAttachment implements AndroidInstallHandler {

    /**
     * 根据安装包的大小预测安装执行的最大时间（预留尽量多的时间，以免中途有其他奇奇怪怪的检查操作）
     * @param file 文件路径
     * @return 最大时间
     */
    public static long predictInstallDuration(File file) {
        // 5M - 15s, 每增加1M，增加0.5s
        // 10M - 25s, 每增加1M，增加0.5s
        def duration = 25
        def fileSize = null == file ? 0 : file.length()
        fileSize /= 1024f
        fileSize /= 1024f
        int fileSizeInt = (int) (fileSize + 0.5)
        if (fileSizeInt < 5) {
            return (long) duration
        }
        if (fileSizeInt < 10) {
            return (long) (duration + (fileSizeInt - 5))
        }
        return (long) (duration + (fileSizeInt - 10))
    }

    public AndroidInstallBaseHandler(ATMContext context) {
        super(context)
    }

    // 该方法不同的机型需要进行部分适配
    /**
     * 执行命令，安装手机/模拟器端的文件
     *
     * @param remoteFile 手机/模拟器端的文件
     * @param localFile 本地的源文件，可以不提供
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    protected void installRemote(File remoteFile, File localFile = null) {
        // 执行安装
        try {
            performOnBeforeInstallRemote(localFile)
            adbInstall(remoteFile, localFile)
            performOnAdbInstallSuccess(localFile)
        } catch(Throwable e) {
            // 删除临时安装文件
            androidSdk.adbUtil.rmRemoteTmpFiles()
            performOnAdbInstallFailed(e)
        }

        // 等待几秒，确保安装后的一些状态
        atm.logI('sleep for installing...')
        sleep(5000)

        // 删除临时安装文件
        androidSdk.adbUtil.rmRemoteTmpFiles()

        performOnAfterInstallStable(localFile)
    }

    @Override
    public void installLocal(File localFile) {
        def remoteApkFile = androidSdk.adbUtil.remoteTmpFile()
        androidSdk.adb.pushFile(localFile, remoteApkFile)
        installRemote(remoteApkFile, localFile)
    }

    protected final void adbInstall(File remoteFile, File localFile = null) {
        def installResult = androidSdk.adbShell.pm.install(InstallInfo.fromFile(remoteFile))
                .timeout(predictInstallDuration(localFile)).exec()
        installResult.checkNonEmptyText('adbInstall')
        def successFlag = installResult.text.split('\n').find { text ->
            text.matches(/(?i).*success.*/)
        }
        atm.preds.isTrue(successFlag != null, 'adbInstall <installed?>')
    }

    protected void performOnBeforeInstallRemote(File localFile) {
        this.mDoBeforeInstallRemoteActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(localFile)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    protected void performOnAdbInstallSuccess(File localFile) {
        this.mAdbInstallSuccessActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(localFile)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    /**
     * just throw param <code>e</code>
     * @param e any exception
     */
    protected void performOnAdbInstallFailed(Throwable e) {
        throw e
    }

    protected void performOnAfterInstallStable(File localFile) {
        this.mDoAfterInstallStableActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(localFile)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    private List<Closure> mDoBeforeInstallRemoteActions = new ArrayList()
    public void doBeforeInstallRemote(Closure closure) {
        mDoBeforeInstallRemoteActions.add(closure)
    }

    private List<Closure> mAdbInstallSuccessActions = new ArrayList()
    public void doOnAdbInstallSuccess(Closure closure) {
        mAdbInstallSuccessActions.add(closure)
    }

    private List<Closure> mDoAfterInstallStableActions = new ArrayList()
    public void doAfterInstallStable(Closure closure) {
        mDoAfterInstallStableActions.add(closure)
    }
}