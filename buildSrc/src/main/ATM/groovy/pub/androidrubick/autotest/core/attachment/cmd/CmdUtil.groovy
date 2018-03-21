package pub.androidrubick.autotest.core.attachment.cmd

import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment

@SuppressWarnings("GroovyUnusedDeclaration")
class CmdUtil extends BaseAttachment {

    CmdUtil(ATMContext context) {
        super(context)
    }

    /**
     * 调用该方法时，外部需要调用exec()方法，才开始执行
     */
    public ExecProcBuilder builder(String... commands) {
        return ExecProcBuilder.by(context, commands)
    }

    /**
     * @param commands string array commands
     * @return a map, like "{code: 0, text: '', err: '', success: true}"
     */
    public ExecResult exec(String... commands) {
        return builder(commands).exec()
    }

    /**
     * @param commands string array commands, without any timeout settings
     * @return a map, like "{code: 0, text: '', err: '', success: true}"
     */
    public ExecResult execn(String... commands) {
        return builder(commands).noTimeout().exec()
    }

    /**
     * @param commands string array commands, with specific timeout
     * @return a map, like "{code: 0, text: '', err: '', success: true}"
     */
    public ExecResult exect(long timeout, String... commands) {
        return builder(commands).timeout(timeout).exec()
    }
}