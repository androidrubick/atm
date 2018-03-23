package pub.androidrubick.autotest.android.tasks

import android.support.annotation.NonNull
import org.gradle.api.Project
import org.gradle.api.Task
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.tasks.app.AndroidArchiveCollector
import pub.androidrubick.autotest.android.tasks.install.InstallApkTask
import pub.androidrubick.autotest.android.tasks.install.UninstallApkTask
import pub.androidrubick.autotest.android.tasks.launch.LaunchAndroidAppTask
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.attachment.app.AppPlatform
import pub.androidrubick.autotest.core.tasks.TaskGraph
import pub.androidrubick.autotest.core.tasks.TaskGroups
import pub.androidrubick.autotest.core.tasks.upload.Upload2PgyerTask
import pub.androidrubick.autotest.core.util.Utils

@SuppressWarnings("GroovyUnusedDeclaration")
class AndroidTaskGraph extends BaseAndroidAttachment implements TaskGraph {

    public static final String INJECT_NAME = "atm_android_tasks"

    public static AndroidTaskGraph attach(@NonNull Project myProject) {
        if (myProject.extensions.findByName(INJECT_NAME) == null) {
            myProject.extensions.create(INJECT_NAME, AndroidTaskGraph.class, ATM.fromProject(myProject).context)
        }
        return fromProject(myProject)
    }

    public static AndroidTaskGraph fromProject(Project project) {
        return project."$INJECT_NAME"
    }

    public static final String TASK_COLLECT_ANDROID_ENV = 'collectAndroidEnv'
    public static final String TASK_COLLECT_ANDROID_DEVICE = 'collectAndroidDevice'

    private final List<AppArchiveType> mAppTypes
    private final Map<AppArchiveType, AndroidArchiveCollector> mArchiveCollectorMap
    AndroidTaskGraph(ATMContext context) {
        super(context)
        mAppTypes = AppArchiveType.allAvailableOf(AppPlatform.Android)

        def acMap = [:]
        mAppTypes.each { AppArchiveType type ->
            acMap.put(type, new AndroidArchiveCollector(context, type))
        }
        mArchiveCollectorMap = acMap

        buildGraph()
    }

    public List<AppArchiveType> getAllTypes() {
        return new ArrayList<>(mAppTypes)
    }

    public List<AndroidArchiveCollector> getArchiveCollectors() {
        return new ArrayList<>(mArchiveCollectorMap.values())
    }

    public AndroidArchiveCollector getArchiveCollector(AppArchiveType appArchiveType) {
        return mArchiveCollectorMap.find { type, ac ->
            appArchiveType == type
        }?.value
    }

    public Task getCollectAndroidEnvTask() {
        return mCollectAndroidEnvTask
    }

    public Task getCollectAndroidDeviceTask() {
        return mCollectAndroidDeviceTask
    }

    public Task getCollectAppTask(AppArchiveType appArchiveType) {
        return getArchiveCollector(appArchiveType)?.collectAppTask
    }

    public Task getUploadAppTask(AppArchiveType appArchiveType) {
        return mTaskMap.get(appArchiveType)?.get(TaskGroups.GROUP_UPLOAD)
    }

    public Task getUninstallAppTask(AppArchiveType appArchiveType) {
        return mTaskMap.get(appArchiveType)?.get(TaskGroups.GROUP_UNINSTALL)
    }

    public Task getInstallAppTask(AppArchiveType appArchiveType) {
        return mTaskMap.get(appArchiveType)?.get(TaskGroups.GROUP_INSTALL)
    }

    public Task getLaunchAppTask(AppArchiveType appArchiveType) {
        return mTaskMap.get(appArchiveType)?.get(TaskGroups.GROUP_LAUNCH)
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = [collectAndroidEnvTask, collectAndroidDeviceTask]
        mTaskMap.each { type, map ->
            tasks.addAll(map.values())
        }
        return tasks
    }

    private Task mCollectAndroidEnvTask
    private Task mCollectAndroidDeviceTask
    private final Map<AppArchiveType, Map<String, Task>> mTaskMap = [:]
    private void buildGraph() {
        mCollectAndroidEnvTask = project.tasks.create(TASK_COLLECT_ANDROID_ENV, CollectAndroidEnvTask.class)
        mCollectAndroidDeviceTask = project.tasks.create(TASK_COLLECT_ANDROID_DEVICE,  CollectAndroidDeviceTask.class)
        mCollectAndroidDeviceTask.dependsOn(mCollectAndroidEnvTask)

        def taskMap = mTaskMap
        archiveCollectors.each { ac ->
            ac.createCollectAndroidAppTask().dependsOn(collectAndroidDeviceTask)
        }.each { ac ->
            def taskMapForType = [:]
            taskMap.put(ac.type, taskMapForType)
            project.with {
                def capitalizedTypeName = Utils.capitalize(ac.type.name)

                def collectAppTask = ac.collectAppTask
                def uninstallTaskName = 'uninstall' + capitalizedTypeName
                def installTaskName = 'install' + capitalizedTypeName
                def launchTaskName = 'launch' + capitalizedTypeName

                def uninstallAppTask = tasks.create(uninstallTaskName, UninstallApkTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(collectAppTask)
                taskMapForType.put(TaskGroups.GROUP_UNINSTALL, uninstallAppTask)

                def installAppTask = tasks.create(installTaskName, InstallApkTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(uninstallAppTask)
                taskMapForType.put(TaskGroups.GROUP_INSTALL, installAppTask)

                def launchAppTask = tasks.create(launchTaskName, LaunchAndroidAppTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(installAppTask)
                taskMapForType.put(TaskGroups.GROUP_LAUNCH, launchAppTask)

                def uploadTaskName = 'upload' + capitalizedTypeName + '2Pgyer'
                def uploadAppTask = tasks.create(uploadTaskName, Upload2PgyerTask.class) { task ->
                    task.archiveCollector = ac
                }.dependsOn(collectAppTask)
                taskMapForType.put(TaskGroups.GROUP_UPLOAD, uploadAppTask)
            }
        }
    }

}