package pub.androidrubick.autotest.core

import android.support.annotation.NonNull

import org.gradle.api.Project
import pub.androidrubick.autotest.core.attachment.cmd.CmdUtil
import pub.androidrubick.autotest.core.attachment.preds.PreconditionUtil
import pub.androidrubick.autotest.core.attachment.property.PropertyUtil
import pub.androidrubick.autotest.core.util.ATMLog
import pub.androidrubick.autotest.core.util.ATMLogLevel

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
public class ATM {

    public static final String INJECT_NAME = "atm"

    public static ATM init(@NonNull Project myProject) {
        Project rootProject = BaseATMPlugin.getRootProject(myProject)

        if (rootProject.extensions.findByName(INJECT_NAME) != null) {
            return fromProject(myProject)
        }

        ATMLog.init(rootProject)

        rootProject.allprojects { project ->
            ATM myAtm = project.extensions.create(INJECT_NAME, ATM, project)
//            project.ext."$INJECT_NAME" = new ATM(project)
            myAtm.log("$INJECT_NAME of project `${project.name}` initialized")
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

    private final ATMContext mContext
    private ATMLogLevel mLogLevel = ATMLogLevel.Debug

    public final CmdUtil cmd
    public final PropertyUtil prop
    public final PreconditionUtil preds

    /**
     * log debug
     */
    public final log
    public final logD
    public final logI
    public final logW
    public final logE
    ATM(@NonNull Project project) {
        mContext = new ATMContext(project)

        this.logD = createLogClosure(ATMLogLevel.Debug)
        this.logI = createLogClosure(ATMLogLevel.Info)
        this.logW = createLogClosure(ATMLogLevel.Warn)
        this.logE = createLogClosure(ATMLogLevel.Error)
        this.log = this.logD

        this.cmd = new CmdUtil(this.mContext)
        this.prop = new PropertyUtil(this.mContext)
        this.preds = new PreconditionUtil(this.mContext)
    }

    public final ATMContext getContext() {
        return mContext
    }

    public final void setLogLevel(level) {
        if (level instanceof ATMLogLevel) {
            this.mLogLevel = level
        } else {
            def l = ATMLogLevel.parse(String.valueOf(level))
            if (l != null) {
                this.mLogLevel = l
            }
        }
    }

    public final ATMLogLevel getLogLevel() {
        return this.mLogLevel
    }

    private createLogClosure(@NonNull ATMLogLevel level) {
        return { Object... msgs ->
            if (logLevel.isEnabled(level)) {
                ATMLog.fromContext(context).log(level, msgs)
            }
        }
    }
}
