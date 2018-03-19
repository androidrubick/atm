package pub.androidrubick.autotest.core.attachment

import org.gradle.api.Project
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.ATMContext

/**
 * base Attachment class
 *
 * <p>
 * Created by Yin Yong on 2018/3/15.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class BaseAttachment {

    private final ATMContext mContext
    public BaseAttachment(ATMContext context) {
        mContext = context
    }

    /**
     * 获取上下文对象，能够直接使用过该对象提供的功能、环境信息
     * @return ATMContext
     */
    public final ATMContext getContext() {
        return this.mContext
    }

    /**
     * 获取当前Attachment所属的project
     * @return Project
     */
    public final Project getProject() {
        return this.context.project
    }

    /**
     * 获取当前Attachment所属的ATM注入对象，可以使用其提供的功能
     * @return ATM
     */
    public final ATM getAtm() {
        return ATM.fromProject(this.project)
    }

    private Map mExtraProperties
    public void set(String name, Object obj) {
        atm.log("Set $name Field To: $this, For class : ${this.class.simpleName}")
        if (null == mExtraProperties) {
            synchronized (this) {
                if (null == mExtraProperties) {
                    mExtraProperties = [:]
                }
            }
        }
        mExtraProperties.put(name, obj)
    }

    public Object get(String name) {
        atm.log("Get $name Field From: $this, For class : ${this.class.simpleName}")
        if (null != mExtraProperties && mExtraProperties.containsKey(name)) {
            return mExtraProperties.get(name)
        }
        try {
            return this.project."$name"
        } catch (Throwable e) {
            throw new MissingFieldException(name, getClass(), e)
        }
    }

}