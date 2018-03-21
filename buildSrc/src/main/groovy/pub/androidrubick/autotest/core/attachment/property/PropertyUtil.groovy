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
     * 是否包含属性值
     * @param name property name
     * @return 是否包含属性值
     * @since 1.0.0
     */
    public Object has(String name) {
        return project.hasProperty(name)
    }

    /**
     * 获取属性值
     * @param name property name
     * @param defVal default value
     * @return 属性值
     * @since 1.0.0
     */
    public Object value(String name, Object defVal = null) {
        return has(name) ? project.property(name) : defVal
    }

    /**
     * 如果<b>目标属性值</b>与`true`等价，比如值为1，true等，返回true
     * @param name property name
     * @return 如果<b>目标属性值</b>与`true`等价，比如值为1，true等，返回true
     * @since 1.0.0
     */
    public boolean valueTrue(String value) {
        return ['1', 'true'].contains(String.valueOf(value?.toLowerCase()))
    }

    /**
     * 如果<b>目标属性</b>与`true`等价，比如值为1，true等，返回true
     * @param name property name
     * @return 如果<b>目标属性</b>与`true`等价，比如值为1，true等，返回true
     * @since 1.0.0
     */
    public boolean isTrue(String name) {
        return valueTrue(String.valueOf(this.value(name)))
    }

}