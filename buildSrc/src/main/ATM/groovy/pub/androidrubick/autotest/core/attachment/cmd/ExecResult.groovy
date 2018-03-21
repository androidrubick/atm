package pub.androidrubick.autotest.core.attachment.cmd

import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment

@SuppressWarnings("GroovyUnusedDeclaration")
public class ExecResult extends BaseAttachment {

    public final int code
    public final String text
    public final String err

    ExecResult(ATMContext context, int code, String text = null, String err = null) {
        super(context)
        this.code = code
        this.text = text
        this.err = err
    }

    public boolean isSuccess() {
        return code == 0
    }

    @Override
    public String toString() {
        return "ExecResult {\n" +
                "\tcode = " + code + ',\n' +
                "\ttext = '" + text + '\'' + ',\n' +
                "\terr = '" + err + '\'' + '\n' +
                '}';
    }

    /**
     * 检查执行结果是否是成功，如果执行结果为异常，将抛出异常
     * @param tag 申明一个标签，将在抛出异常中携带
     * @return this ExecResult, 为了链式调用
     */
    public ExecResult checkSuccess(String tag) {
        atm.preds.isTrue(this.success, "$tag <success>")
        return this
    }

    /**
     * 检查执行结果是否是成功，且执行后打印的信息不是空；如果不满足条件，将抛出异常
     * @param tag 申明一个标签，将在抛出异常中携带
     * @return this ExecResult, 为了链式调用
     */
    public ExecResult checkNonEmptyText(String tag) {
        checkSuccess(tag)
        atm.preds.nonEmpty(this.text, "$tag <text>")
        return this
    }
}