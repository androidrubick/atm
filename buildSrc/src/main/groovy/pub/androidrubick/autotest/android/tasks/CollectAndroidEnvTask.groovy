package pub.androidrubick.autotest.android.tasks

import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.core.tasks.TaskGroups

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class CollectAndroidEnvTask extends BaseAndroidTask {

    CollectAndroidEnvTask() {
        group = TaskGroups.GROUP_COLLECT
    }

    @TaskAction
    public void collect() {
        def androidSDKEnvProName = checkAndroidSDKEnvName()
        atm.logI("Android SDK Environment Name: $androidSDKEnvProName")

        if (isEmpty(androidSDKEnvProName)) {
            throw new IllegalAccessException("No Android Home Found: you may add -PANROID_HOME in cmd")
        }

        def dir = new File(atm.prop.value(androidSDKEnvProName))
        atm.preds.file(dir, "Android Home: $androidSDKEnvProName")
        androidSdk.configuration.with {
            SDKDir = dir
        }

        checkAdbEnv()
    }

    private void checkAdbEnv() {
        androidSdk.cmd.adb.version()
    }

    /**
     * 从环境变量中获取android SDK的路径;
     *
     * 包含android sdk根目录设置，支持如下的环境变量名：
     * 1、ANDROID_HOME；
     * 2、ANDROID_SDK_HOME；
     * 3、ANDROID_SDK；
     * 4、ANDROID_SDK_ROOT；
     *
     * @since 1.0.0
     */
    private String checkAndroidSDKEnvName() {
        if (project.hasProperty('ANDROID_HOME')) {
            return 'ANDROID_HOME'
        }
        if (project.hasProperty('ANDROID_SDK_HOME')) {
            return 'ANDROID_SDK_HOME'
        }
        if (project.hasProperty('ANDROID_SDK_ROOT')) {
            return 'ANDROID_SDK_ROOT'
        }
        if (project.hasProperty('ANDROID_SDK')) {
            return 'ANDROID_SDK'
        }
        return ''
    }
}