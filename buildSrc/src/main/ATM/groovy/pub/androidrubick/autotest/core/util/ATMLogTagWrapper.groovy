package pub.androidrubick.autotest.core.util

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.IATM
import pub.androidrubick.autotest.core.attachment.cmd.CmdUtil
import pub.androidrubick.autotest.core.attachment.preds.PreconditionUtil
import pub.androidrubick.autotest.core.attachment.property.PropertyUtil

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
public class ATMLogTagWrapper extends ATM implements IATM {

    private final IATM mBase
    private final Object mLogTag
    ATMLogTagWrapper(@NonNull IATM base, Object tag) {
        super(base.context.project)
        mBase = base
        mLogTag = tag
    }

    @Override
    void log(Object msg) {
        mBase.log(prependTag(msg))
    }

    @Override
    void logD(Object msg) {
        mBase.logD(prependTag(msg))
    }

    @Override
    void logI(Object msg) {
        mBase.logI(prependTag(msg))
    }

    @Override
    void logW(Object msg) {
        mBase.logW(prependTag(msg))
    }

    @Override
    void logE(Object msg) {
        mBase.logE(prependTag(msg))
    }

    @Override
    void setLogLevel(ATMLogLevel level) {
        mBase.setLogLevel(level)
    }

    @Override
    ATMLogLevel getLogLevel() {
        return mBase.getLogLevel()
    }

    @Override
    ATMContext getContext() {
        return mBase.getContext()
    }

    @Override
    CmdUtil getCmd() {
        return mBase.getCmd()
    }

    @Override
    PropertyUtil getProp() {
        return mBase.getProp()
    }

    @Override
    PreconditionUtil getPreds() {
        return mBase.getPreds()
    }

    protected Object prependTag(Object msg) {
        return [mLogTag, msg].join(' ')
    }
}
