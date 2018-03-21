package pub.androidrubick.autotest.android

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.android.tasks.CollectAndroidDeviceTask
import pub.androidrubick.autotest.android.tasks.CollectAndroidEnvTask
import pub.androidrubick.autotest.android.tasks.app.AndroidArchiveCollector
import pub.androidrubick.autotest.android.tasks.install.InstallApkTask
import pub.androidrubick.autotest.android.tasks.install.UninstallApkTask
import pub.androidrubick.autotest.android.tasks.launch.LaunchAndroidAppTask
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.BaseATMPlugin
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.attachment.app.AppPlatform
import pub.androidrubick.autotest.core.tasks.upload.Upload2PgyerTask
import pub.androidrubick.autotest.core.util.Utils

/**
 * <p>
 * Created by Yin Yong on 2018/3/8.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GrMethodMayBeStatic")
public class AndroidPlugin extends BaseATMPlugin {

    public static final String TASK_COLLECT_ANDROID_ENV = 'collectAndroidEnv'
    public static final String TASK_COLLECT_ANDROID_DEVICE = 'collectAndroidDevice'

    @Override
    protected void applyMe(@NonNull Project project, @NonNull ATM atm) {
        AndroidSdk.attach(project)

        project.with {
            tasks.create(TASK_COLLECT_ANDROID_ENV, CollectAndroidEnvTask)
            tasks.create(TASK_COLLECT_ANDROID_DEVICE,  CollectAndroidDeviceTask).dependsOn(TASK_COLLECT_ANDROID_ENV)
        }

        def context = AndroidSdk.fromProject(project).context
        AppArchiveType.allAvailableOf(AppPlatform.Android).collect { AppArchiveType type ->
            new AndroidArchiveCollector(context, type)
        }.each { ac ->
            ac.createCollectAndroidAppTask().dependsOn(TASK_COLLECT_ANDROID_DEVICE)
        }.each { ac ->
            String capitalizedTypeName = Utils.capitalize(ac.type.name)
            project.with {
                def collectTaskName = ac.collectAppTask.name
                def uninstallTaskName = 'uninstall' + capitalizedTypeName
                def installTaskName = 'install' + capitalizedTypeName
                def launchTaskName = 'launch' + capitalizedTypeName

                tasks.create(uninstallTaskName, UninstallApkTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(collectTaskName)

                tasks.create(installTaskName, InstallApkTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(uninstallTaskName)

                tasks.create(launchTaskName, LaunchAndroidAppTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(installTaskName)

                def uploadTaskName = 'upload' + capitalizedTypeName + '2Pgyer'
                tasks.create(uploadTaskName, Upload2PgyerTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(collectTaskName)
            }
        }
    }

}
