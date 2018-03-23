package pub.androidrubick.autotest.core

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.core.attachment.cmd.CmdUtil
import pub.androidrubick.autotest.core.attachment.preds.PreconditionUtil
import pub.androidrubick.autotest.core.attachment.property.PropertyUtil
import pub.androidrubick.autotest.core.util.ATMLog
import pub.androidrubick.autotest.core.util.ATMLogLevel
import pub.androidrubick.autotest.core.util.ATMLogTagWrapper

/**
 * 当 apply 任何插件时，会尝试向{@link Project project}注入名为`atm`的对象；
 *
 * 包含一些基础操作、工具，比如日志，
 *
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public abstract class ATM implements IATM {

    public static final String INJECT_NAME = "atm"

    public static ATM init(@NonNull Project myProject) {
        Project rootProject = BaseATMPlugin.getRootProject(myProject)

        if (rootProject.extensions.findByName(INJECT_NAME) != null) {
            return fromProject(myProject)
        }

        ATMLog.init(rootProject)

        rootProject.allprojects { project ->
            ATM myAtm = project.extensions.create(INJECT_NAME, ATMImpl.class, project)
//            project.ext."$INJECT_NAME" = new ATM(project)
            myAtm.log("$INJECT_NAME of project <${project.name}> initialized")
        }
        return fromProject(myProject)
    }

    /**
     * get the {@link ATM} implementation from target project
     * @param project target project
     * @return the {@link ATM} implementation
     * @since 1.0.0
     */
    public static ATM fromProject(@NonNull Project project) {
        return project."$INJECT_NAME"
    }

    public static ATM wrapped(ATM base, Object tag) {
        return new ATMLogTagWrapper(base, tag)
    }

    private final ATMContext mContext
    private ATMLogLevel mLogLevel = ATMLogLevel.Debug

    private CmdUtil mCmdUtil
    private PropertyUtil mPropUtil
    private PreconditionUtil mPredsUtil
    ATM(Project project) {
        mContext = new ATMContext(project)
    }

    @Override
    void log(Object msg) {
        logD(msg)
    }

    @Override
    void logD(Object msg) {
        printLog(ATMLogLevel.Debug, msg)
    }

    @Override
    void logI(Object msg) {
        printLog(ATMLogLevel.Info, msg)
    }

    @Override
    void logW(Object msg) {
        printLog(ATMLogLevel.Warn, msg)
    }

    @Override
    void logE(Object msg) {
        printLog(ATMLogLevel.Error, msg)
    }

    protected void printLog(ATMLogLevel level, Object msg) {
        if (logLevel.isEnabled(level)) {
            ATMLog.fromContext(context).log(level, msg)
        }
    }

    @Override
    void setLogLevel(ATMLogLevel level) {
        if (level instanceof ATMLogLevel) {
            this.mLogLevel = level
        }
    }

    void setLogLevel(Object level) {
        this.setLogLevel(ATMLogLevel.parse(String.valueOf(level)))
    }

    @NonNull
    @Override
    ATMLogLevel getLogLevel() {
        return this.mLogLevel
    }

    @Override
    ATMContext getContext() {
        return mContext
    }

    @Override
    CmdUtil getCmd() {
        if (null == mCmdUtil) {
            synchronized (this) {
                if (null == mCmdUtil) {
                    mCmdUtil = new CmdUtil(this.context)
                }
            }
        }
        return mCmdUtil
    }

    @Override
    PropertyUtil getProp() {
        if (null == mPropUtil) {
            synchronized (this) {
                if (null == mPropUtil) {
                    mPropUtil = new PropertyUtil(this.context)
                }
            }
        }
        return mPropUtil
    }

    @Override
    PreconditionUtil getPreds() {
        if (null == mPredsUtil) {
            synchronized (this) {
                if (null == mPredsUtil) {
                    mPredsUtil = new PreconditionUtil(this.context)
                }
            }
        }
        return mPredsUtil
    }

}
