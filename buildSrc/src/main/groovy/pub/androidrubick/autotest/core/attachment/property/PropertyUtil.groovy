package pub.androidrubick.autotest.core.attachment.property

import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment

/**
 * Attachment for get Property/Properties from project
 *
 * <p>
 * Created by Yin Yong on 2018/3/15.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GrMethodMayBeStatic"])
public class PropertyUtil extends BaseAttachment {

    PropertyUtil(ATMContext context) {
        super(context)
    }

    /**
     * 获取属性值
     * @param name property name
     * @param defVal default value
     * @return 属性值
     * @since 1.0.0
     */
    public Object value(String name, Object defVal = null) {
        return project.hasProperty(name) ? project.property(name) : defVal
    }

    /**
     * 如果目标属性与`true`等价，比如值为1，true等，返回true
     * @param name property name
     * @param defVal default value
     * @return 如果目标属性与`true`等价，比如值为1，true等，返回true
     * @since 1.0.0
     */
    public boolean valueTrue(String name) {
        return ['1', 'true'].contains(String.valueOf(this.value(name)))
    }

}