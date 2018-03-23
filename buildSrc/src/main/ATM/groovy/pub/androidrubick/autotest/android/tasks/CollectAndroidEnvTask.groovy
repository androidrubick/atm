package pub.androidrubick.autotest.android.tasks

import android.util.Log
import org.gradle.api.tasks.TaskAction
import pub.androidrubick.autotest.android.property.AndroidGradleProperties
import pub.androidrubick.autotest.core.tasks.TaskGroups

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
class CollectAndroidEnvTask extends BaseAndroidTask {

    CollectAndroidEnvTask() {
        group = TaskGroups.GROUP_COLLECT
    }

    @TaskAction
    public void collect() {
        atm.logI("Gradle Project properties: $project.ext.properties")

        def androidSDKEnvProName = checkAndroidSDKEnvName()
        atm.logI("Android SDK Environment Name: $androidSDKEnvProName")

        if (isEmpty(androidSDKEnvProName)) {
            try {
                checkAdbEnv()
            } catch (Throwable ignore) {
                atm.logE(Log.getStackTraceString(ignore))
                throw new IllegalAccessException("No Android Home Found: you may add -PANROID_HOME in cmd")
            }
        } else {
            def dir = new File(atm.prop.value(androidSDKEnvProName))
            atm.preds.file(dir, "Android Home: $androidSDKEnvProName")
            androidSdk.configuration.with {
                SDKDir = dir
            }
            checkAdbEnv()
        }
    }

    private void checkAdbEnv() {
        androidSdk.adb.version()
        androidSdk.adb.startServer()
    }

    /**
     * 从环境变量中获取android SDK的路径;
     *
     * @since 1.0.0
     */
    private String checkAndroidSDKEnvName() {
        return AndroidGradleProperties.androidHome.find { name ->
            project.hasProperty(name)
        } ?: ''
    }
}