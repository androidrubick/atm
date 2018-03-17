package pub.androidrubick.autotest.core;

import android.support.annotation.NonNull;

import org.gradle.api.Project
import pub.androidrubick.autotest.core.util.ATMLog
import pub.androidrubick.autotest.core.util.ATMLogLevel;

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

    public static final String INJECT_NAME = "atm";

    public static ATM init(@NonNull Project project) {
        ATM atm;
        if ((atm = (ATM) project.extensions.findByName(INJECT_NAME)) != null) {
            return atm;
        }

        ATMLog.init(project.rootProject)

        atm = project.extensions.create(INJECT_NAME, ATM, project);
//        project.ext."$INJECT_NAME" = new ATM(project)
        project.with {
            atm.log("$INJECT_NAME initialized")
        }
        return atm;
    }

    private final ATMContext mContext
    private ATMLogLevel mLogLevel = ATMLogLevel.Debug

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
