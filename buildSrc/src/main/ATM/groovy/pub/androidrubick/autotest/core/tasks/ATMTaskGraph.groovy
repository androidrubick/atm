package pub.androidrubick.autotest.core.tasks

import android.support.annotation.NonNull
import org.gradle.api.Project
import org.gradle.api.Task
import pub.androidrubick.autotest.core.ATM
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment
import pub.androidrubick.autotest.core.attachment.app.AppArchiveType
import pub.androidrubick.autotest.core.tasks.app.ArchiveCollector
import pub.androidrubick.autotest.core.tasks.app.DefaultArchiveCollector
import pub.androidrubick.autotest.core.tasks.upload.Upload2PgyerTask
import pub.androidrubick.autotest.core.util.Utils

@SuppressWarnings("GroovyUnusedDeclaration")
class ATMTaskGraph extends BaseAttachment implements TaskGraph {

    public static final String INJECT_NAME = "atm_tasks"

    public static ATMTaskGraph attach(@NonNull Project myProject) {
        if (myProject.extensions.findByName(INJECT_NAME) == null) {
            myProject.extensions.create(INJECT_NAME, ATMTaskGraph.class, ATM.fromProject(myProject).context)
        }
        return fromProject(myProject)
    }

    public static ATMTaskGraph fromProject(Project project) {
        return project."$INJECT_NAME"
    }

    private final List<AppArchiveType> mAppTypes
    private final Map<AppArchiveType, ArchiveCollector> mArchiveCollectorMap
    ATMTaskGraph(ATMContext context) {
        super(context)
        mAppTypes = AppArchiveType.allAvailable
        def acMap = [:]
        mAppTypes.each { AppArchiveType type ->
            acMap.put(type, new DefaultArchiveCollector(context, type))
        }
        mArchiveCollectorMap = acMap
        buildGraph()
    }

    public List<AppArchiveType> getAllTypes() {
        return new ArrayList<>(mAppTypes)
    }

    public List<AppArchiveType> getArchiveCollectors() {
        return new ArrayList<>(mArchiveCollectorMap.values())
    }

    public ArchiveCollector getArchiveCollector(AppArchiveType appArchiveType) {
        return mArchiveCollectorMap.find { type, ac ->
            appArchiveType == type
        }?.value
    }

    public Task getCollectAppTask(AppArchiveType appArchiveType) {
        return getArchiveCollector(appArchiveType)?.collectAppTask
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = []
        mTaskMap.each { type, map ->
            tasks.addAll(map.values())
        }
        return tasks
    }

    private final Map<AppArchiveType, Map<String, Task>> mTaskMap = [:]
    private void buildGraph() {
        def taskMap = mTaskMap
        archiveCollectors.each { ArchiveCollector ac ->
            ac.createDefaultCollectAppTask()
        }.each { ArchiveCollector ac ->
            def taskMapForType = [:]
            taskMap.put(ac.type, taskMapForType)

            String capitalizedTypeName = Utils.capitalize(ac.type.name)

            def collectAppTask = ac.collectAppTask
            def uploadTaskName = 'upload' + capitalizedTypeName + '2Pgyer'

            def uploadAppTask = project.tasks.create(uploadTaskName, Upload2PgyerTask.class) { task ->
                task.archiveCollector = ac
            }.dependsOn(collectAppTask)
            taskMapForType.put(TaskGroups.GROUP_UPLOAD, uploadAppTask)
        }
    }

}