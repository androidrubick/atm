package pub.androidrubick.autotest.android.property

import pub.androidrubick.autotest.core.property.ATMGradleProperties

@SuppressWarnings("GroovyUnusedDeclaration")
class AndroidGradleProperties extends ATMGradleProperties {

    /**
     * 包含android sdk根目录设置，支持如下的环境变量名：
     * 1、ANDROID_HOME；
     * 2、ANDROID_SDK_HOME；
     * 3、ANDROID_SDK；
     * 4、ANDROID_SDK_ROOT；
     *
     * @since 1.0.0
     */
    public static List<String> getAndroidHome() {
        return ['ANDROID_HOME', 'ANDROID_SDK_HOME', 'ANDROID_SDK_ROOT', 'ANDROID_SDK']
    }

    /**
     * 测试用例的
     *
     * @since 1.0.0
     */
    public static final String TEST_CASE = 'TEST_CASE'

}