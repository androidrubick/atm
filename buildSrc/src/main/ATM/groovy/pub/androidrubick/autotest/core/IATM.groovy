package pub.androidrubick.autotest.core

import org.gradle.api.Project
import pub.androidrubick.autotest.core.attachment.cmd.CmdUtil
import pub.androidrubick.autotest.core.attachment.preds.PreconditionUtil
import pub.androidrubick.autotest.core.attachment.property.PropertyUtil
import pub.androidrubick.autotest.core.util.ATMLoggable

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
public interface IATM extends ATMLoggable {

    public ATMContext getContext()

    public CmdUtil getCmd()
    public PropertyUtil getProp()
    public PreconditionUtil getPreds()

}
