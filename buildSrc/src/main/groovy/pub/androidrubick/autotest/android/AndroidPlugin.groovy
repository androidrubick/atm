package pub.androidrubick.autotest.android

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.android.tasks.CollectAndroidDeviceTask
import pub.androidrubick.autotest.android.tasks.CollectAndroidEnvTask
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.BaseATMPlugin

/**
 * <p>
 * Created by Yin Yong on 2018/3/8.
 *
 * @since 1.0.0
 */
public class AndroidPlugin extends BaseATMPlugin {

    @Override
    protected void applyMe(@NonNull Project project, @NonNull ATM atm) {
        AndroidSdk.attach(project)

        project.with {
            tasks.create('collectAndroidEnv', CollectAndroidEnvTask)
            tasks.create('collectAndroidDevice',  CollectAndroidDeviceTask).dependsOn('collectAndroidEnv')

            AppArchiveType.allAvailableOf(AppPlatform.Android).each { AppArchiveType type ->
                tasks.create('collect' + Utils.capitalize(type.name), CollectAndroidAppTask)
            }

//            tasks.create('dumpUI', DumpUITask)
//            tasks.create('installApk', InstallApkTask)
//            tasks.create('launchApp', LaunchAppTask)
//            tasks.create('instrumentApp', InstrumentAppTask)
//            tasks.create('simpleSampler', SimpleSamplerTask)


//            tasks.create('assembleT', SimpleSamplerTask)
//            tasks.create('performAndroidSimpleScene', SimpleSamplerTask)
        }
    }
}
